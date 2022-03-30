package io.github.nguba.lunanera.application.brewersfriend;

import io.github.nguba.lunanera.domain.ABV;
import io.github.nguba.lunanera.domain.OriginalGravity;
import io.github.nguba.lunanera.domain.Recipe;
import io.github.nguba.lunanera.domain.RecipeId;
import io.github.nguba.lunanera.infrastructure.brewersfriend.BrewersFriendClient;
import io.github.nguba.lunanera.infrastructure.brewersfriend.RecipeResponse;

import java.util.*;

public record RecipeRepository(BrewersFriendClient client) {

    public RecipeRepository(final BrewersFriendClient client) {
        this.client = client;
        System.out.println(client);
    }

    public Collection<Recipe> findAll() {
        Collection<RecipeResponse> recipeResponses = client.getRecipes();
        List<Recipe> recipes = new ArrayList<>(recipeResponses.size());
        for (RecipeResponse response : recipeResponses) {
            RecipeId id = new RecipeId(response.id());
            ABV abv = new ABV(response.abv());
            OriginalGravity og = new OriginalGravity(response.og());
            boolean snapshot = response.snapshot() > 0 ? true : false;
            Recipe recipe = new Recipe(id, response.title(), snapshot, og, abv);
            recipes.add(recipe);
        }
        return recipes;
    }
}
