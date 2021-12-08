package low.level.splitwise.service;

import low.level.splitwise.exception.ContributionExceededException;
import low.level.splitwise.exception.ExpenseSettledException;
import low.level.splitwise.exception.InvalidExpenseException;
import low.level.splitwise.model.Contribution;
import low.level.splitwise.model.Expense;
import low.level.splitwise.model.ExpenseGroup;
import low.level.splitwise.model.ExpenseStatus;
import low.level.splitwise.model.User;
import low.level.splitwise.model.UserShare;
import low.level.splitwise.repository.ExpenseRepository;
import low.level.splitwise.repository.UserRepository;

public class UserService {
    public User createUser(String emailId, String name, String phoneNumber) {
        User user = new User(emailId, name, phoneNumber);
        UserRepository.userHashMap.putIfAbsent(emailId, user);
        return user;
    }

    public void contributeToExpense(String expenseId, String emailId, Contribution contribution) throws InvalidExpenseException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseException("Invalid expense state");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expene is alrady settled");
        }
        UserShare userShare = expenseGroup.getUserContributions().get(emailId);
        if (contribution.getContributionValue() > userShare.getShare()) {
            throw new ContributionExceededException(String.format("User %s contriution %d exceeded the share %d", emailId, contribution.getContributionValue(), userShare.getShare()));
        }
        userShare.getContributions().add(contribution);
    }

    public void contributeToExpense(String expenseId, String emailId, String toEmailId, Contribution contribution) throws InvalidExpenseException, ExpenseSettledException, ContributionExceededException {
        Expense expense = ExpenseRepository.expenseMap.get(expenseId);
        ExpenseGroup expenseGroup = expense.getExpenseGroup();
        if (expense.getExpenseStatus() == ExpenseStatus.CREATED) {
            throw new InvalidExpenseException("Invalid expense State");
        }
        if (expense.getExpenseStatus() == ExpenseStatus.SETTLED) {
            throw new ExpenseSettledException("Expense is already settled.");
        }
        UserShare userShare = expenseGroup.getUserContributions().get(emailId);
        if (contribution.getContributionValue() > userShare.getShare()) {
            throw new ContributionExceededException(
                    String.format("User %s contribution %d exceeded the share %d",
                            emailId, contribution.getContributionValue(), userShare.getShare()));
        }
        userShare.getContributions().add(contribution);
    }
}
