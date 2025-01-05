package com.musicreviewer.music_reviewer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
    
            // Update allowed fields
            existingAccount.getLogin().setEmail(updatedAccount.getEmail());
            existingAccount.getLogin().setPassword(
                // Check if the password is already hashed or not before hashing again
                passwordEncoder.matches(updatedAccount.getPassword(), existingAccount.getLogin().getPassword())
                    ? existingAccount.getLogin().getPassword() // Keep existing hashed password
                    : passwordEncoder.encode(updatedAccount.getPassword()) // Hash new password
            );
            existingAccount.getUser().setFullName(updatedAccount.getFullName());
            existingAccount.getUser().setUsername(updatedAccount.getUsername());
            existingAccount.setReviewsCreated(updatedAccount.getReviewsCreated());
    
            // Save and return updated account
            return accountRepository.save(existingAccount);
        } else {
            throw new IllegalArgumentException("Account with ID " + accountId + " not found.");
        }
    }    

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }
}