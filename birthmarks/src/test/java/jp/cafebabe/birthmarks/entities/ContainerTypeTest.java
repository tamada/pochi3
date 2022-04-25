package jp.cafebabe.birthmarks.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContainerTypeTest {
    @Test
    public void testBasic() {
        assertTrue(ContainerType.List.isAcceptable(ContainerType.List));
        assertFalse(ContainerType.List.isAcceptable(ContainerType.Set));
        assertFalse(ContainerType.List.isAcceptable(ContainerType.Graph));
        assertFalse(ContainerType.List.isAcceptable(ContainerType.Vector));

        assertTrue(ContainerType.Set.isAcceptable(ContainerType.List));
        assertTrue(ContainerType.Set.isAcceptable(ContainerType.Set));
        assertFalse(ContainerType.Set.isAcceptable(ContainerType.Graph));
        assertTrue(ContainerType.Set.isAcceptable(ContainerType.Vector));

        assertFalse(ContainerType.Graph.isAcceptable(ContainerType.List));
        assertFalse(ContainerType.Graph.isAcceptable(ContainerType.Set));
        assertTrue(ContainerType.Graph.isAcceptable(ContainerType.Graph));
        assertFalse(ContainerType.Graph.isAcceptable(ContainerType.Vector));

        assertTrue(ContainerType.Vector.isAcceptable(ContainerType.List));
        assertFalse(ContainerType.Vector.isAcceptable(ContainerType.Set));
        assertFalse(ContainerType.Vector.isAcceptable(ContainerType.Graph));
        assertTrue(ContainerType.Vector.isAcceptable(ContainerType.Vector));
    }
}
