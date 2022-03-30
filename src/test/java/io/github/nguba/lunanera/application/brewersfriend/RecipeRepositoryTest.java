package io.github.nguba.lunanera.application.brewersfriend;

import io.github.nguba.lunanera.domain.Recipe;
import io.github.nguba.lunanera.infrastructure.brewersfriend.BrewersFriendClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {BrewersFriendClient.class})
@ActiveProfiles("test")
class RecipeRepositoryTest {

    @Autowired
    BrewersFriendClient client;

    RecipeRepository repository;

    @BeforeEach
    void setUp() {
        repository = new RecipeRepository(client);
    }

    @Test
    void findAll() {
        Collection<Recipe> recipes = repository.findAll();

        assertThat(recipes).asList().isNotEmpty();

        for(Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }
}