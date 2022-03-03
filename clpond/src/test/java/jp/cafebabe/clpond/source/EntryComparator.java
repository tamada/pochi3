package jp.cafebabe.clpond.source;

import jp.cafebabe.clpond.entities.Entry;

import java.net.URI;
import java.util.Comparator;

public class EntryComparator implements Comparator<Entry> {
    @Override
    public int compare(Entry o1, Entry o2) {
        URI uri1 = o1.loadFrom();
        URI uri2 = o2.loadFrom();

        return uri1.compareTo(uri2);
    }

}
