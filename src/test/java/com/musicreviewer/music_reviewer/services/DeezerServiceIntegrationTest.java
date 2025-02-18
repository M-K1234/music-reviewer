package com.musicreviewer.music_reviewer.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musicreviewer.music_reviewer.MusicReviewerApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = MusicReviewerApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class DeezerServiceIntegrationTest {

    private static final String TEST_URL = "http://localhost:";

    @LocalServerPort
    private int port;

    private static final String CHARTS_ALBUMS_PATH = "/api/charts/albums";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper mapper;

    @Test
    void whenFetchingTopAlbums_shouldReturnOk() {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + CHARTS_ALBUMS_PATH, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() != null && !response.getBody().isEmpty());
    }

    @Test
    void whenFetchingTopAlbums_shouldReturnTenAlbums() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + CHARTS_ALBUMS_PATH, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        JsonNode node = mapper.readTree(response.getBody());
        assertEquals(10, node.get("data").size());
    }

    @Test
    void fetchTopAlbums_invalidChartId_returnForbidden() {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + "/api/charts/-1", String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @ParameterizedTest
    @ValueSource(strings = {"id", "title", "link", "cover_small"})
    void fetchTopAlbums_containsRequiredFields(String fieldName) throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + CHARTS_ALBUMS_PATH, String.class);
        JsonNode firstAlbum = mapper.readTree(response.getBody());
        assertTrue(firstAlbum.get("data").get(0).has(fieldName));
    }

    @Test
    void fetchTopAlbums_eachAlbumContainsArtist() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + CHARTS_ALBUMS_PATH, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.path("data");
        for (JsonNode album : data) {
            JsonNode artist = album.path("artist");
            assertFalse(artist.isMissingNode(), "Missing 'artist' object");
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "link"})
    void fetchTopAlbums_eachAlbumArtistContainsRequiredFields(String fieldName) throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(TEST_URL + port + CHARTS_ALBUMS_PATH, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode data = root.path("data");
        for (JsonNode album : data) {
            JsonNode artist = album.path("artist");
            assertTrue(artist.has(fieldName), "Missing '" + fieldName + "' in artist");
        }
    }
}