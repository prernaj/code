package low.level.carRental.model.vehicle;

import lombok.Getter;
import lombok.Setter;
import low.level.carRental.model.common.Address;
import low.level.carRental.model.common.Coordinates;

@Getter
@Setter
public class VehicleLocation {
    private String locationId;
    private Address address;
    private Coordinates coordinates;
}