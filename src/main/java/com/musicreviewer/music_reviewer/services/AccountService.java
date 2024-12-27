package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    // Opret en ny Account
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    // // Hent alle Accounts med deres anmeldelsesantal
    // public List<AccountDTO> getAllAccountsWithReviewCounts() {
    //     List<Account> accounts = accountRepository.findAll();
    //     return accounts.stream()
    //             .map(account -> {
    //                 int reviewCount = account.getReviews() != null ? account.getReviews().size() : 0;
    //                 return new AccountDTO(account, reviewCount);
    //             })
    //             .toList();
    // }

    public AccountDTO getAccountDTOById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(AccountDTO::new).orElse(null);
    }

    // Update en Account med et givet ID
    public Account updateAccount(int accountId, Account updatedAccount) {
        // Find eksisterende Account i databasen via ID
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();

            // Opdater kun tilladte felter
            existingAccount.setCreationDate(updatedAccount.getCreationDate());
            existingAccount.setReviewsCreated(updatedAccount.getReviewsCreated());
            existingAccount.setUser(updatedAccount.getUser());
            existingAccount.setReviews(updatedAccount.getReviews());
            existingAccount.setLogin(updatedAccount.getLogin());

            // Gem ændringerne
            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found.");
        }
    }

    // Slet en Account
    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }
}