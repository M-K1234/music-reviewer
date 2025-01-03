package com.musicreviewer.music_reviewer.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.musicreviewer.music_reviewer.MusicReviewerApplication;
import com.musicreviewer.music_reviewer.config.FakerTestConfig;
import com.musicreviewer.music_reviewer.dtos.RegistrationDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void register_givenEmptyRequestBody_returnBadRequest() throws Exception {
        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_givenValidRequestBody_returnOk() throws Exception {
        var registrationBody = new RegistrationDTO(faker.name().nameWithMiddle(), faker.internet().emailAddress(), "user1", faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "@Jane Doe",        // @ at start
            "Jane@ Doe",        // @ in middle
            "Jane Doe#",        // # at end
            "Jane! Doe",        // ! in middle
            "Jane,Doe",         // , comma
            "Jane Doe;",        // ; semicolon
            "Jane:Doe",         // : colon
            "Jane?Doe",         // ? question mark
            "Jane=Doe",         // = equal sign
            "Jane%Doe",         // % percent
            "Jane^Doe",         // ^ caret
            "Jane&Doe",         // & ampersand
            "Jane*Doe",         // * asterisk
            "Jane(Doe)",        // () parentheses
            "Jane+Doe",         // + plus
            "Jane{Doe}",        // {} curly braces
            "Jane[Doe]",        // [] square brackets
            "Jane|Doe",         // | pipe
            "Jane\\Doe",        // \ backslash
            "Jane<Doe>",        // <> angle brackets
            "Jane~Doe",         // ~ tilde
            "Jane`Doe",         // ` backtick
            "Jane\"Doe\"",      // " double quotes
            ""                  // empty string
    })
    void register_givenFullNameContainsInvalidCharacter_returnBadRequest(String input) throws Exception {
        var registrationBody = new RegistrationDTO(input, faker.internet().emailAddress(), "user1", faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fullName", startsWith("must match")));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "J",
            "Jane DoeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeE",
            "Jane DoeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeEE"})
    void register_givenFullNameHasInvalidLength_returnBadRequest(String input) throws Exception {
        var registrationBody = new RegistrationDTO(input, faker.internet().emailAddress(), "user1", faker.internet().password());

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationBody)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fullName", startsWith("size must be between")));
    }
}