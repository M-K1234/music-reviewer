package com.musicreviewer.music_reviewer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.jwt.Jwt;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.services.AccountService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable int id) {
        AccountDTO accountDTO = accountService.getAccountDTOById(id);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AccountDTO> getAccountByEmail(@PathVariable String email) {
        AccountDTO accountDTO = accountService.getAccountDTOByEmail(email);
        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<AccountDTO> getCurrentUserAccount(@AuthenticationPrincipal UserDetails userDetails) {

        String email = userDetails.getUsername(); // Assuming username is the email

        System.out.println("Email from UserDetails: " + email);

        AccountDTO accountDTO = accountService.getAccountDTOByEmail(email);

        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountDTO> updateAccount(@PathVariable int id, @Valid @RequestBody AccountDTO updatedAccount) {
        System.out.println("Received update request for ID: " + id);
        System.out.println("Updated Account Details: " + updatedAccount);
        Account savedAccount = accountService.updateAccount(id, updatedAccount);
        return ResponseEntity.ok(new AccountDTO(savedAccount));
    }
}