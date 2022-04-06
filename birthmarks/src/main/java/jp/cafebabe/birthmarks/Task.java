package jp.cafebabe.birthmarks;

import jp.cafebabe.birthmarks.events.BirthmarkEvent;
import jp.cafebabe.birthmarks.events.BirthmarkListener;
import jp.cafebabe.birthmarks.events.ListenerContainer;

public abstract class Task {
    private ListenerContainer container = new ListenerContainer();

    public void addBirthmarkListener(BirthmarkListener listener, BirthmarkEvent.Id... ids) {
        container.addBirthmarkListener(listener, ids);
    }

    public BirthmarkListener removeBirthmarkListener(BirthmarkListener listener) {
        return container.removeBirthmarkListener(listener);
    }

    public void fireEvent(BirthmarkEvent e) {
        container.fireEvent(e);
    }
}
