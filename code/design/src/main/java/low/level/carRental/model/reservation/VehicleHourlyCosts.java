package low.level.carRental.model.reservation;

import java.util.HashMap;
import java.util.Map;

import low.level.carRental.model.vehicle.VehicleType;


public class VehicleHourlyCosts {
    public static Map<VehicleType, Double> vehicleHourlyCost = new HashMap<>();

    static {
        vehicleHourlyCost.put(VehicleType.BICYCLE, 5.0);
        vehicleHourlyCost.put(VehicleType.MOTORCYCLE, 20.0);
        vehicleHourlyCost.put(VehicleType.HATCHBACK, 50.0);
        vehicleHourlyCost.put(VehicleType.SEDAN, 150.0);
        vehicleHourlyCost.put(VehicleType.SUV, 200.0);
        vehicleHourlyCost.put(VehicleType.TRUCK, 250.0);
        vehicleHourlyCost.put(VehicleType.THREEWHEELER, 100.0);
        vehicleHourlyCost.put(VehicleType.VAN, 100.0);
    }
}
