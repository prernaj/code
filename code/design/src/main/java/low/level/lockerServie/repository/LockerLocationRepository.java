package low.level.lockerServie.repository;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import low.level.lockerServie.model.LockerLocation;

public class LockerLocationRepository {
    public static List<LockerLocation> lockerLocations = new ArrayList<>();
    
    public static LockerLocation getLockerLocation(String locationId) {
        java.util.Optional<LockerLocation> lockerLocation = lockerLocations.stream()
                                .filter(ll -> ll.getLocationId().equals(locationId)).findFirst();
        if (lockerLocation.isPresent()) {
            return lockerLocation.get();
        }
        return null;
    }
    
}
