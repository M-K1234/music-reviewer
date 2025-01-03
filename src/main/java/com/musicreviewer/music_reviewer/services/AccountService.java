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

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public AccountDTO getAccountDTOByEmail(String email) {
        Optional<Account> account = accountRepository.findByLoginEmail(email);
        return account.map(AccountDTO::new).orElse(null);
    }

    public AccountDTO getAccountDTOById(int id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.map(AccountDTO::new).orElse(null);
    }

    public Account updateAccount(int accountId, AccountDTO updatedAccount) {
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);
    
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();

            existingAccount.getLogin().setEmail(updatedAccount.getEmail());
            existingAccount.getLogin().setPassword(updatedAccount.getPassword());
            existingAccount.getUser().setFullName(updatedAccount.getFullName());
            existingAccount.getUser().setUsername(updatedAccount.getUsername());
            existingAccount.setReviewsCreated(updatedAccount.getReviewsCreated());

            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found.");
        }
    }    

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }
}