package jp.cafebabe.birthmarks.events;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ListenerContainerTest {

    @Test
    public void testRemove() {
        var listeners = new BirthmarkListener[] {
                new BirthmarkAdapter(), new BirthmarkAdapter(), new BirthmarkAdapter(),
                new BirthmarkAdapter(), new BirthmarkAdapter(), new BirthmarkAdapter(),
        };
        var container = new ListenerContainer();
        Arrays.stream(listeners).forEach(container::addBirthmarkListener);
        assertEquals(6, container.size());
        var l1 = container.removeBirthmarkListener(listeners[2]);
        assertEquals(listeners[2], l1);
        assertEquals(5, container.size());
        var l2 = container.removeBirthmarkListener(listeners[2]);
        assertNull(l2);
        assertEquals(5, container.size());
    }

    @Test
    public void testBasic() {
        var container = new ListenerContainer();
        container.addBirthmarkListener(new BirthmarkAdapter() {
            @Override
            public void beforeEach(BirthmarkEvent e) {
                assertEquals(BirthmarkEvent.Id.Extraction, e.id());
                assertEquals(BirthmarkEvent.Phase.BeforeEach, e.phase());
            }

            @Override
            public void afterEach(BirthmarkEvent e) {
                assertEquals(BirthmarkEvent.Id.Extraction, e.id());
                assertEquals(BirthmarkEvent.Phase.AfterEach, e.phase());
            }

            @Override
            public void before(BirthmarkEvent e) {
                assertEquals(BirthmarkEvent.Id.Extraction, e.id());
                assertEquals(BirthmarkEvent.Phase.Before, e.phase());
            }

            @Override
            public void after(BirthmarkEvent e) {
                assertEquals(BirthmarkEvent.Id.Extraction, e.id());
                assertEquals(BirthmarkEvent.Phase.After, e.phase());
            }
        }, BirthmarkEvent.Id.Extraction);

        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.After, null));
        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.AfterEach, null));
        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.Before, null));
        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Extraction, BirthmarkEvent.Phase.BeforeEach, null));
        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Comparison, BirthmarkEvent.Phase.After, null));
        container.fireEvent(BirthmarkEvent.of(BirthmarkEvent.Id.Parsing, BirthmarkEvent.Phase.After, null));
    }
}
