package low.level.lockerServie.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private String orderId;
    private List<Item> items;
    private GeoLocation deliveryGeoLocation;
}
