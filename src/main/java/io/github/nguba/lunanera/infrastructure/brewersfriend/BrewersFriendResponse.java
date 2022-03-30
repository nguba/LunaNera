package io.github.nguba.lunanera.infrastructure.brewersfriend;

import java.util.List;

public abstract class BrewersFriendResponse<T> {
    protected int count;

    protected List<T> items;

    public int getCount() {
        return count;
    }

    public void setCount(final int count) {
        this.count = count;
    }

    public abstract List<T> getItems();

    public abstract void setItems(final List<T> items);
}
