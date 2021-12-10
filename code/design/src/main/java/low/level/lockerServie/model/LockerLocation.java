package low.level.lockerServie.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LockerLocation {
    private String locationId;
    private List<Locker> lockers = new ArrayList<>();
    private GeoLocation geoLocation;
    private LocationTiming locationTiming;
}
