package com.gildedrose.second.model.predicates.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class QualityEquals implements Predicate<Item> {

    private final int value;

    public QualityEquals(int value) {
        this.value = value;
    }

    @Override
    public boolean test(Item item) {
        return item.quality == value;
    }
}
