package com.musicreviewer.music_reviewer.services;

import com.musicreviewer.music_reviewer.dtos.AccountDTO;
import com.musicreviewer.music_reviewer.entities.Account;
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
    void updateAccount_givenCorrectInput_accountIsSaved() {
        var account = AccountBuilder.create().withEmail().withPassword("password").withUsername().withFullName().build();
        var accountDto = new AccountDTO(account);

        when(accountRepository.findById(0)).thenReturn(Optional.of(account));
        when(passwordEncoder.matches(any(CharSequence.class), anyString())).thenReturn(true);

        accountService.updateAccount(0, accountDto);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void updateAccount_givenFieldChanged_shouldSaveUpdatedValues() {

        //Arrange

        var existingAccount = AccountBuilder.create()
                .withId(1)
                .withEmail("old@example.com")
                .withPassword("oldHashedPassword")
                .withFullName()
                .withUsername()
                .withReviewsCreated(5)
                .build();

        var expected = new AccountDTO();
        expected.setId(1);
        expected.setEmail("new@example.com");
        expected.setPassword("newCleartextPassword");
        expected.setFullName("New Full Name");
        expected.setUsername("newUsername");
        expected.setReviewsCreated(10);

        final var newHashedPassword = "newHashedPassword";

        when(accountRepository.findById(1)).thenReturn(Optional.of(existingAccount));
        when(passwordEncoder.matches(expected.getPassword(), existingAccount.getLogin().getPassword())).thenReturn(false);
        when(passwordEncoder.encode(expected.getPassword())).thenReturn(newHashedPassword);

        // Act

        accountService.updateAccount(1, expected);

        // Assert

        verify(accountRepository).save(matchesExpected(expected, newHashedPassword));
    }

    private static Account matchesExpected(AccountDTO expected, String newHashedPassword) {
        return argThat(actual ->
                actual.getLogin().getEmail().equals(expected.getEmail())
                        && actual.getLogin().getPassword().equals(newHashedPassword)
                        && actual.getUser().getFullName().equals(expected.getFullName())
                        && actual.getUser().getUsername().equals(expected.getUsername())
                        && actual.getReviewsCreated() == expected.getReviewsCreated()
        );
    }
}