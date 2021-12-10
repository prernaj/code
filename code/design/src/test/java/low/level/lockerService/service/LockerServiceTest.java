package low.level.lockerService.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import low.level.lockerService.TestData;
import low.level.lockerServie.exception.LockerCodeMismatchException;
import low.level.lockerServie.exception.LockerNotFoundException;
import low.level.lockerServie.exception.PackPickTimeExceededException;
import low.level.lockerServie.exception.PackageSizeMappingException;
import low.level.lockerServie.exception.PickupCodeExpiredException;
import low.level.lockerServie.model.GeoLocation;
import low.level.lockerServie.model.Locker;
import low.level.lockerServie.model.LockerPackage;
import low.level.lockerServie.model.LockerSize;
import low.level.lockerServie.model.LockerStatus;
import low.level.lockerServie.model.Notification;
import low.level.lockerServie.model.Pack;
import low.level.lockerServie.repository.LockerLocationRepository;
import low.level.lockerServie.repository.LockerPackageRepository;
import low.level.lockerServie.repository.LockerRepository;
import low.level.lockerServie.repository.NotificationRepository;
import low.level.lockerServie.repository.OrderRepository;
import low.level.lockerServie.service.DeliveryService;
import low.level.lockerServie.service.LockerService;
import low.level.lockerServie.utils.SizeUtils;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerServiceTest {
    static LockerService lockerService;
    static DeliveryService deliveryService;

    @BeforeAll
    public static void setup() {
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("RMBGBGKAIN", 12.876416, 77.595466));
        LockerLocationRepository.lockerLocations.add(TestData.setupLockerLocation("VMBGBGKAIN", 12.909953, 77.601866));
        LockerPackageRepository.lockerPackages.add(TestData.getLockerPackage());

        LockerRepository.lockers.addAll(LockerLocationRepository.getLockerLocation("RMBGBGKAIN").getLockers());
        for (Locker locker : LockerRepository.lockers) {
            LockerRepository.lockerMap.put(locker.getId(), locker);
        }

        OrderRepository.orderMap.put("o1", TestData.getPhoneOrder());
        OrderRepository.orderMap.put("o2", TestData.getHeadSetOrder());
        lockerService = new LockerService();
        deliveryService = new DeliveryService();
    }

    @Test
    public void shouldReturnLocker() {
        String lockerId = LockerRepository.lockers.get(0).getId();
        assertNotNull(lockerService.findLockerById(lockerId));
    }

    @Test
    public void shouldGetLocker() {
        Locker locker = lockerService.getLocker(LockerSize.XS, new GeoLocation(12.909953, 77.601866));
        assertNotNull(locker);
    }

    @Test
    public void shouldGetLockerSizeForPack() throws PackageSizeMappingException {
        Pack pack = TestData.getPackage();
        LockerSize lockerSize = SizeUtils.getLockerSizeForPack(pack.getPackageSize());
        System.out.println(lockerSize);
        assertNotNull(lockerSize);
    }

    @Test
    public void emulatePickFromLocker() throws PackageSizeMappingException, LockerCodeMismatchException, LockerNotFoundException, PackPickTimeExceededException, PickupCodeExpiredException {
        deliveryService.deliver("o1");
        Notification notification = NotificationRepository.notificationMap.get("o1");
        LockerPackage lockerPackage =
                LockerPackageRepository.lockerPackages.stream()
                        .filter(lockerPackage1 -> lockerPackage1.getOrderId().equals("o1"))
                        .findFirst().get();
        lockerService.pickFromLocker(lockerPackage.getLockerId(), lockerPackage.getCode(), LocalDateTime.now());

        assertEquals(LockerStatus.AVAILABLE, LockerRepository.lockerMap.get(notification.getLockerId()).getLockerStatus());
    }
}
