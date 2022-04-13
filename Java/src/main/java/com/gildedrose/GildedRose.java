package com.gildedrose;

class GildedRose {
    Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        // Iterate over all items
        for (Item item : items) {
            // Sulfuras can't be altered
            if (item.name.equals(SULFURAS)) {
                return;
            } else if (item.name.equals(BACKSTAGE_PASSES)) {
                handleBackstagePasses(item);
            } else if (item.name.equals(AGED_BRIE)) {
                handleAgedBrie(item);
            }
            else {
                handleNormalItem(item);
            }
        }
    }

    private void handleBackstagePasses(Item item) {
        if (isQualityBelowMaxValue(item)) {
            item.quality = item.quality + 1;
            // ... and there are 10 or less days to concert...
            if (item.sellIn < 11) {

                // cant't breach 50 quality
                if (isQualityBelowMaxValue(item)) {

                    // ... increase quality by total 2
                    item.quality = item.quality + 1;
                }
            }

            // ... and there are 5 or less days to concert...
            if (item.sellIn < 6) {

                // cant't breach 50 quality
                if (isQualityBelowMaxValue(item)) {

                    // ... increase quality by total 3
                    item.quality = item.quality + 1;
                }
            }
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            // If concert date passed, quality of Backstage Pass will drop to 0
            item.quality = 0;
        }
    }

    private void handleAgedBrie(Item item) {
        if (isQualityBelowMaxValue(item)) {
            item.quality = item.quality + 1;
        }
        item.sellIn = item.sellIn - 1;

        if (item.sellIn < 0) {
            // This condition will be met for Brie only
            if (isQualityBelowMaxValue(item)) {
                item.quality = item.quality + 1;
            }
        }
    }

    private void handleNormalItem(Item item) {
        item.sellIn = item.sellIn - 1;

        // Item quality can't be negative
        if (isQualityBiggerThatLowestValue(item)) {
            // If item is not Sulfuras
            item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
        }

        if (item.sellIn < 0) {
            if (isQualityBiggerThatLowestValue(item)) {
                item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
            }
        }
    }

    private boolean isQualityBiggerThatLowestValue(Item item) {
        return item.quality > 0;
    }

    private boolean isQualityBelowMaxValue(Item item) {
        return item.quality < 50;
    }
}
