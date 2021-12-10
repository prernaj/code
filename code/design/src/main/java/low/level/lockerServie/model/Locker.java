package low.level.lockerServie.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Locker {
    private String id;
    private LockerSize lockerSize;
    private String locationId;
    private LockerStatus lockerStatus;

    public Locker(LockerSize lockerSize, String locationId) {
        
    }
}
