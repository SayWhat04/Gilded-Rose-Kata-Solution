package com.gildedrose.item;

public enum CustomItemNames {
    AGED_BRIE("Aged Brie"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert"),
    CONJURED("Conjured");

    private final String name;

    CustomItemNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
