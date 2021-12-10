package low.level.carRental.service;

import java.time.LocalDateTime;
import java.util.List;

import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.model.vehicle.VehicleType;

public interface VehicleSearchService {
    List<HireableVehicle> search(VehicleType vehicleType, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(String make, String city,
                                 String model, LocalDateTime fromDate, LocalDateTime toDate);

    List<HireableVehicle> search(int seats, String city,
                                 LocalDateTime fromDate, LocalDateTime toDate);
}
