
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class GraphTest {
    private Graph<Integer> graph;
    public void stangeOne() {

        this.graph = new Graph(false, false, false);
        Vertex<Integer> vertex1 = new Vertex(1);
        Vertex<Integer> vertex2 = new Vertex(2);
        Vertex<Integer> vertex3 = new Vertex(3);
        Vertex<Integer> vertex4 = new Vertex(4);
        this.graph.addVertex(vertex1);
        this.graph.addVertex(vertex2);
        this.graph.addVertex(vertex4);
        this.graph.addVertex(vertex3);
    }

    public void stageTwo() {
        this.graph = new Graph(false, false, false);
        this.graph.addVertex(new Vertex(1));
        this.graph.addVertex(new Vertex(2));
        this.graph.addEdge(1, 2, 10);
    }

    public void stageThree() {
        this.graph = new Graph(false, true, false);
        this.graph.addVertex(new Vertex(1));
        this.graph.addVertex(new Vertex(2));
        this.graph.addEdge(1, 2, 10);
        this.graph.addEdge(1, 2, 20);
    }

    public void stageFour() {
        this.graph = new Graph(false, true, true);
        this.graph.addVertex(new Vertex(1));
        this.graph.addVertex(new Vertex(2));
        this.graph.addEdge(1, 2, 10);
        this.graph.addEdge(1, 1, 20);
    }

    @Test
    public void addSuccessfullyVertextest() {
        this.stangeOne();
        Vertex<Integer> vertex = new Vertex(5);
        this.graph.addVertex(vertex);
        Assertions.assertEquals(5, (Integer)this.graph.searchVertex(5).getValue());
    }

    @Test
    public void addNonSuccessfullyVertextest() {
        this.stangeOne();
        int sizeBefore = this.graph.getVertices().size();
        Vertex<Integer> vertex = new Vertex(2);
        this.graph.addVertex(vertex);
        int sizeAfter = this.graph.getVertices().size();
        Assertions.assertEquals(sizeBefore, sizeAfter);
    }

    @Test
    public void addANullVertexTest() {
        this.stangeOne();
        int sizeBefore2 = this.graph.getVertices().size();
        Assertions.assertThrows(NullPointerException.class, () -> {
            this.graph.addVertex((Vertex)null);
        });
        int sizeAfter2 = this.graph.getVertices().size();
        Assertions.assertEquals(sizeBefore2, sizeAfter2);
    }

    @Test
    public void findVertexThatExistsTest() {
        this.stangeOne();
        Vertex<Integer> vertex = new Vertex(6);
        this.graph.addVertex(vertex);
        Vertex<Integer> result1 = this.graph.searchVertex(6);
        Assertions.assertEquals(vertex, result1);
    }

    @Test
    public void findVertexThatDoesNotExistTest() {
        this.stangeOne();
        Vertex<Integer> result2 = this.graph.searchVertex(8);
        Assertions.assertNull(result2);
    }

    @Test
    public void findNullValue() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            this.graph.searchVertex((Integer) null);
        });
    }

    @Test
    void testAddEdgeSimpleGraph() {
        this.stageTwo();
        Assertions.assertEquals(1, this.graph.getEdges().size());
        Assertions.assertEquals(1, ((Vertex)this.graph.getVertices().get(0)).getEdges().size());
        Assertions.assertEquals(1, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }

    @Test
    void testAddEdgeMultipleGraph() {
        this.stageThree();
        Assertions.assertEquals(2, this.graph.getEdges().size());
        Assertions.assertEquals(2, ((Vertex)this.graph.getVertices().get(0)).getEdges().size());
        Assertions.assertEquals(2, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }

    @Test
    void testAddEdgePseudograph() {
        this.stageFour();
        Assertions.assertEquals(2, this.graph.getEdges().size());
        Assertions.assertEquals(1, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }


    @Test
    void testRemoveEdgeSimpleGraph() {
        this.stageTwo();
        Assertions.assertTrue(this.graph.removeEdge(1, 2));
        Assertions.assertEquals(0, this.graph.getEdges().size());
        Assertions.assertEquals(0, ((Vertex)this.graph.getVertices().get(0)).getEdges().size());
        Assertions.assertEquals(0, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }

    @Test
    void testRemoveEdgeMultipleGraph() {
        this.stageThree();
        Assertions.assertTrue(this.graph.removeEdge(1, 2));
        Assertions.assertEquals(1, this.graph.getEdges().size());
        Assertions.assertEquals(1, ((Vertex)this.graph.getVertices().get(0)).getEdges().size());
        Assertions.assertEquals(1, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }

    @Test
    void testRemoveEdgePseudograph() {
        this.stageFour();
        Assertions.assertTrue(this.graph.removeEdge(1, 2));
        Assertions.assertEquals(1, this.graph.getEdges().size());
        Assertions.assertEquals(0, ((Vertex)this.graph.getVertices().get(1)).getEdges().size());
    }


    @Test
    public void testSingleVertexGraph() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        graph.addVertex(new Vertex<>(1));

        try {
            graph.BFS(0);
            // Comprobar que no se produzcan excepciones o errores
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail("Error - BFS en grafo con un solo vértice");
        }
    }

    @Test
    public void testMultipleVerticesGraph() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);
        Vertex<Integer> vertex4 = new Vertex<>(4);
        Vertex<Integer> vertex5 = new Vertex<>(5);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);

        graph.addEdge(1, 2, 0);
        graph.addEdge(1, 3, 0);
        graph.addEdge(2, 4, 0);
        graph.addEdge(3, 4, 0);
        graph.addEdge(3, 5, 0);

        try {
            graph.BFS(0);
            // Comprobar que no se produzcan excepciones o errores
            assertTrue(true);
        } catch (IllegalArgumentException e) {
            fail("Error - BFS en grafo con varios vértices y aristas");
        }
    }

    @Test
    public void testEmptyGraph() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        // Verificar si el grafo está vacío utilizando el método isEmpty()
        assertTrue(graph.getVertices().isEmpty());

}

    @Test
    public void testRemoveExistingVertex() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addEdge(1, 2, 0);

        boolean result = graph.removeVertex(vertex1.getValue());

        // Verificar que el vértice ha sido eliminado correctamente
        assertTrue(result);
        assertFalse(graph.getVertices().contains(vertex1));
    }

    @Test
    public void testRemoveNonExistingVertex() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(1);

        graph.addVertex(vertex1);

        boolean result = graph.removeVertex(2);

        // Verificar que el vértice no existe y la eliminación falla
        assertFalse(result);
        assertTrue(graph.getVertices().contains(vertex1));
    }

    @Test
    public void testRemoveVertexWithEdges() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addEdge(1, 2, 0);

        boolean result = graph.removeVertex(vertex2.getValue());

        // Verificar que el vértice y sus aristas han sido eliminados correctamente
        assertTrue(result);
        assertFalse(graph.getVertices().contains(vertex2));
        assertEquals(0, graph.getEdges().size());
    }
    
    @Test
    public void testDFSInvalidStartVertexIndex() {
        // Crear un grafo de ejemplo
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> v1 = new Vertex<>(1);
        graph.addVertex(v1);

        // Intentar realizar una búsqueda en profundidad (DFS) desde un índice de vértice inválido
        assertThrows(IllegalArgumentException.class, () -> graph.DFS(1));
    }

  
    @Test
    public void testDFSGraph1() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(1);
        Vertex<Integer> vertex2 = new Vertex<>(2);
        Vertex<Integer> vertex3 = new Vertex<>(3);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);

        graph.addEdge(1, 2, 0);
        graph.addEdge(2, 3, 0);

        graph.DFS(0);

        assertEquals(Color.BLACK, vertex1.getColor());
        assertEquals(Color.BLACK, vertex2.getColor());
        assertEquals(Color.BLACK, vertex3.getColor());
    }

    @Test
    public void testDFSGraph2() {
        Graph<Integer> graph = new Graph<>(false, false, false);
        Vertex<Integer> vertex1 = new Vertex<>(10);
        Vertex<Integer> vertex2 = new Vertex<>(20);
        Vertex<Integer> vertex3 = new Vertex<>(30);
        Vertex<Integer> vertex4 = new Vertex<>(40);

        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);

        graph.addEdge(10, 20, 0);
        graph.addEdge(10, 30, 0);
        graph.addEdge(20, 40, 0);

        graph.DFS(0);

        assertEquals(Color.BLACK, vertex1.getColor());
        assertEquals(Color.BLACK, vertex2.getColor());
        assertEquals(Color.BLACK, vertex3.getColor());
        assertEquals(Color.BLACK, vertex4.getColor());
    }



}

