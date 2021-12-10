package low.level.carRental.service;

import java.time.LocalDateTime;
import java.util.List;

import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.VehicleType;
import low.level.carRental.repository.VehicleInventoryRepository;
import low.level.carRental.repository.VehicleReservationRepository;

public class VehicleReservationServiceImpl implements VehicleReservationService {
    VehicleReservationRepository vehicleReservationRepository = new VehicleReservationRepository();

    @Override
    public List<VehicleReservation> getReservations(VehicleType vehicleType) {
        return vehicleReservationRepository.getVehicleReservations(vehicleType);
    }

    @Override
    public boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate) {
        return VehicleInventoryRepository.vehicleInventoryList
                .stream().anyMatch(vehicleInventory ->
                        vehicleInventory.getVehicle().getId().equalsIgnoreCase(qrCode) &&
                                ((vehicleInventory.getDueDate() != null &&
                                        fromDate.isBefore(vehicleInventory.getDueDate()))
                                        && (vehicleInventory.getFromDate() != null
                                        && toDate.isAfter(vehicleInventory.getFromDate()))));
    }
}
