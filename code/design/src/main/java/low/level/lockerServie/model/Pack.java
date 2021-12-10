package low.level.lockerServie.model;

import java.util.List;
import java.util.UUID;

import lombok.Getter;

@Getter
public class Pack {
    private String id;
    private double packageSize;
    private String orderId;
    private List<Item> items;

    public Pack(String orderId, List<Item> items) {
        this.id = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.items = items;
        pack();
    }

    private void pack() {
        packageSize = Math.random()*10;
    }
    
}

/**
 * Best fit mapping packet and order items for space efficiency.
 */
