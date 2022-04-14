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

        if (item.quality < MAX_QUALITY) {
            item.quality = item.quality + 1;
            // ... and there are 10 or less days to concert...
            if (item.sellIn < 11) {

                // cant't breach 50 quality
                if (item.quality < MAX_QUALITY) {

                    // ... increase quality by total 2
                    item.quality = item.quality + 1;
                }
            }

            // ... and there are 5 or less days to concert...
            if (item.sellIn < 6) {

                // cant't breach 50 quality
                if (item.quality < MAX_QUALITY) {

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
}
