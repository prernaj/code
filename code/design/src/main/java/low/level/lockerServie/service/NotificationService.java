package low.level.lockerServie.service;


import low.level.lockerServie.model.LockerPackage;
import low.level.lockerServie.model.Notification;
import low.level.lockerServie.repository.NotificationRepository;

public class NotificationService {
    CustomerService customerService = new CustomerService();

    public void notifyCustomerOrder(LockerPackage lockerPackage) {
        String customerId = customerService.getCustomerIdForOrder(lockerPackage.getOrderId());
        Notification notification = new Notification(customerId, lockerPackage.getOrderId(), lockerPackage.getLockerId(), lockerPackage.getCode());
        NotificationRepository.notificationMap.put(lockerPackage.getOrderId(), notification);
        System.out.printf("Customer %s notified for order %s in lokcer %s with pin %s", customerId, lockerPackage.getOrderId(), lockerPackage.getLockerId(), lockerPackage.getCode());
    }
}
