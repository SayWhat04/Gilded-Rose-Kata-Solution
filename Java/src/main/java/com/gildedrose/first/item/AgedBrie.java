package com.gildedrose.first.item;

import com.gildedrose.Item;

public class AgedBrie implements ItemWrapper {
    private static final int MAX_QUALITY = 50;
    private Item item;

    public AgedBrie(Item item) {
        this.item = item;
    }

    @Override
    public void updateItemState() {
        updateSellIn();
        updateQuality();
    }

    private void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    private void updateQuality() {
        item.quality = item.quality + 1;
        if (item.sellIn < 0) {
            item.quality = item.quality + 1;
        }

        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }
}
