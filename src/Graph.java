import java.util.*;


public class Graph <V> implements IGraph<V> {

    private ArrayList<Vertex<V>> vertices;
    private ArrayList<Edge<V>> edges;
    private int time;
    private boolean isDirectGraph;
    private boolean isMultiple;
    private boolean hasLoops;
    public ArrayList<Vertex<V>> getVertices() {
        return vertices;
    }

    public ArrayList<Edge<V>> getEdges() {
        return edges;
    }

    public Graph(boolean isDirectGraph, boolean isMultiple, boolean hasLoops) {
        this.vertices= new ArrayList<>();
        this.edges= new ArrayList<>();
        this.isDirectGraph = isDirectGraph;
        this.isMultiple = isMultiple;
        this.hasLoops = hasLoops;
    }

    @Override
    public void addVertex(Vertex<V> vertex) {
        if (searchVertex(vertex.getValue()) != null) {
            return;
        }
        vertices.add(vertex);

    }

   /** @Override
    public boolean removeVertex(V vertex) {
        Vertex<V> vertexFound = searchVertex(vertex);
        boolean delete = false;
        if (vertexFound != null) {
            vertices.remove(vertexFound);
            vertexFound.removeEdge();
            delete = true;
        }
        return delete;
    }**/
   @Override
   public boolean removeVertex(V vertex) {
       Vertex<V> vertexFound = searchVertex(vertex);
       boolean delete = false;
       if (vertexFound != null) {
           // Eliminar todas las aristas asociadas al vértice
           List<Edge<V>> edgesToRemove = new ArrayList<>();
           for (Edge<V> edge : edges) {
               if (edge.getSource().equals(vertexFound) || edge.getDestination().equals(vertexFound)) {
                   edgesToRemove.add(edge);
               }
           }
           edges.removeAll(edgesToRemove);

           // Eliminar el vértice del grafo
           vertices.remove(vertexFound);

           delete = true;
       }
       return delete;
   }

    @Override
    public void addEdge(V source, V destination, int cost) {
        Vertex<V> sourceVertex = searchVertex(source);
        Vertex<V> destinationVertex = searchVertex(destination);

        if (sourceVertex == null || destinationVertex == null) {
            throw new IllegalArgumentException("Source or destination vertex not found in the graph");
        }

        // Grafo simple
        if (!isDirectGraph && !isMultiple && !hasLoops) {
            if (sourceVertex != destinationVertex) {
                Edge<V> edge = new Edge<>(sourceVertex, destinationVertex, cost);
                if (!edges.contains(edge)) {
                    sourceVertex.addEdge(edge);
                    destinationVertex.addEdge(edge);
                    edges.add(edge);
                }
            }
            // Grafo multiple
        } else if (!isDirectGraph && isMultiple && !hasLoops) {
            if (sourceVertex != destinationVertex) {
                Edge<V> edge = new Edge<>(sourceVertex, destinationVertex, cost);
                sourceVertex.addEdge(edge);
                destinationVertex.addEdge(edge);
                edges.add(edge);
            }
            // Grafo pseudo grafo
        } else if (!isDirectGraph && isMultiple && hasLoops) {
            Edge<V> edge = new Edge<>(sourceVertex, destinationVertex, cost);
            sourceVertex.addEdge(edge);
            destinationVertex.addEdge(edge);
            edges.add(edge);
            // Grafo dirigido
        } else if (isDirectGraph && !isMultiple && hasLoops) {
            Edge<V> edge = new Edge<>(sourceVertex, destinationVertex, cost);
            if (!edges.contains(edge)) {
                sourceVertex.addEdge(edge);
                edges.add(edge);
            }
        } else if (isDirectGraph && isMultiple && hasLoops) {
            Edge<V> edge = new Edge<>(sourceVertex, destinationVertex, cost);
            sourceVertex.addEdge(edge);
            edges.add(edge);
        }

    }

    @Override
    public boolean removeEdge(V source, V destination) {
        Vertex<V> sourceVertex = searchVertex(source);
        Vertex<V> destinationVertex = searchVertex(destination);
        boolean delete = false;
        if (sourceVertex != null && destinationVertex != null) {
            Edge<V> edgeToRemove = null;
            for (Edge<V> edge : edges) {
                if (edge.getSource() == sourceVertex && edge.getDestination() == destinationVertex) {
                    edgeToRemove = edge;
                    break;
                }
            }
            if (edgeToRemove != null) {
                edges.remove(edgeToRemove);
                sourceVertex.getEdges().remove(edgeToRemove);
                destinationVertex.getEdges().remove(edgeToRemove);
                delete = true;
            }
        }
        return delete;
    }

    public Vertex<V> searchVertex(V value ){
        if (value == null){
            throw new NullPointerException("nul vertex");
        }
        for (Vertex<V> vertex: vertices){
            if (vertex.getValue().equals(value)){
                return vertex;
            }
        }
        return null;
    }



    @Override
    public void BFS(int startVertexIndex) {

        if (startVertexIndex < 0 || startVertexIndex >= vertices.size()) {
            throw new IllegalArgumentException("Invalid start vertex index");
        }

        Vertex<V> startVertex = vertices.get(startVertexIndex);

        // Marcar todos los vértices como no visitados (blanco)
        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setDistance(Integer.MAX_VALUE);
            vertex.setPredecessor(null);
        }

        // Usar una cola para el recorrido BFS
        Queue<Vertex<V>> queue = new LinkedList<>();

        // Marcar el vértice inicial como visitado (gris) y encolarlo
        startVertex.setColor(Color.GRAY);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            Vertex<V> currentVertex = queue.poll();

            // Recorrer los vértices adyacentes del vértice actual
            List<Edge<V>> edges = currentVertex.getEdges();
            for (Edge<V> edge : edges) {
                Vertex<V> neighbor = edge.getDestination();
                if (neighbor.getColor() == Color.WHITE) {
                    // Marcar el vértice adyacente como visitado (gris) y encolarlo
                    neighbor.setColor(Color.GRAY);
                    neighbor.setDistance(currentVertex.getDistance() + 1);
                    neighbor.setPredecessor(currentVertex);
                    queue.offer(neighbor);
                }
            }
            // Marcar el vértice actual como visitado completamente (negro)
            currentVertex.setColor(Color.BLACK);
        }

    }

    @Override
    public void DFS(int startVertexIndex) {
        if (startVertexIndex < 0 || startVertexIndex >= vertices.size()) {
            throw new IllegalArgumentException("Invalid start vertex index");
        }

        Vertex<V> startVertex = vertices.get(startVertexIndex);

        // Marcar todos los vértices como no visitados (blanco)
        for (Vertex<V> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }
        time = 0;
        for (Vertex<V> vertex : vertices) {
            if (vertex.getColor() == Color.WHITE) {
                DFSVisit(startVertex);
            }
        }

    }

    private void DFSVisit(Vertex<V> vertex) {
        time++;
        vertex.setTime(time);
        vertex.setColor(Color.GRAY);
        for (Edge<V> edge : edges) {
            Vertex<V> neighbor = edge.getDestination();
            if (neighbor.getColor() == Color.WHITE) {
                neighbor.setPredecessor(vertex);
                DFSVisit(neighbor);
            }
        }
        // Marcar el vértice actual como visitado completamente (negro)
        vertex.setColor(Color.BLACK);
        time++;
        vertex.setTime(time);
    }


}
