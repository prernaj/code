package low.level.carRental.repository;

import low.level.carRental.exception.AccountDoesNotExistsException;
import low.level.carRental.model.account.Account;

public interface AccountRepository {
    Account createAccount(Account account);

    void resetPassword(String userId, String password) throws AccountDoesNotExistsException;
}
