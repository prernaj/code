package low.level.splitwise.service;

import low.level.splitwise.model.Expense;
import low.level.splitwise.model.User;

public interface NotificationService {
    void notifyUser(User user, Expense expense);
}
