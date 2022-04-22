package com.gildedrose.second.model.updaters.quality;

import com.gildedrose.Item;

import java.util.function.Function;

public class IncreasingQuality implements Function<Item, Void> {

    private final int amount;

    public IncreasingQuality(int amount) {
        this.amount = amount;
    }

    @Override
    public Void apply(Item item) {
        item.quality = item.quality + amount;
        return null;
    }
}
