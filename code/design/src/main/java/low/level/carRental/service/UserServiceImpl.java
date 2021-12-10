package low.level.carRental.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util. UUID;

import low.level.carRental.exception.InvalidVehicleIdException;
import low.level.carRental.exception.ReservationNotFoundException;
import low.level.carRental.exception.VehicleBookedException;
import low.level.carRental.model.common.NotificationStatus;
import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.InvoiceNotification;
import low.level.carRental.model.reservation.ReservationStatus;
import low.level.carRental.model.reservation.VehicleInventory;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.reservation.VehicleReservationType;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.model.vehicle.VehicleLocation;
import low.level.carRental.model.vehicle.VehicleStatus;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleInventoryRepository;
import low.level.carRental.repository.VehicleRepository;
import low.level.carRental.repository.VehicleReservationRepository;

public class UserServiceImpl implements UserService {

    UserRepository userRepository = new UserRepository();
    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    VehicleReservationService vehicleReservationService = new
            VehicleReservationServiceImpl();
    InvoiceService invoiceService = new InvoiceServiceImpl();
    InvoiceNotificationService invoiceNotificationService =
            new InvoiceNotificationServiceImpl();

    VehicleInventoryRepository vehicleInventoryRepository = new VehicleInventoryRepository();

    @Override
    public VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException {
        if (VehicleRepository.vehicleMap.get(qrCode) == null) {
            throw new InvalidVehicleIdException("Invalid vehicle id.");
        }
        if (vehicleReservationService.isVehicleBooked(qrCode,
                LocalDateTime.now(), LocalDateTime.now().plusHours(2))) {
            throw new VehicleBookedException("Vehicle booked. Try another vehicle.");
        }
        VehicleReservation vehicleReservation = buildQuickReservation(qrCode, userId);
        vehicleReservation = vehicleReservationRepository.reserve(vehicleReservation);
        updateVehicleInventory(vehicleReservation);
        return vehicleReservation;
    }

    @Override
    public VehicleReservation remoteReserve(VehicleReservation vehicleReservation) {
        vehicleReservation.setStatus(ReservationStatus.CONFIRMED);
        vehicleReservation.setReservationDate(LocalDateTime.now());
        vehicleReservation = vehicleReservationRepository.reserve(vehicleReservation);
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        //Notify vehicle inventory
        updateVehicleInventory(vehicleReservation);
        return vehicleReservation;
    }

    @Override
    public VehicleReservation cancel(String reservationId) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap.get(reservationId);
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleReservation.getAccocatedVehicleId());
        vehicleReservation.setStatus(ReservationStatus.CANCELLED);
        //Notify vehicle inventory
        updateVehicleInventory(vehicleReservation);
        vehicleReservation.setDropLocation(hireableVehicle.getParkedLocation().getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(hireableVehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
        return vehicleReservation;
    }

    @Override
    public HireableVehicle pickupVehicle(VehicleReservation vehicleReservation) {
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        vehicleReservation.setStartMileage(hireableVehicle.getMileage());
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        hireableVehicle.setVehicleStatus(VehicleStatus.INUSE);
        return hireableVehicle;
    }

    @Override
    public void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException {

        VehicleReservation vehicleReservation = vehicleReservationRepository
                .getVehicleReservation(reservationId);
        if (vehicleReservation == null) {
            throw new ReservationNotFoundException
                    ("Could not find reservation with id " + reservationId);
        }

        HireableVehicle vehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());
        vehicle.setParkedLocation(vehicleLocation);
        vehicle.setVehicleStatus(VehicleStatus.AVAILALBE);
        vehicleReservation.setStatus(ReservationStatus.COMPLETED);
        vehicleReservation.setDropLocation(vehicleLocation.getAddress());
        vehicleReservation.setReturnDate(LocalDateTime.now());
        vehicleReservation.setEndMileage(vehicle.getMileage());
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        updateVehicleInventory(vehicleReservation);
        invoiceNotificationService.notifyUser(buildInvoiceNotification(invoice));
    }

    @Override
    public List<HireableVehicle> getHiredVehicles(String userId) {
        return userRepository.getHiredVehicles(userId);
    }

    @Override
    public List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                                  LocalDateTime endDate) {
        return userRepository.getHiredVehicles(userId, startDate, endDate);
    }

    private VehicleReservation buildQuickReservation(String qrCode, String userId) {

        HireableVehicle vehicle = VehicleRepository.vehicleMap.get(qrCode);
        vehicle.setVehicleStatus(VehicleStatus.BOOKED);
        VehicleReservation vehicleReservation = new VehicleReservation();
        vehicleReservation.setUsrId(userId);
        vehicleReservation.setReservationId(UUID.randomUUID().toString());
        vehicleReservation.setFromDate(LocalDateTime.now());
        vehicleReservation.setDueDate(LocalDateTime.now().plusHours(2));
        vehicleReservation.setStatus(ReservationStatus.ACTIVE);
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        vehicleReservation.setVehicleType(vehicle.getVehicleType());
        vehicleReservation.setStartMileage(vehicle.getMileage());
        vehicleReservation.setPickupLocation(vehicle.getParkedLocation().getAddress());

        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(qrCode);
        vehicleReservation.setAccocatedVehicleId(hireableVehicle.getId());
        vehicleReservation.setVehicleReservationType(VehicleReservationType.HOURLY);
        return vehicleReservation;
    }

    private InvoiceNotification buildInvoiceNotification(Invoice invoice) {
        InvoiceNotification invoiceNotification = new InvoiceNotification();
        invoiceNotification.setReservationId(invoice.getReservationId());
        invoiceNotification.setUserId(invoice.getUserId());
        invoiceNotification.setCreatedDate(LocalDateTime.now());
        invoiceNotification.setNotificationStatus(NotificationStatus.PENDING);
        return invoiceNotification;
    }

    private VehicleInventory buildVehicleInventory(VehicleReservation vehicleReservation) {
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleReservation.getAccocatedVehicleId());
        VehicleInventory vehicleInventory = new VehicleInventory(vehicleReservation, hireableVehicle);
        return vehicleInventory;
    }

    private void updateVehicleInventory(VehicleReservation vehicleReservation) {
        VehicleInventory vehicleInventory = buildVehicleInventory(vehicleReservation);
        vehicleInventoryRepository.addToInventory(vehicleInventory);
    }
}
