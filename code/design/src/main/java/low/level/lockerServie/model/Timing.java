package low.level.lockerServie.model;

import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Timing {
    private Time openTime;
    private Time closeTime;

    public Timing(String openTime, String closeTime) {
        this.openTime = Time.valueOf(openTime);
        this.closeTime = Time.valueOf(closeTime);
    }
}
