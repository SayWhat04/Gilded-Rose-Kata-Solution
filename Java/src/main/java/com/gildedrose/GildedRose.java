package com.gildedrose;

import com.gildedrose.second.api.DynamicItemWrapperFactory;
import com.gildedrose.first.item.ItemWrapper;

import java.util.Arrays;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
            // .map(ItemWrapperFactory::create) // First approach
            .map(DynamicItemWrapperFactory::create) // Second approach
            .forEach(ItemWrapper::updateItemState);
    }
}
