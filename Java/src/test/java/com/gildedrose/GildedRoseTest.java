package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// TODO: Re-factor test methods names to meet specific naming convention
class GildedRoseTest {
    private final String NORMAL_ITEM = "Normal Item";
    private final String AGED_BRIE = "Aged Brie";
    private final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private final String CONJURED = "Conjured";


    @Test
    void normalItemBeforeExpirationDate() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 2, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void normalItemAfterExpirationDate() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 0, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void normalItemBeforeExpirationDateZeroQuality() {
        Item[] items = new Item[]{new Item(NORMAL_ITEM, 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void agedBrieCantHaveMoreQualityThat50() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 2, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void agedBrieShouldRaiseQuality() {
        Item[] items = new Item[]{new Item(AGED_BRIE, 2, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void sulfurasPropertiesShouldNotChange() {
        Item[] items = new Item[]{new Item(SULFURAS, 2, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void backstagePassValueDropToZeroAfterTimePassed() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 0, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void backstageIncreaseQualityByOneWhenDateBiggerThat10() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 11, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void backstageIncreaseQualityByTwoWhenDateLessThat10() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 10, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void backstageIncreaseQualityByThreeWhenDateLessThat6() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 2)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, app.items[0].quality);
    }

    @Test
    void backstageIncreaseQualityByThreeWhenDateLessThat6ButCantExceedMaxQuality() {
        Item[] items = new Item[]{new Item(BACKSTAGE_PASSES, 5, 48)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }
}
