package low.level.carRental.service;

import java.time.LocalDateTime;
import java.util.List;

import low.level.carRental.exception.InvalidVehicleIdException;
import low.level.carRental.exception.ReservationNotFoundException;
import low.level.carRental.exception.VehicleBookedException;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.model.vehicle.VehicleLocation;

public interface UserService {
    VehicleReservation scanToReserve(String qrCode, String userId) throws InvalidVehicleIdException, VehicleBookedException;

    VehicleReservation remoteReserve(VehicleReservation vehicleReservation);

    VehicleReservation cancel(String reservationId);

    HireableVehicle pickupVehicle(VehicleReservation vehicleReservation);

    void returnVehicle(String reservationId, VehicleLocation vehicleLocation) throws ReservationNotFoundException;

    List<HireableVehicle> getHiredVehicles(String userId);

    List<HireableVehicle> getHiredVehicles(String userId, LocalDateTime startDate,
                                           LocalDateTime endDate);
}
