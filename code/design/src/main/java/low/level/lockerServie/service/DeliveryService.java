package low.level.lockerServie.service;

import java.util.List;

import low.level.lockerServie.exception.PackageSizeMappingException;
import low.level.lockerServie.model.Item;
import low.level.lockerServie.model.Locker;
import low.level.lockerServie.model.LockerPackage;
import low.level.lockerServie.model.LockerSize;
import low.level.lockerServie.model.LockerStatus;
import low.level.lockerServie.model.Order;
import low.level.lockerServie.model.Pack;
import low.level.lockerServie.repository.LockerPackageRepository;
import low.level.lockerServie.utils.IdGenerator;
import low.level.lockerServie.utils.SizeUtils;

public class DeliveryService {
    NotificationService notificationService = new NotificationService();
    OrderService orderService = new OrderService();
    LockerService lockerService = new LockerService();

    public void deliver(String orderId) throws  PackageSizeMappingException {
        Order order = orderService.getOrder(orderId);
        List<Item> items = orderService.getItemsForOrder(orderId);
        Pack pack = new Pack(orderId, items);
        LockerSize lockerSize = SizeUtils.getLockerSizeForPack(pack.getPackageSize());
        Locker locker = lockerService.getLocker(lockerSize, order.getDeliveryGeoLocation());
        LockerPackage lockerPackage = new LockerPackage();
        lockerPackage.setOrderId(orderId);
        lockerPackage.setLockerId(locker.getId());
        lockerPackage.setCode(IdGenerator.generateId(6));
        LockerPackageRepository.lockerPackages.add(lockerPackage);
        locker.setLockerStatus(LockerStatus.CLOSED);
        notificationService.notifyCustomerOrder(lockerPackage);
    }
}
