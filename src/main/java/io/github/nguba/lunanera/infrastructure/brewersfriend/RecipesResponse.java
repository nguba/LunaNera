package io.github.nguba.lunanera.infrastructure.brewersfriend;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

public class RecipesResponse extends BrewersFriendResponse<RecipeResponse>{

    @JsonProperty("recipes")
    @Override
    public List<RecipeResponse> getItems() {
        return items;
    }

    @Override
    public void setItems(final List<RecipeResponse> items) {
        this.items = items;
    }

    public RecipeResponse getRecipe() {
        return items.get(0);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RecipesResponse.class.getSimpleName() + "[", "]")
                .add("count=" + count)
                .add("recipes=" + items)
                .toString();
    }
}
