package low.level.lockerServie.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import low.level.lockerServie.model.GeoLocation;
import low.level.lockerServie.model.Locker;
import low.level.lockerServie.model.LockerSize;
import low.level.lockerServie.model.LockerStatus;

public class LockerRepository {
    public static List<Locker> lockers;
    public static Map<String, Locker> lockerMap;

    static {
        lockers = new ArrayList<>();
        lockerMap = new HashMap<>();
    }

    public static Locker getLocker(LockerSize lockersize, GeoLocation location) {
        // assumption the nearby lockers in radius are fetched from a service
        List<Locker> lockerList = lockers.stream()
        .filter(locker -> locker.getLockerStatus() == LockerStatus.AVAILABLE && locker.getLockerSize() == lockersize)
        .collect(Collectors.toList());
        if (lockerList != null && lockerList.size() > 0) {
            return lockerList.get(0);
        }
        return null;
    }
}
