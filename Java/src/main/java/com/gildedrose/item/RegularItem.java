package com.gildedrose.item;

import com.gildedrose.Item;

public class RegularItem implements ItemWrapper {

    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private Item item;

    public RegularItem(Item item) {
        this.item = item;
    }

    @Override
    public void updateItemState() {
        item.sellIn = item.sellIn - 1;

        // Item quality can't be negative
        if (item.quality > MIN_QUALITY) {
            // If item is not Sulfuras
            item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
        }

        if (item.sellIn < 0) {
            if (item.quality > MIN_QUALITY) {
                item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
            }
        }
    }
}
