package low.level.lockerServie.service;

import java.util.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Optional;

import low.level.lockerServie.exception.LockerCodeMismatchException;
import low.level.lockerServie.exception.LockerNotFoundException;
import low.level.lockerServie.exception.PackPickTimeExceededException;
import low.level.lockerServie.exception.PickupCodeExpiredException;
import low.level.lockerServie.model.GeoLocation;
import low.level.lockerServie.model.LocationTiming;
import low.level.lockerServie.model.Locker;
import low.level.lockerServie.model.LockerLocation;
import low.level.lockerServie.model.LockerPackage;
import low.level.lockerServie.model.LockerSize;
import low.level.lockerServie.model.LockerStatus;
import low.level.lockerServie.model.Timing;
import low.level.lockerServie.repository.LockerLocationRepository;
import low.level.lockerServie.repository.LockerPackageRepository;
import low.level.lockerServie.repository.LockerRepository;

public class LockerService {
    public Locker findLockerById(String id) {
        return LockerRepository.lockerMap.get(id);
    }

    public Locker getLocker(LockerSize lockerSize, GeoLocation geoLocation) {
        return getAvailableLocker(lockerSize, geoLocation);
    }

    public void pickFromLocker(String lockerId, String code, LocalDateTime localDateTime) throws LockerNotFoundException, LockerCodeMismatchException, PackPickTimeExceededException, PickupCodeExpiredException {
        Optional<LockerPackage> lockerPackage = LockerPackageRepository.getLockerByLockerId(lockerId);
        if (!lockerPackage.isPresent()) {
            throw new LockerNotFoundException("Locker with code not found");
        }
        if (!lockerPackage.get().verifyCode(code)) {
            throw new LockerCodeMismatchException("Locker code mismatch");
        }
        if (!lockerPackage.get().isValidCode(localDateTime)) {
            throw new PickupCodeExpiredException("Pickup code expired");
        }
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        if (canPickFromLocker(lockerId, localDateTime)) {
            locker.setLockerStatus(LockerStatus.AVAILABLE);
            lockerPackage.get().setCode(null);
        } else {
            lockerPackage.get().setCode(null);
            throw new PackPickTimeExceededException("Package not picked for x days");
        }
    }

    private Locker getAvailableLocker(LockerSize lockerSize, GeoLocation geoLocation) {
        return checkAndGeAvailaleLockers(lockerSize, geoLocation);
    }

    private Locker checkAndGeAvailaleLockers(LockerSize lockerSize, GeoLocation geoLocation) {
        Locker locker = LockerRepository.getLocker(lockerSize, geoLocation);
        locker.setLockerStatus(LockerStatus.BOOKED);
        return locker;
    }

    private boolean canPickFromLocker(String lockerId, LocalDateTime localDateTime) {
        Locker locker = LockerRepository.lockerMap.get(lockerId);
        LockerLocation locakerLocation = LockerLocationRepository.getLockerLocation(locker.getLocationId());
        LocationTiming locationTiming = locakerLocation.getLocationTiming();
        Timing timing = locationTiming.getTimingMap().get(localDateTime.getDayOfWeek());
        Time currentTime = Time.valueOf(getTimeFromDate(localDateTime));
        if (currentTime.after(timing.getOpenTime()) && currentTime.before(timing.getCloseTime())) {
            return true;
        }
        return false;
    }

    private  String getTimeFromDate(LocalDateTime localDateTime) {
        SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = localDateFormat.format(new Date());
        return time;
    }
}
