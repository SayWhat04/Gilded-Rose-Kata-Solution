package com.gildedrose.second.model.item;

import com.gildedrose.Item;
import com.gildedrose.first.item.ItemWrapper;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class DynamicItemWrapper implements ItemWrapper {
    private Item item;
    private Map<Predicate<Item>, Function<Item, Void>> sellInUpdaters;
    private Map<Predicate<Item>, Function<Item, Void>> qualityUpdaters;

    private DynamicItemWrapper(Builder builder) {
        this.item = builder.item;
        this.sellInUpdaters = builder.sellInUpdaters;
        this.qualityUpdaters = builder.qualityUpdaters;
    }

    @Override
    public void updateItemState() {
        updateSellIn();
        updateQuality();
    }

    private void updateSellIn() {
        for (Map.Entry<Predicate<Item>, Function<Item, Void>> entry : sellInUpdaters.entrySet()) {
            if (entry.getKey().test(item)) {
                entry.getValue().apply(item);
            }
        }
    }

    private void updateQuality() {
        for (Map.Entry<Predicate<Item>, Function<Item, Void>> entry : qualityUpdaters.entrySet()) {
            if (entry.getKey().test(item)) {
                entry.getValue().apply(item);
            }
        }
    }

    public static class Builder {
        private Item item;
        private Map<Predicate<Item>, Function<Item, Void>> sellInUpdaters;
        private Map<Predicate<Item>, Function<Item, Void>> qualityUpdaters;

        public Builder(Item item) {
            this.item = item;
            // order of inserting elements is important, because actions are inserted in
            // order they should be executed later
            this.sellInUpdaters = new LinkedHashMap<>();
            this.qualityUpdaters = new LinkedHashMap<>();
        }

        public Builder sellInCondition(Predicate<Item> condition, Function<Item, Void> action) {
            this.sellInUpdaters.put(condition, action);
            return this;
        }

        public Builder qualityCondition(Predicate<Item> condition, Function<Item, Void> action) {
            this.qualityUpdaters.put(condition, action);
            return this;
        }

        public DynamicItemWrapper build() {
            return new DynamicItemWrapper(this);
        }
    }
}
