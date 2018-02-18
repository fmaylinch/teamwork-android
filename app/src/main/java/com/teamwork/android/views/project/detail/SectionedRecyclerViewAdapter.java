package com.teamwork.android.views.project.detail;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * {@link RecyclerView.Adapter} with sections.
 *
 * Each of the {@link #getSectionCount()} sections displays {@link #getItemCount(int)} items and
 * optionally a header, decided by {@link #isHeaderDisplayed(int)} (by default, if section has items).
 *
 * {@link #getItemViewType(ItemCoord)} decides the item type,
 * and {@link #onBindViewHolder(RecyclerView.ViewHolder, ItemCoord)} binds the item.
 * You have to implement {@link #onCreateViewHolder(ViewGroup, int)} as usual.
 *
 * Similar to this solution, but simpler: https://github.com/afollestad/sectioned-recyclerview
 */
public abstract class SectionedRecyclerViewAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {


    /** Number of sections */
    public abstract int getSectionCount();

    /** Number of items in the given section */
    public abstract int getItemCount(int section);

    /** Item view type of the item described by the given {@link ItemCoord} */
    public abstract int getItemViewType(ItemCoord itemCoord);

    public abstract void onBindViewHolder(T vh, ItemCoord coord);


    @Override
    public void onBindViewHolder(T vh, int position) {
        onBindViewHolder(vh, positionToCoord(position));
    }

    /** By default, displays header for sections with items; override if necessary */
    public boolean isHeaderDisplayed(int section) {
        return getItemCount(section) > 0;
    }

    @Override
    public int getItemCount() {
        int total = 0;
        for (int section = 0; section < getSectionCount(); section++) {
            total += getItemCount(section) + oneIf(isHeaderDisplayed(section));
        }
        return total;
    }

    @Override
    public int getItemViewType(int position) {
        return getItemViewType(positionToCoord(position));
    }

    /**
     * Calculates {@link ItemCoord} from general position.
     * Takes into account if {@link #isHeaderDisplayed(int)} for every section.
     */
    private ItemCoord positionToCoord(int position) {

        int current = 0;

        for (int section = 0; section < getSectionCount(); section++) {

            final int itemsInSection = getItemCount(section) + oneIf(isHeaderDisplayed(section));
            if (current + itemsInSection > position) {
                return new ItemCoord(section, position - current - oneIf(isHeaderDisplayed(section)));
            } else {
                current += itemsInSection;
            }
        }

        throw new IndexOutOfBoundsException("Index: " + position + ", item count: " + getItemCount());
    }

    /** Convenience conversion from boolean to int */
    public static int oneIf(boolean condition) {
        return condition ? 1 : 0;
    }

    /** Describes the coordinate (position) of an item */
    public static class ItemCoord {

        public static final int INDEX_HEADER = -1;

        /** Section of the item */
        public final int section;
        /** Index of item inside the {@link #section}; only makes sense if {@link #header} is false. */
        public final int index;
        /** True if this item represents a header (in that case {@link #index} is {@link #INDEX_HEADER}) */
        public final boolean header;

        private ItemCoord(int section, int index) {
            this.section = section;
            this.index = index;
            this.header = index == INDEX_HEADER;
        }
    }
}