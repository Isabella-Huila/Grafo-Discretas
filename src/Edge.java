
import java.util.*;


public class Edge<V> {

    private Vertex<V> source;
    private Vertex<V> destination;
    private int cost;

    public Edge(Vertex<V> vertex1, Vertex<V> destination, int cost) {
        this.source = vertex1;
        this.destination = destination;
        this.cost = cost;
    }

    public Vertex<V> getSource() {
        return source;
    }

    public void setSource(Vertex<V> source) {
        this.source = source;
    }

    public Vertex<V> getDestination() {
        return destination;
    }

    public void setDestination(Vertex<V> destination) {
        this.destination = destination;
    }

    public int getCost() {
        return this.cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Vertex<V> getNeighbor(Vertex<V> vertex) {

        if (vertex == source) {
            return destination;
        } else if (vertex ==destination ){
            return source;
        } else {
            throw new IllegalArgumentException("Vertex is not connected by this edge");
        }
    }
}
