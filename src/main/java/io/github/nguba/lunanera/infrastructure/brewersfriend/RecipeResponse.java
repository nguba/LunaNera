package io.github.nguba.lunanera.infrastructure.brewersfriend;

import java.util.Objects;

public record RecipeResponse(int id, String title, int snapshot, float og, float abv) {

//    @Override
//    public boolean equals(final Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        final RecipeResponse that = (RecipeResponse) o;
//        return id == that.id;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
}
