package low.level.carRental.model.account;

import lombok.Getter;
import lombok.Setter;
import low.level.carRental.model.common.Address;

@Getter
@Setter
public class Contact {
    private String phone;
    private String email;
    private Address address;
    private PersonalInfo personalInfo;
}
