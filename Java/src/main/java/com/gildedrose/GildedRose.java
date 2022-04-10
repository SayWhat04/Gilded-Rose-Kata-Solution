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
            }

            if (item.name.equals(BACKSTAGE_PASSES)) {
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

            if (item.name.equals(AGED_BRIE)) {
                if (isQualityBelowMaxValue(item)) {
                    item.quality = item.quality + 1;
                }
            }


            // If item is not Backstage pass or Brie, NORMAL ITEM
            // can be moved to else block
            if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {

                // Item quality can't be negative
                if (isQualityBiggerThatLowestValue(item)) {

                    // If item is not Sulfuras
                    item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
                }
            } else {
                //// Item quality can't be greater that 50
                //if (isQualityBelowMaxValue(item)) {
                //    item.quality = item.quality + 1;
//
                //    // If item is Backstage Pass...
                //    if (item.name.equals(BACKSTAGE_PASSES)) {
                //        // ... and there are 10 or less days to concert...
                //        if (item.sellIn < 11) {
//
                //            // cant't breach 50 quality
                //            if (isQualityBelowMaxValue(item)) {
//
                //                // ... increase quality by total 2
                //                item.quality = item.quality + 1;
                //            }
                //        }
//
                //        // ... and there are 5 or less days to concert...
                //        if (item.sellIn < 6) {
//
                //            // cant't breach 50 quality
                //            if (isQualityBelowMaxValue(item)) {
//
                //                // ... increase quality by total 3
                //                item.quality = item.quality + 1;
                //            }
                //        }
                //    }
                //}
            }

            // Only Sulfuras isn't needed to be sold
            item.sellIn = item.sellIn - 1;

            // Once selling date has passed, quality will drop twice as fast
            if (item.sellIn < 0) {

                // Quality for Brie will increase as days passed
                if (item.name.equals(AGED_BRIE)) {

                    // This condition will be met for Brie only
                    if (isQualityBelowMaxValue(item)) {
                        item.quality = item.quality + 1;
                    }
                } else if (item.name.equals(BACKSTAGE_PASSES)) {
                    // If concert date passed, quality of Backstage Pass will drop to 0
                    item.quality = 0;
                } else {
                    if (isQualityBiggerThatLowestValue(item)) {
                        item.quality = item.quality - 1; // Reduce item quality. It is normal item approach
                    }
                }
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
