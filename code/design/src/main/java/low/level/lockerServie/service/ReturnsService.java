package low.level.lockerServie.service;

import low.level.lockerServie.model.Item;
import low.level.lockerServie.model.Locker;
import low.level.lockerServie.model.LockerStatus;

public class ReturnsService {
    public void returnItemsToLocker(Item item, Locker locker) {
        locker.setLockerStatus(LockerStatus.CLOSED);
    }
}
