package low.level.carRental.service;

import low.level.carRental.exception.AccountDoesNotExistsException;
import low.level.carRental.model.account.Account;
import low.level.carRental.model.account.AccountType;

public interface AccountService {
    Account createAccount(Account account, AccountType accountType);

    void resetPassword(String userId, String password, AccountType accountType) throws AccountDoesNotExistsException;
}
