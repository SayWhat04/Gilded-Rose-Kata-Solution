package com.gildedrose.second.model.predicates.common;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class FalseCondition implements Predicate<Item> {

    @Override
    public boolean test(Item item) {
        return false;
    }
}
