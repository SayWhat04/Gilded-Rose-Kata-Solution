package com.gildedrose.second.model.predicates.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class QualityBiggerThan implements Predicate<Item> {

    private final int value;

    public QualityBiggerThan(int value) {
        this.value = value;
    }

    @Override
    public boolean test(Item item) {
        return item.quality > value;
    }
}
