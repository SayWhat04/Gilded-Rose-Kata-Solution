package com.gildedrose.second.model.predicates.common;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class TrueCondition implements Predicate<Item> {

    @Override
    public boolean test(Item item) {
        return true;
    }
}
