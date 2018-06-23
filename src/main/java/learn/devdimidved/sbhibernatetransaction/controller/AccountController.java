package learn.devdimidved.sbhibernatetransaction.controller;

import learn.devdimidved.sbhibernatetransaction.dto.SendMoneyDto;
import learn.devdimidved.sbhibernatetransaction.service.AccountService;
import learn.devdimidved.sbhibernatetransaction.service.AccountTransactionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String showAllAccounts(Model model) {
        model.addAttribute("accounts", accountService.getAllAccounts());
        return "accounts";
    }

    @GetMapping("/sendMoney")
    public String showSendMoney(Model model) {
        model.addAttribute("sendMoneyDto", new SendMoneyDto());
        return "sendMoney";
    }

    @PostMapping("/sendMoney")
    public String handleSendMoney(Model model, SendMoneyDto sendMoneyDto) {
        Long fromAccountId = sendMoneyDto.getFromAccountId();
        Long toAccountId = sendMoneyDto.getToAccountId();
        double amount = sendMoneyDto.getAmount();
        log.info("Got request for transaction: from {}, to {}, amount {}", fromAccountId, toAccountId, amount);
        try {
            accountService.sendMoney(fromAccountId, toAccountId, amount);
        } catch (AccountTransactionException ate) {
            model.addAttribute("errorMessage", "Error: " + ate.getMessage());
            return "/sendMoney";
        }
        return "redirect:/";
    }
}
