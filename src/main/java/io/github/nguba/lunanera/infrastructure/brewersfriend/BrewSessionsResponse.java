package io.github.nguba.lunanera.infrastructure.brewersfriend;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.StringJoiner;

public class BrewSessionsResponse extends BrewersFriendResponse<BrewSessionResponse> {

    @Override
    public String toString() {
        return new StringJoiner(", ", BrewSessionsResponse.class.getSimpleName() + "[", "]")
                .add("count=" + count)
                .add("brewsessions=" + items)
                .toString();
    }

    @JsonProperty("brewsessions")
    @Override
    public List<BrewSessionResponse> getItems() {
        return items;
    }

    @Override
    public void setItems(final List<BrewSessionResponse> items) {
        this.items = items;
    }

    public BrewSessionResponse getFirst() {
        return items.get(0);
    }
}
