package low.level.carRental.service;

import java.util.List;

import org.junit.jupiter.api.Test;

import low.level.carRental.TestData;
import low.level.carRental.exception.InvalidVehicleIdException;
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

public class InvoiceBuilderUtilTest {
    @Test
    public void shouldBuildCancelledInvoice() throws VehicleBookedException, InvalidVehicleIdException {
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
        VehicleReservation vehicleReservation = userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        vehicleReservation = userService.cancel(vehicleReservation.getReservationId());

        Invoice invoice = InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);

        assertNotNull(invoice);
        assertEquals(invoice.getUsageCharges(), 50);
        assertEquals(invoice.getTaxes(), 9);
        assertEquals(invoice.getTotal(), 59);
    }
}