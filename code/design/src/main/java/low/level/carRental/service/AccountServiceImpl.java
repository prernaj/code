package low.level.carRental.service;

import low.level.carRental.exception.AccountDoesNotExistsException;
import low.level.carRental.model.account.Account;
import low.level.carRental.model.account.AccountType;
import low.level.carRental.repository.AccountRepository;
import low.level.carRental.repository.AccountRepositoryFactory;

public class AccountServiceImpl implements AccountService {

    @Override
    public Account createAccount(Account account, AccountType accountType) {
        AccountRepository accountRepository = AccountRepositoryFactory.getAccountRepository(accountType);
        return accountRepository.createAccount(account);
    }

    public void resetPassword(String userId, String password, AccountType accountType) throws AccountDoesNotExistsException {
        AccountRepository accountRepository = AccountRepositoryFactory.getAccountRepository(accountType);
        accountRepository.resetPassword(userId, password);
    }
}