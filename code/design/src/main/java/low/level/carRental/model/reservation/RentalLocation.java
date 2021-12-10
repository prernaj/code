package low.level.carRental.model.reservation;

import lombok.Getter;
import lombok.Setter;
import low.level.carRental.model.common.Address;
import low.level.carRental.model.common.Coordinates;

@Getter
@Setter
public class RentalLocation {
    private String id;
    private Address address;
    private Coordinates coordinates;
}
