package low.level.carRental.service;

import org.junit.jupiter.api.Test;

import low.level.carRental.TestData;
import low.level.carRental.exception.VehicleNotExistsException;
import low.level.carRental.model.vehicle.HireableVehicle;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleServiceTest {
    @Test
    public void addVehicleTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle = vehicleService.addVehicle(TestData.getHatchBack());
        assertNotNull(hireableVehicle);
    }

    @Test
    public void updateQrCodeTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle = vehicleService.addVehicle(TestData.getHatchBack());
        String qrCode = UUID.randomUUID().toString();
        hireableVehicle.setQrCode(qrCode);
        vehicleService.updateQrCode(hireableVehicle.getId(), qrCode);
        HireableVehicle altered = vehicleService.getVehicleById(hireableVehicle.getId());
        assertEquals(qrCode, altered.getQrCode());
    }

    @Test
    public void getVehicleByIdTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle = vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        assertNotNull(vehicle);
    }

    @Test
    public void getVehicleByQrCodeTest() {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle = vehicleService.addVehicle(TestData.getHatchBack());
        HireableVehicle vehicle = vehicleService.getVehicleByQrCode(hireableVehicle.getQrCode());
        assertNotNull(vehicle);
    }

    @Test
    public void removeVehicleTest() throws VehicleNotExistsException {
        VehicleService vehicleService = new VehicleServiceImpl();
        HireableVehicle hireableVehicle = vehicleService.addVehicle(TestData.getHatchBack());
        vehicleService.removeVehicle(hireableVehicle.getId());
        HireableVehicle vehicle = vehicleService.getVehicleById(hireableVehicle.getId());
        assertNull(vehicle);
    }
}

