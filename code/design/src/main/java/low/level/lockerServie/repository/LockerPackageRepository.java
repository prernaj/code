package low.level.lockerServie.repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import low.level.lockerServie.model.LockerPackage;

public class LockerPackageRepository {
    public static List<LockerPackage> lockerPackages = new ArrayList<>();

    public static Optional<LockerPackage> getLockerByLockerId(String lockerId) {
        return lockerPackages.stream()
            .filter(lockerPackage -> lockerPackage.getLockerId().equals(lockerId))
            .findFirst();
    }
    
}
