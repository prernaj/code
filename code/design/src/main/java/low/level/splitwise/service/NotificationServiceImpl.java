package low.level.splitwise.service;

import low.level.splitwise.model.Expense;
import low.level.splitwise.model.User;

public class NotificationServiceImpl implements NotificationService {

    @Override
    public void notifyUser(User user, Expense expense) {
        System.out.println("notify message");
    }
    
}
