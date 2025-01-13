// package com.musicreviewer.music_reviewer.controllers;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.github.javafaker.Faker;
// import com.musicreviewer.music_reviewer.MusicReviewerApplication;
// import com.musicreviewer.music_reviewer.dtos.RegistrationDTO;
// import com.musicreviewer.music_reviewer.repositories.AccountRepository;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.http.MediaType;
// import org.springframework.test.context.ActiveProfiles;
// import org.springframework.test.web.servlet.MockMvc;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
// import static org.junit.jupiter.api.Assertions.assertTrue;

// @SpringBootTest(
//         webEnvironment = SpringBootTest.WebEnvironment.MOCK,
//         classes = MusicReviewerApplication.class)
// @AutoConfigureMockMvc
// @ActiveProfiles("test")
// class RegistrationIntegrationTest {

//     @Autowired
//     private MockMvc mvc;

//     @Autowired
//     private ObjectMapper objectMapper;

//     @Autowired
//     private AccountRepository accountRepository;

//     @Autowired
//     private Faker faker;

//     @Test
//     void register_givenValidData_shouldReturnOkAndSaveAccount() throws Exception {
//         // Arrange
//         var registrationBody = new RegistrationDTO(
//                 faker.name().fullName(),
//                 faker.internet().emailAddress(),
//                 faker.name().username(),
//                 faker.internet().password()
//         );

//         // Act
//         mvc.perform(post("/auth/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(registrationBody)))
//                 .andExpect(status().isOk());

//         // Assert
//         var account = accountRepository.findByLoginEmail(registrationBody.getEmail());
//         assertTrue(account.isPresent());
//         assertTrue(account.get().getLogin().getEmail().equals(registrationBody.getEmail()));
//     }

//     @Test
//     void register_givenDuplicateEmail_shouldReturnBadRequest() throws Exception {
//         // Arrange
//         String email = faker.internet().emailAddress();

//         var firstRegistrationBody = new RegistrationDTO(
//                 faker.name().fullName(),
//                 email,
//                 faker.name().username(),
//                 faker.internet().password()
//         );

//         var duplicateRegistrationBody = new RegistrationDTO(
//                 faker.name().fullName(),
//                 email, // Same email as above
//                 faker.name().username(),
//                 faker.internet().password()
//         );

//         mvc.perform(post("/auth/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(firstRegistrationBody)))
//                 .andExpect(status().isOk());

//         // Act
//         mvc.perform(post("/auth/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(duplicateRegistrationBody)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$.error").value("Email already exists."));
//     }

//     @Test
//     void register_givenInvalidEmail_shouldReturnBadRequest() throws Exception {
//         // Arrange
//         var registrationBody = new RegistrationDTO(
//                 faker.name().fullName(),
//                 "invalid-email", // Invalid email format
//                 faker.name().username(),
//                 faker.internet().password()
//         );

//         // Act & Assert
//         mvc.perform(post("/auth/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(registrationBody)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$.email").value("must be a well-formed email address"));
//     }

//     @Test
//     void register_givenShortPassword_shouldReturnBadRequest() throws Exception {
//         // Arrange
//         var registrationBody = new RegistrationDTO(
//                 faker.name().fullName(),
//                 faker.internet().emailAddress(),
//                 faker.name().username(),
//                 "123" // Password too short
//         );

//         // Act & Assert
//         mvc.perform(post("/auth/register")
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content(objectMapper.writeValueAsString(registrationBody)))
//                 .andExpect(status().isBadRequest())
//                 .andExpect(jsonPath("$.password").value("size must be between 6 and 64"));
//     }
// }