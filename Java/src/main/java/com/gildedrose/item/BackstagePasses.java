package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePasses implements ItemWrapper {
    private static final int MAX_QUALITY = 50;
    private Item item;

    public BackstagePasses(Item item) {
        this.item = item;
    }

    @Override
    public void updateItemState() {
        updateQuality();
        updateSellIn();
    }

    private void updateSellIn() {
        item.sellIn = item.sellIn - 1;
    }

    private void updateQuality() {
        if (sellInIsOver(10)) {
            increaseQualityBy(1);
        } else if (sellInIsOver(5)) {
            increaseQualityBy(2);
        } else if (sellInIsOver(0)) {
            increaseQualityBy(3);
        } else {
            dropQualityToZero();
        }

        if (item.quality > MAX_QUALITY) {
            item.quality = MAX_QUALITY;
        }
    }

    private boolean sellInIsOver(int dayNumber) {
        return item.sellIn > dayNumber;
    }

    private void increaseQualityBy(int qualityValue) {
        item.quality += qualityValue;
    }

    private void dropQualityToZero() {
        item.quality = 0;
    }
}
