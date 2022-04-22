package com.gildedrose.second.model.updaters.sellin;

import com.gildedrose.Item;

import java.util.function.Function;

public class DecreasingSellIn implements Function<Item, Void> {

    private final int amount;

    public DecreasingSellIn(int amount) {
        this.amount = amount;
    }


    @Override
    public Void apply(Item item) {
        item.sellIn = item.sellIn - amount;
        return null;
    }
}
