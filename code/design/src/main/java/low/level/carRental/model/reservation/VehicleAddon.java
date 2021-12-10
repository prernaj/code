package low.level.carRental.model.reservation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class VehicleAddon {
    private String id;
    private String name;
    private String description;
    private double cost;
}
