package com.gildedrose.second.api;

import com.gildedrose.Item;
import com.gildedrose.first.item.CustomItemNames;
import com.gildedrose.first.item.ItemWrapper;
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

public class DynamicItemWrapperFactory {

    public static ItemWrapper create(Item item) {
        if (item.name.equals(CustomItemNames.SULFURAS.getName())) {
            return buildSulfurasWrapper(item);
        } else if (item.name.equals(CustomItemNames.BACKSTAGE_PASSES.getName())) {
            return buildBackstagePassWrapper(item);
        } else if (item.name.equals(CustomItemNames.AGED_BRIE.getName())) {
            return buildBrieWrapper(item);
        } else if (item.name.contains(CustomItemNames.CONJURED.getName())) {
            return buildConjuredItem(item);
        } else {
            return buildRegularItemWrapper(item);
        }
    }

    private static DynamicItemWrapper buildSulfurasWrapper(Item item) {
        return new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new NullUpdater())
            .qualityCondition(new TrueCondition(), new NullUpdater())
            .build();
    }

    private static DynamicItemWrapper buildBackstagePassWrapper(Item item) {
        return new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(
                new SellInBiggerThan(10).or(new SellInEquals(10)),
                new IncreasingQuality(1))
            .qualityCondition(
                new SellInBiggerThan(5).and(new SellInLesserThan(10)),
                new IncreasingQuality(2))
            .qualityCondition(
                new SellInBiggerThan(0).and(new SellInLesserThan(6)),
                new IncreasingQuality(3))
            .qualityCondition(new QualityBiggerThan(50), new SetQuality(50))
            .qualityCondition(new SellInLesserThan(0), new SetQuality(0))
            .build();
    }

    private static DynamicItemWrapper buildBrieWrapper(Item item) {
        return new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(new QualityLesserThan(50), new IncreasingQuality(1))
            .qualityCondition(
                new QualityLesserThan(50).and(new SellInLesserThan(0)),
                new IncreasingQuality(1))
            .build();
    }

    private static DynamicItemWrapper buildConjuredItem(Item item) {
        return new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(new SellInBiggerThan(0).or(new SellInEquals(0)), new DecreasingQuality(2))
            .qualityCondition(new SellInLesserThan(0), new DecreasingQuality(4))
            .qualityCondition(new QualityLesserThan(0), new SetQuality(0))
            .build();
    }

    private static DynamicItemWrapper buildRegularItemWrapper(Item item) {
        return new DynamicItemWrapper.Builder(item)
            .sellInCondition(new TrueCondition(), new DecreasingSellIn(1))
            .qualityCondition(new SellInBiggerThan(0).or(new SellInEquals(0)), new DecreasingQuality(1))
            .qualityCondition(new SellInLesserThan(0), new DecreasingQuality(2))
            .qualityCondition(new QualityLesserThan(0), new SetQuality(0))
            .build();
    }
}
