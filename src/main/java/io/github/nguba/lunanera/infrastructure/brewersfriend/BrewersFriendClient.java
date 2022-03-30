package io.github.nguba.lunanera.infrastructure.brewersfriend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

public class BrewersFriendClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrewersFriendClient.class);

    private final RestTemplate rest = new RestTemplate();

    private final URI baseUri = URI.create("https://api.brewersfriend.com/v1/");

    public BrewersFriendClient(@Value("${brewersfriend.api.key}") final String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;

    public HttpEntity<Object> makeRequestEntity() {
        return makeRequestEntity(null);
    }

    public HttpEntity<Object> makeRequestEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-Key", apiKey);
        return new HttpEntity<Object>(body, headers);
    }

    public Collection<RecipeResponse> getRecipes() {
        URI uri = UriComponentsBuilder.fromUri(baseUri)
                .path("recipes").queryParam("snapshots", false).build().toUri();
        ResponseEntity<RecipesResponse> recipes =
                rest.exchange(uri, HttpMethod.GET, makeRequestEntity(), RecipesResponse.class);

        LOGGER.debug("{}", recipes.getBody());

        return recipes.getBody().getItems();
    }

    public Collection<BrewSessionResponse> getBrewSessions() {
        URI uri = UriComponentsBuilder.fromUri(baseUri).path("brewsessions").build().toUri();
        ResponseEntity<BrewSessionsResponse> response =
                rest.exchange(uri, HttpMethod.GET, makeRequestEntity(), BrewSessionsResponse.class);

        LOGGER.debug("{}", response.getBody());

        return response.getBody().getItems();
    }

    public void submit(final FermentationEntry entry) {
        HttpEntity<Object> requestEntity = makeRequestEntity(entry);

        ResponseEntity<Void> response =
                rest.exchange("https://log.brewersfriend.com/stream/" + apiKey, HttpMethod.POST, requestEntity, Void.class);

        LOGGER.debug("STREAM: {}", response.getBody());
    }
}
