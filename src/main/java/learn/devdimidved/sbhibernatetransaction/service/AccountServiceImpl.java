package learn.devdimidved.sbhibernatetransaction.service;

import learn.devdimidved.sbhibernatetransaction.model.Account;
import learn.devdimidved.sbhibernatetransaction.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) {
        changeBalance(fromAccountId, -amount);
        changeBalance(toAccountId, amount);
    }

    private void changeBalance(Long accountId, double amount) {
        Account account = getAccountById(accountId);
        if (account == null) {
            log.warn("Account Id={} not found", accountId);
            throw new AccountTransactionException("Account " + accountId + " does not exist!");
        }
        double newBalance = account.getBalance() + amount;
        if (newBalance < 0) {
            log.warn("Account Id={} does not have enough money: requested {}, but actual {}",
                    accountId, amount, account.getBalance());
            throw new AccountTransactionException("Account " + accountId + " does not have enough money!");
        }
        account.setBalance(newBalance);
    }
}
