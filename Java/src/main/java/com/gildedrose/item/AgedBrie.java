package com.gildedrose.item;

import com.gildedrose.Item;

public class AgedBrie implements ItemWrapper {

    private static final int MAX_QUALITY = 50;
    private Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void updateItemState() {
        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            // This condition will be met for Brie only
            if (item.quality < MAX_QUALITY) {
                item.quality = item.quality + 1;
            }
        }
    }
}
