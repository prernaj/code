package low.level.carRental.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import low.level.carRental.TestData;
import low.level.carRental.exception.InvalidVehicleIdException;
import low.level.carRental.exception.ReservationNotFoundException;
import low.level.carRental.exception.VehicleBookedException;
import low.level.carRental.model.account.User;
import low.level.carRental.model.reservation.ReservationStatus;
import low.level.carRental.model.reservation.VehicleInventory;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.model.vehicle.VehicleStatus;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleInventoryRepository;
import low.level.carRental.repository.VehicleRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {
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
    public void should_ScanToReserve() throws VehicleBookedException, InvalidVehicleIdException {
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
        VehicleReservation vehicleReservation = userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());

        assertNotNull(vehicleReservation);
    }

    @Test
    public void should_CancelReservation() throws VehicleBookedException, InvalidVehicleIdException {
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

        assertEquals(vehicleReservation.getStatus(), ReservationStatus.CANCELLED);
    }

    @Test
    public void should_PickupVehicle() throws VehicleBookedException, InvalidVehicleIdException {
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
        userService.pickupVehicle(vehicleReservation);

        assertEquals(vehicleList.get(1).getVehicleStatus(), VehicleStatus.INUSE);
    }

    @Test
    public void should_ReturnVehicle() throws VehicleBookedException, InvalidVehicleIdException, ReservationNotFoundException {
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
        userService.pickupVehicle(vehicleReservation);
        userService.returnVehicle(vehicleReservation.getReservationId(),vehicleList.get(1).getParkedLocation());
        assertEquals(vehicleReservation.getStatus(), ReservationStatus.COMPLETED);
        assertEquals(vehicleList.get(1).getVehicleStatus(), VehicleStatus.AVAILALBE);
    }

    @Test
    public void should_GetHiredVehicles() throws VehicleBookedException, InvalidVehicleIdException {
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
        //VehicleReservation vehicleReservation = userService.scanToReserve(vehicleList.get(1).getQrCode(), user.getId());
        List<HireableVehicle> hiredVehicles = userService.getHiredVehicles(user.getId());
        assertEquals(hiredVehicles.size(), 1);
    }

    @Test
    public void should_GetHiredVehiclesInDateRange() throws VehicleBookedException, InvalidVehicleIdException {
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
        List<HireableVehicle> hiredVehicles = userService.getHiredVehicles(user.getId(),LocalDateTime.now().minusDays(3), LocalDateTime.now().plusDays(1));
        assertEquals(hiredVehicles.size(), 1);

        hiredVehicles = userService.getHiredVehicles(user.getId(),LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(4));
        assertEquals(hiredVehicles.size(), 0);
    }

    @Test
    public void should_RemoteReserve() throws VehicleBookedException, InvalidVehicleIdException {
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
        vehicleReservation = userService.remoteReserve(vehicleReservation);
        assertNotNull(vehicleReservation);
        assertEquals(vehicleReservation.getStatus(), ReservationStatus.CONFIRMED);
    }
}
