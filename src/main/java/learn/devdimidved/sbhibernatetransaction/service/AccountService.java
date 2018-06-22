package learn.devdimidved.sbhibernatetransaction.service;

import learn.devdimidved.sbhibernatetransaction.model.Account;

import java.util.List;

public interface AccountService {
    Account getAccountById(Long id);
    List<Account> getAllAccounts();
    void sendMoney(Long fromAccountId, Long toAccountId, double amount);
}
