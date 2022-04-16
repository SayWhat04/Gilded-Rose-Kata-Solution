package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    private final String NORMAL_ITEM = "Normal Item";
    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String CONJURED = "Conjured";

    @Test
    void should_ReduceItemQualityAndSellInByOne_when_NormalItemBeforeExpirationDate() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 2, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void should_ReduceItemQualityByTwo_when_NormalItemAfterExpirationDate() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 0, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void should_NotReduceItemQuality_when_NormalItemWithZeroQuality() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void should_NotIncreaseItemQuality_when_BrieWithMaxQuality() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 2, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void should_IncreaseItemQuality_when_Brie() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 2, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void should_NotChangeItemProperties_when_Sulfuras() {
        Item[] items = new Item[]{new Item(SULFURAS, 2, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void should_DropQualityToZero_when_BackstagePassesWithZeroSellIn() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 0, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void should_IncreaseQualityByOne_when_BackstagePassesWithTenOrMoreSellIn() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 11, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void should_IncreaseQualityByTwo_when_BackstagePassesWithLessThanTenSellIn() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 10, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void should_IncreaseQualityByThree_when_BackstagePassesWithLessThanSixSellIn() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
    }

    @Test
    void should_NotBreachMaxQuality_when_BackstagePassesWithLessThanSixSellIn() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void should_DropQualityByTwo_when_ConjuredItem() {
        Item[] items = new Item[]{new Item(CONJURED + " Item", 5, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
    }
}
