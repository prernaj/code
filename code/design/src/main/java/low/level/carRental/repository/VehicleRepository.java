package low.level.carRental.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import low.level.carRental.model.vehicle.HireableVehicle;

public class VehicleRepository {
    public static Map<String, HireableVehicle> vehicleMap = new HashMap<>();
    public static List<HireableVehicle> vehicles = new ArrayList<>();

    public HireableVehicle addVehicle(HireableVehicle hireableVehicle) {
        vehicleMap.putIfAbsent(hireableVehicle.getId(), hireableVehicle);
        vehicles.add(hireableVehicle);
        return hireableVehicle;
    }

}

