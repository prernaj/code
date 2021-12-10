package low.level.carRental.model.account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends Account {
    private LicenseInfo licenseInfo;
}
