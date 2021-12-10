package low.level.carRental.service;

import java.time.LocalDateTime;
import java.util.List;

import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.VehicleType;

public interface VehicleReservationService {
    List<VehicleReservation> getReservations(VehicleType vehicleType);

    boolean isVehicleBooked(String qrCode, LocalDateTime fromDate, LocalDateTime toDate);
}
