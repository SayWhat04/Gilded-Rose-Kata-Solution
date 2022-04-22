package com.gildedrose.first.item;

import com.gildedrose.Item;

public class ConjuredItem implements ItemWrapper {
    private static final int MIN_QUALITY = 0;
    private static final int MAX_QUALITY = 50;
    private Item item;

    public ConjuredItem(Item item) {
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
        item.quality = item.quality - 2;

        if (item.sellIn < 0) {
            item.quality = item.quality - 2;
        }
        if (item.quality < MIN_QUALITY) {
            item.quality = MIN_QUALITY;
        }
    }
}
