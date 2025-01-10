package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.repositories.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void updateAccount_givenAccountDoesNotExist_shouldThrowException() {
        var accountDto = new AccountDTO();
        when(accountRepository.findById(0)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> accountService.updateAccount(0, accountDto));
    }
    @Test
    void updateAccount_givenCorrectInput_AccountIsSaved() {
        var account = AccountBuilder.create().withEmail().withPassword("password").withUsername().withFullName().build();
        var accountDto = new AccountDTO(account);

        when(accountRepository.findById(0)).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(any(CharSequence.class), anyString())).thenReturn(true);

        accountService.updateAccount(0, accountDto);
        verify(accountRepository, times(1)).save(account);
    }

    // updateAccount_ verify that when a field is changed, the change is saved in the database

    // test that the password is updated when new password is supplied

    // test that the password is encoded before / when its saved

    // test invalid inputs (null, empty string, boundary values

    // what happens when the object returned from the database has invalid values like null
}