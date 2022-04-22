package com.gildedrose.second.model.predicates.sellin;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class SellInLesserThan implements Predicate<Item> {

    private final int value;

    public SellInLesserThan(int value) {
        this.value = value;
    }

    @Override
    public boolean test(Item item) {
        return item.sellIn < value;
    }
}
