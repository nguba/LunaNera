package io.github.nguba.lunanera.domain;

public record Recipe(RecipeId id, String name, boolean snapshot, OriginalGravity originalGravity, ABV abv) {
}
