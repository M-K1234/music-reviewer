package com.musicreviewer.music_reviewer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.services.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create: Opret en ny Account
    @PostMapping("/create")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        int reviewCount = createdAccount.getReviews() != null ? createdAccount.getReviews().size() : 0;
        AccountDTO responseDTO = new AccountDTO(createdAccount, reviewCount);
        return ResponseEntity.ok(responseDTO);
    }

    // Read: Hent alle Accounts med deres anmeldelsesantal
    @GetMapping("/all")
    public List<AccountDTO> getAllAccountsWithReviewCounts() {
        return accountService.getAllAccountsWithReviewCounts();
    }

    // Read: Hent en Account med anmeldelsesantal via ID
    @GetMapping("/read/{id}")
    public ResponseEntity<AccountDTO> getAccountWithReviewCountById(@PathVariable int id) {
        Optional<Account> account = accountService.getAccountById(id);
        if (account.isPresent()) {
            int reviewCount = account.get().getReviews() != null ? account.get().getReviews().size() : 0;
            AccountDTO responseDTO = new AccountDTO(account.get(), reviewCount);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update: Opdater en eksisterende Account
    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable int id, @RequestBody AccountDTO accountDTO) {
        Optional<Account> accountOptional = accountService.getAccountById(id);
        if (accountOptional.isPresent()) {
            Account updatedAccount = accountService.updateAccount(id, accountDTO);
            int reviewCount = updatedAccount.getReviews() != null ? updatedAccount.getReviews().size() : 0;
            AccountDTO responseDTO = new AccountDTO(updatedAccount, reviewCount);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete: Slet en Account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable int id) {
        if (accountService.getAccountById(id).isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
