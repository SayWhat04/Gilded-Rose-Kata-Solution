package com.gildedrose.item;

import com.gildedrose.Item;

public class Sulfuras implements ItemWrapper {

    private static final int MAX_QUALITY = 80;
    private Item item;

    public Sulfuras(Item item) {
        this.item = item;
    }

    @Override
    public void updateItemState() {
        // Sulfuras will never change it's properties
    }
}
