package low.level.carRental.service;

import low.level.carRental.exception.VehicleNotExistsException;
import low.level.carRental.model.vehicle.HireableVehicle;

public interface VehicleService {

    HireableVehicle getVehicleById(String id);

    HireableVehicle getVehicleByQrCode(String qrCode);

    HireableVehicle addVehicle(HireableVehicle hireableVehicle);

    void updateQrCode(String vehicleId, String qrCode) throws VehicleNotExistsException;

    void removeVehicle(String vehicleId) throws VehicleNotExistsException;
}