package low.level.carRental.repository;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import low.level.carRental.model.account.Account;

public class DriverRepository implements AccountRepository {
    public static Map<String, Driver> driverMap = new HashMap<>();
    public static List<Driver> drivers = new ArrayList<>();

    public Account createAccount(Account account) {
        driverMap.putIfAbsent(account.getEmail(), (Driver) account);
        return account;
    }

    public void resetPassword(String userId, String password) {

    }
}
