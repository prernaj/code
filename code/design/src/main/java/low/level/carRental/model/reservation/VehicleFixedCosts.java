package low.level.carRental.model.reservation;

import java.util.HashMap;
import java.util.Map;

import low.level.carRental.model.vehicle.VehicleType;


public class VehicleFixedCosts {
    public static Map<VehicleType, Double> vehicleFixedCost = new HashMap<>();

    static {
        vehicleFixedCost.put(VehicleType.BICYCLE, 5.0);
        vehicleFixedCost.put(VehicleType.MOTORCYCLE, 20.0);
        vehicleFixedCost.put(VehicleType.HATCHBACK, 50.0);
        vehicleFixedCost.put(VehicleType.SEDAN, 100.0);
        vehicleFixedCost.put(VehicleType.SUV, 100.0);
        vehicleFixedCost.put(VehicleType.TRUCK, 250.0);
        vehicleFixedCost.put(VehicleType.THREEWHEELER, 50.0);
        vehicleFixedCost.put(VehicleType.VAN, 100.0);
    }
}
