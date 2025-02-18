package com.musicreviewer.music_reviewer.controllers;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
import com.musicreviewer.music_reviewer.security.JwtUtil;
import com.musicreviewer.music_reviewer.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;
    @Autowired
    JwtUtil jwtUtil;

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
    public ResponseEntity<Map<String, Object>> updateAccount(@PathVariable int id, @Valid @RequestBody AccountDTO updatedAccount) {
        System.out.println("Received update request for ID: " + id);
        System.out.println("Updated Account Details: " + updatedAccount);

        Account savedAccount = accountService.updateAccount(id, updatedAccount);

        // Generate a new JWT with the updated email
        String newJwtToken = jwtUtil.generateToken(savedAccount.getLogin().getEmail());

        // Return updated account details and the new token
        Map<String, Object> response = Map.of(
                "account", new AccountDTO(savedAccount),
                "token", newJwtToken
        );

        return ResponseEntity.ok(response);
    }
}