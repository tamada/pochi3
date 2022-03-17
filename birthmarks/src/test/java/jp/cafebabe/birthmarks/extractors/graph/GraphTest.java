package jp.cafebabe.birthmarks.extractors.graph;

import jp.cafebabe.birthmarks.extractors.graph.Graph;
import jp.cafebabe.birthmarks.extractors.graph.GraphBuilder;
import jp.cafebabe.birthmarks.extractors.graph.Node;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {
    public Graph<String> buildGraph() {
        return new GraphBuilder<>(Stream.of("a", "b", "c", "d"))
                .relateTo("a", "d")
                .relateMutually("a", "c", 3)
                .relateMutually("b", "d", 2)
                .relateMutually("c", "d")
                .build();
    }

    @Nested
    public class NodeTest {
        @Test
        public void testRelatedNodesWithIndex() {
            var graph = buildGraph();
            var array = graph.node(0).relatedNodes().toArray(Node[]::new);
            assertEquals(2, array.length);
            assertEquals("c", array[0].element());
            assertEquals("d", array[1].element());
        }

        @Test
        public void testRelatedNodesWithItem() {
            var graph = buildGraph();
            var array = graph.node("b").relatedNodes().toArray(Node[]::new);
            assertEquals(1, array.length);
            assertEquals("d", array[0].element());
        }

        @Test
        public void testNoSuchElement() {
            var graph = buildGraph();
            assertThrows(NoSuchElementException.class, () -> graph.node("A"));
        }
    }

    @Nested
    public class StreamTest {
        @Test
        public void testStream() {
            var graph = buildGraph();
            var array = graph.stream().toArray(String[]::new);
            assertEquals(4, array.length);
            assertArrayEquals(new String[] { "a", "b", "c", "d"}, array);
        }

        @Test
        public void testNodeStream() {
            var graph = buildGraph();
            var array = graph.nodes().toArray(Node[]::new);
            assertEquals(4, array.length);

            assertEquals("a", array[0].element());
            assertEquals("b", array[1].element());
            assertEquals("c", array[2].element());
            assertEquals("d", array[3].element());
        }
    }

    @Nested
    public class UnboundIndexTest {
        @Test
        public void testUnboundIndex() {
            Graph<String> graph = buildGraph();
            assertThrows(NoSuchElementException.class, () -> graph.relation("c", "A"));
            assertThrows(NoSuchElementException.class, () -> graph.relation("C", "a"));
            assertThrows(NoSuchElementException.class, () -> graph.relation("C", "A"));
        }
    }

    @Nested
    public class GraphBuilderTest {
        @Test
        public void testBasicGraph() {
            Graph<String> graph = buildGraph();
            assertEquals(4, graph.size());
            assertEquals("a", graph.node(0).element());
            assertEquals("b", graph.node(1).element());
            assertEquals("c", graph.node(2).element());
            assertEquals("d", graph.node(3).element());

            assertFalse(graph.hasRelation("a", "a"));  assertEquals(0, graph.relation("a", "a"));
            assertFalse(graph.hasRelation("a", "b"));  assertEquals(0, graph.relation("a", "b"));
            assertTrue(graph.hasRelation("a", "c"));   assertEquals(3, graph.relation("a", "c"));
            assertTrue(graph.hasRelation("a", "d"));   assertEquals(1, graph.relation("a", "d"));
            assertFalse(graph.hasRelation("b", "a"));  assertEquals(0, graph.relation("b", "a"));
            assertFalse(graph.hasRelation("b", "b"));  assertEquals(0, graph.relation("b", "b"));
            assertFalse(graph.hasRelation("b", "c"));  assertEquals(0, graph.relation("b", "c"));
            assertTrue(graph.hasRelation("b", "d"));   assertEquals(2, graph.relation("b", "d"));
            assertTrue(graph.hasRelation("c", "a"));   assertEquals(3, graph.relation("c", "a"));
            assertFalse(graph.hasRelation("c", "b"));  assertEquals(0, graph.relation("c", "b"));
            assertFalse(graph.hasRelation("c", "c"));  assertEquals(0, graph.relation("c", "c"));
            assertTrue(graph.hasRelation("c", "d"));   assertEquals(1, graph.relation("c", "d"));
            assertFalse(graph.hasRelation("d", "a"));  assertEquals(0, graph.relation("d", "a"));
            assertTrue(graph.hasRelation("d", "b"));   assertEquals(2, graph.relation("d", "b"));
            assertTrue(graph.hasRelation("d", "c"));   assertEquals(1, graph.relation("d", "c"));
            assertFalse(graph.hasRelation("d", "d"));  assertEquals(0, graph.relation("d", "d"));
        }
    }
}
