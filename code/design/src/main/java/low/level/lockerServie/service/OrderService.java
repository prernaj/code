package low.level.lockerServie.service;

import java.util.List;

import low.level.lockerServie.model.Item;
import low.level.lockerServie.model.Order;
import low.level.lockerServie.repository.OrderRepository;

public class OrderService {
    OrderRepository orderRepository = new OrderRepository();

    public Order getOrder(String orderId) {
        return orderRepository.getOrder(orderId);
    }

    public List<Item> getItemsForOrder(String orderId) {
        return orderRepository.getOrder(orderId).getItems();
    }

    public void initiateRefund(String orderId) {
        System.out.printf("Refund for order %s initiated", orderId);
    }
    
}
