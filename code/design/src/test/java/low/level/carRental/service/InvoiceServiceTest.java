package low.level.carRental.service;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import low.level.carRental.TestData;
import low.level.carRental.exception.InvalidVehicleIdException;
import low.level.carRental.exception.ReservationNotFoundException;
import low.level.carRental.exception.VehicleBookedException;
import low.level.carRental.model.account.User;
import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.VehicleInventory;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleInventoryRepository;
import low.level.carRental.repository.VehicleRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InvoiceServiceTest {
    @AfterEach
    public void clean() {
        VehicleRepository.vehicleMap.clear();
        VehicleRepository.vehicles.clear();
        UserRepository.userMap.clear();
        UserRepository.userUserIdMap.clear();
        UserRepository.users.clear();
        VehicleInventoryRepository.vehicleInventoryList.clear();
    }

    @Test
    public void should_ComputeDailyInvoice() {
        VehicleRepository vehicleRepository = new VehicleRepository();
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation = TestData.getVehicleReservation(user);
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 1600.0);
        assertEquals(invoice.getAddonCost(), 500.0);
        assertEquals(invoice.getTaxes(), 288.0);
        assertEquals(invoice.getTotal(), 1888.0);
    }

    @Test
    public void should_ComputeHourlyInvoice() throws VehicleBookedException, InvalidVehicleIdException, ReservationNotFoundException {
        VehicleRepository vehicleRepository = new VehicleRepository();
        List<HireableVehicle> vehicleList = TestData.getHireableVehicles();
        for (HireableVehicle hireableVehicle : vehicleList) {
            vehicleRepository.addVehicle(hireableVehicle);
            VehicleInventoryRepository.vehicleInventoryList.add(new VehicleInventory(hireableVehicle));
        }
        User user = TestData.getUser("user@email.com");
        UserRepository.userMap.putIfAbsent("user@email.com", user);
        UserRepository.userUserIdMap.putIfAbsent(user.getId(), user);

        UserService userService = new UserServiceImpl();
        VehicleReservation vehicleReservation =
                userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        userService.returnVehicle(vehicleReservation.getReservationId(),
                vehicleList.get(1).getParkedLocation());
        InvoiceService invoiceService = new InvoiceServiceImpl();
        Invoice invoice = invoiceService.computeInvoice(vehicleReservation);
        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 100);
        assertEquals(invoice.getTaxes(), 18);
        assertEquals(invoice.getTotal(), 118);
    }
}

