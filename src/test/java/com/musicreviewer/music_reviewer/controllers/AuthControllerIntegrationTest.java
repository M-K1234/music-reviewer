package com.musicreviewer.music_reviewer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.musicreviewer.music_reviewer.MusicReviewerApplication;
import com.musicreviewer.music_reviewer.config.FakerTestConfig;
import com.musicreviewer.music_reviewer.dtos.RegistrationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MusicReviewerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Import(FakerTestConfig.class)
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    Faker faker = new Faker();

    private static final String DATA_PROVIDER_PATH = "com.musicreviewer.music_reviewer.controllers.TestDataProvider";

    @Test
    void register_givenEmptyRequestBody_returnBadRequest() throws Exception {
        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_givenValidRequestBody_returnOk() throws Exception {
        var registrationBody = new RegistrationDTO(faker.name().nameWithMiddle(), faker.internet().emailAddress(), username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void register_givenFullNameIsNullOrEmpty_returnBadRequest(String input) throws Exception {
        var registrationBody = new RegistrationDTO(input, faker.internet().emailAddress(), username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource(value = DATA_PROVIDER_PATH + "#invalidFullNames")
    void register_givenFullNameContainsInvalidCharacter_returnBadRequest(String input) throws Exception {
        var registrationBody = new RegistrationDTO(input, faker.internet().emailAddress(), username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fullName", startsWith("must match")));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "J",
            "Jane LastnameOneCharacterTooManyAAAAAAAAAAAAAAAAAAA",  // 51 characters
            "Jane LastnameTwoCharacterTooManyAAAAAAAAAAAAAAAAAAAA"  // 52 characters
    })
    void register_givenFullNameHasInvalidLength_returnBadRequest(String input) throws Exception {
        var registrationBody = new RegistrationDTO(input, faker.internet().emailAddress(), username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fullName", startsWith("size must be between")));
    }

    @ParameterizedTest
    @MethodSource(value = DATA_PROVIDER_PATH + "#validEmailAddresses")
    void register_givenEmailIsValid_returnOk(String email) throws Exception {
        var registrationBody = new RegistrationDTO(faker.name().nameWithMiddle(), email, username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @MethodSource(value = DATA_PROVIDER_PATH + "#invalidEmailAddresses")
    void register_givenEmailContainsInvalidCharacter_returnBadRequest(String email) throws Exception {
        var registrationBody = new RegistrationDTO(faker.name().nameWithMiddle(), email, username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email", startsWith("must be a well-formed email address")));
    }

    @Disabled("Bug reported, test ignored until fixed")
    @ParameterizedTest
    @ValueSource(strings = {
            "d@k.dk",                                                                           // Local part 1 char
            "Jfjhkljukkjjhbghfgjdfjnvnhvhvhnvhfjdkdfjhfghghdjcnvhfgjdjxjhvhgh@example.com"      // Local part 64 char
    })
    void register_givenEmailHasValidLength_returnOk(String email) throws Exception {
        var registrationBody = new RegistrationDTO(faker.name().nameWithMiddle(), email, username(), faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isOk());
    }

    private String username() {
        // Generate slug and ensure that it fits the username field requirements
        var slug = faker.internet().slug();
        slug = slug.replace('.', '_');
        if (slug.length() < 3) {
            slug += "xxx".substring(0, 3 - slug.length());
        }
        if (slug.length() > 20) {
            slug = slug.substring(0, 20);
        }
        return slug;
    }
}
