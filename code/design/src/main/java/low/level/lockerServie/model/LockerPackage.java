package low.level.lockerServie.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import low.level.lockerServie.exception.PickupCodeExpiredException;

@Getter
@Setter
public class LockerPackage {
    final int codeValidDays = 3;
    private String lockerId;
    private String orderId;
    private String code;
    private LocalDateTime packageDeliveredTime;

    public boolean isValidCode(LocalDateTime currentTime) throws PickupCodeExpiredException {
        return false; // todo
    }
    
    public boolean verifyCode(String code) {
        return code.length() == 6 ? true : false;
    }
 }
