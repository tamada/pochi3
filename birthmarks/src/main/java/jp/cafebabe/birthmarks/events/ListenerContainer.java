package jp.cafebabe.birthmarks.events;

import jp.cafebabe.birthmarks.comparators.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public class ListenerContainer {
    private final List<Pair<BirthmarkEvent.Id[], BirthmarkListener>> listeners = new ArrayList<>();

    public void addBirthmarkListener(BirthmarkListener listener, BirthmarkEvent.Id... ids) {
        this.listeners.add(Pair.of(ids, listener));
    }

    public int size() {
        return listeners.size();
    }

    public BirthmarkListener removeBirthmarkListener(BirthmarkListener listener) {
        var first = listeners.stream().filter(p -> p.test((l, r) -> r == listener))
                .findFirst();
        first.ifPresent(l -> listeners.remove(l));
        return first.map(p -> p.right()).orElse(null);
    }

    public void fireEvent(BirthmarkEvent e) {
        switch(e.phase()) {
            case After -> fireImpl(e, BirthmarkListener::after);
            case AfterEach -> fireImpl(e, BirthmarkListener::afterEach);
            case BeforeEach -> fireImpl(e, BirthmarkListener::beforeEach);
            case Before -> fireImpl(e, BirthmarkListener::before);
        }
    }

    private void fireImpl(BirthmarkEvent e, BiConsumer<BirthmarkListener, BirthmarkEvent> action) {
        listeners.forEach(listener -> fireEventIfNeeded(e, action, listener));
    }

    private void fireEventIfNeeded(BirthmarkEvent e, BiConsumer<BirthmarkListener, BirthmarkEvent> action, Pair<BirthmarkEvent.Id[], BirthmarkListener> pair) {
        if(pair.test((l, r) -> isIn(l, e)))
            pair.accept((l, r) -> action.accept(r, e));
    }

    private boolean isIn(BirthmarkEvent.Id[] ids, BirthmarkEvent e) {
        return Arrays.stream(ids)
                .anyMatch(id -> e.id() == id);
    }
}