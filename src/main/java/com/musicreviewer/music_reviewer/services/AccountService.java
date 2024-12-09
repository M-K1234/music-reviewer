package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Opret en ny Account
    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setCreation_date(accountDTO.getCreation_date());
        account.setReviews_created(accountDTO.getReviews_created());
        account.setUser_id_user(accountDTO.getUser_id_user());
        account.setLogin_id_login(accountDTO.getLogin_id_login());
        return accountRepository.save(account);
    }

    // Hent alle Accounts med deres anmeldelsesantal
    public List<AccountDTO> getAllAccountsWithReviewCounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(account -> {
                    int reviewCount = account.getReviews() != null ? account.getReviews().size() : 0;
                    return new AccountDTO(account, reviewCount);
                })
                .toList();
    }

    // Hent en Account med anmeldelsesantal via ID
    public Optional<Account> getAccountById(int accountId) {
        return accountRepository.findById(accountId);
    }

    // Opdater en eksisterende Account
    public Account updateAccount(int accountId, AccountDTO accountDTO) {
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();
            existingAccount.setCreation_date(accountDTO.getCreation_date());
            existingAccount.setReviews_created(accountDTO.getReviews_created());
            existingAccount.setUser_id_user(accountDTO.getUser_id_user());
            existingAccount.setLogin_id_login(accountDTO.getLogin_id_login());
            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account not found for update.");
        }
    }

    // Slet en Account
    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }
}
