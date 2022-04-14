package com.gildedrose;

import com.gildedrose.item.*;

public class ItemWrapperFactory {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";

    public static ItemWrapper create(Item item) {
        if (item.name.equals(SULFURAS)) {
            return new Sulfuras(item);
        } else if (item.name.equals(BACKSTAGE_PASSES)) {
            return new BackstagePasses(item);
        } else if (item.name.equals(AGED_BRIE)) {
            return new AgedBrie(item);
        } else {
            return new RegularItem(item);
        }
    }
}
