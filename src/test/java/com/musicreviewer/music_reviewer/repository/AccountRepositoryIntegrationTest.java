// package com.musicreviewer.music_reviewer.repository;

// import com.musicreviewer.music_reviewer.entities.Account;
// import com.musicreviewer.music_reviewer.entities.Login;
// import com.musicreviewer.music_reviewer.entities.User;
// import com.musicreviewer.music_reviewer.repositories.AccountRepository;

// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.transaction.annotation.Transactional;

// import static org.junit.jupiter.api.Assertions.*;

// import java.time.LocalDateTime;

// @SpringBootTest
// @ActiveProfiles("test")  // Use the test profile with application-integrationtest.properties
// @Transactional           // Ensure changes to the database are rolled back after each test
// public class AccountRepositoryIntegrationTest {

//     @Autowired
//     private AccountRepository accountRepository;

//     @Test
//     void testFindByLoginEmail() {
//         // Arrange
//         Login login = new Login(0, "test@example.com", "password");
//         User user = new User(0, "Test User", "testuser");
//         Account account = new Account(0, LocalDateTime.now(), 0, user, login);
//         accountRepository.save(account);

//         // Act
//         var result = accountRepository.findByLoginEmail("test@example.com");

//         // Assert
//         assertTrue(result.isPresent());
//         assertEquals("test@example.com", result.get().getLogin().getEmail());
//     }

//     @Test
//     void testSaveAndRetrieveAccount() {
//         // Arrange
//         Login login = new Login(0, "jane.doe@example.com", "password");
//         User user = new User(0, "Jane Doe", "janedoe");
//         Account account = new Account(0, LocalDateTime.now(), 0, user, login);
//         accountRepository.save(account);

//         // Act
//         var retrievedAccount = accountRepository.findById(account.getId());

//         // Assert
//         assertTrue(retrievedAccount.isPresent());
//         assertEquals("Jane Doe", retrievedAccount.get().getUser().getFullName());
//     }
// }