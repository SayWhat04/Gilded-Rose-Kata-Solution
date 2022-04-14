package com.gildedrose;

import com.gildedrose.item.ItemWrapper;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
            .map(ItemWrapperFactory::create)
            .forEach(ItemWrapper::updateItemState);
    }
}
