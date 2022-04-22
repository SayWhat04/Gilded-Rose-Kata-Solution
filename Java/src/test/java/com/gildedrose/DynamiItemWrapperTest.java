package com.gildedrose;

import com.gildedrose.second.model.item.DynamicItemWrapper;
import com.gildedrose.second.model.predicates.common.TrueCondition;
import com.gildedrose.second.model.predicates.quality.QualityBiggerThan;
import com.gildedrose.second.model.predicates.quality.QualityLesserThan;
import com.gildedrose.second.model.predicates.sellin.SellInBiggerThan;
import com.gildedrose.second.model.predicates.sellin.SellInEquals;
import com.gildedrose.second.model.predicates.sellin.SellInLesserThan;

import com.gildedrose.second.model.updaters.common.NullUpdater;
import com.gildedrose.second.model.updaters.quality.DecreasingQuality;
import com.gildedrose.second.model.updaters.quality.IncreasingQuality;
import com.gildedrose.second.model.updaters.quality.SetQuality;
import com.gildedrose.second.model.updaters.sellin.DecreasingSellIn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DynamicItemWrapperTest {

    @Test
    void dynamicBuildOfNormalItem() {
        Item item = new Item("Normal Item", 5, 48);

        DynamicItemWrapper regularItemWrapper = new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(new QualityBiggerThan(0), new DecreasingQuality(1))
            .build();

        regularItemWrapper.updateSellIn();
        regularItemWrapper.updateQuality();

        assertEquals(4, item.sellIn);
        assertEquals(47, item.quality);
    }

    @Test
    void dynamicBuildOfBrie() {
        Item item = new Item("Brie", 0, 47);

        DynamicItemWrapper brieWrapper = new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(new QualityLesserThan(50), new IncreasingQuality(1))
            .qualityCondition(
                new QualityLesserThan(50).and(new SellInLesserThan(0)),
                new IncreasingQuality(1))
            .build();

        brieWrapper.updateSellIn();
        brieWrapper.updateQuality();

        assertEquals(-1, item.sellIn);
        assertEquals(49, item.quality);
    }

    @Test
    void dynamicBuildOfSulfuras() {
        Item item = new Item("Sulfuras", 0, 80);

        DynamicItemWrapper sufurasWrapper = new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new NullUpdater())
            .qualityCondition(new TrueCondition(), new NullUpdater())
            .build();

        sufurasWrapper.updateSellIn();
        sufurasWrapper.updateQuality();

        assertEquals(0, item.sellIn);
        assertEquals(80, item.quality);
    }

    @Test
    void dynamicBuildOfBackstagePass() {
        Item item = new Item("Backstage Pass", 5, 48);

        DynamicItemWrapper backstagePassWrapper = new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(
                new QualityLesserThan(50).and(new SellInBiggerThan(10)),
                new IncreasingQuality(1))
            .qualityCondition(
                new QualityLesserThan(50).and(new SellInBiggerThan(5).and(new SellInLesserThan(11))),
                new IncreasingQuality(2))
            .qualityCondition(
                new QualityLesserThan(50).and(new SellInBiggerThan(0).and(new SellInLesserThan(6))),
                new IncreasingQuality(3))
            .qualityCondition(
                new QualityBiggerThan(50), new SetQuality(50))
            .qualityCondition(
                new SellInEquals(0), new SetQuality(0))
            .build();

        backstagePassWrapper.updateSellIn();
        backstagePassWrapper.updateQuality();

        assertEquals(4, item.sellIn);
        assertEquals(50, item.quality);
    }
}
