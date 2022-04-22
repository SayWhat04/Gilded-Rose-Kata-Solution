package com.gildedrose.second.model.updaters.quality;

import com.gildedrose.Item;

import java.util.function.Function;

public class SetQuality implements Function<Item, Void> {

    private final int amount;

    public SetQuality(int amount) {
        this.amount = amount;
    }

    @Override
    public Void apply(Item item) {
        item.quality = amount;
        return null;
    }
}
