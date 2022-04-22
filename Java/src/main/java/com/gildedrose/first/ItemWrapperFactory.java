package com.gildedrose.first;

import com.gildedrose.Item;
import com.gildedrose.first.item.*;

public class ItemWrapperFactory {

    public static ItemWrapper create(Item item) {
        if (item.name.equals(CustomItemNames.SULFURAS.getName())) {
            return new Sulfuras(item);
        } else if (item.name.equals(CustomItemNames.BACKSTAGE_PASSES.getName())) {
            return new BackstagePasses(item);
        } else if (item.name.equals(CustomItemNames.AGED_BRIE.getName())) {
            return new AgedBrie(item);
        } else if (item.name.contains(CustomItemNames.CONJURED.getName())) {
            return new ConjuredItem(item);
        } else {
            return new RegularItem(item);
        }
    }
}
