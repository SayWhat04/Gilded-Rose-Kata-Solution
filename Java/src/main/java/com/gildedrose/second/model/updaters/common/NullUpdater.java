package com.gildedrose.second.model.updaters.common;

import com.gildedrose.Item;

import java.util.function.Function;

public class NullUpdater implements Function<Item, Void> {

    public NullUpdater() {
    }

    @Override
    public Void apply(Item item) {
        return null;
    }
}
