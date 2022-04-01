package io.github.nguba.lunanera.infrastructure.brewersfriend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.util.Collection;

public class BrewersFriendClient {

    public BrewersFriendClient(@Value("${brewersfriend.api.key}") final String apiKey) {
        this.apiKey = apiKey;
    }

    private final String apiKey;

    WebClient client = WebClient.create("https://api.brewersfriend.com/v1/");

    public Collection<RecipeResponse> findAllRecipes() {
        Mono<RecipesResponse> response = client.get().uri("/recipes")
                .header("X-API-Key", apiKey)
                .retrieve().bodyToMono(RecipesResponse.class);

        return response.block().getItems();
    }

    public Collection<BrewSessionResponse> getBrewSessions() {
        Mono<BrewSessionsResponse> response = client.get().uri("/brewsessions")
                .header("X-API-Key", apiKey)
                .retrieve().bodyToMono(BrewSessionsResponse.class);

        return response.block().getItems();
    }

    public void submit(final FermentationEntry entry) {
        WebClient.create("https://log.brewersfriend.com/stream/" + apiKey).post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(entry), FermentationEntry.class)
                .exchangeToMono(response -> {
                    return response.bodyToMono(String.class);
                }).block();
    }

    public RecipeResponse getRecipe(final int id) throws FileNotFoundException {
        Mono<RecipesResponse> response = client.get().uri("/recipes/" + id)
                .header("X-API-Key", apiKey)
                .retrieve().bodyToMono(RecipesResponse.class);
        try {
            return response.block().getRecipe();
        } catch (WebClientResponseException clientError) {
            if (clientError.getStatusCode().is4xxClientError()) {
                throw new FileNotFoundException(clientError.getMessage());
            }
            throw clientError;
        }
    }
}
