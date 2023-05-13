import java.util.ArrayList;

public class Vertex<V> {

    private V value;
    private ArrayList<Edge<V>> edges;
    private int distance;
    private Vertex<V> predecessor;
    private Color color;
    private int time;

    public Vertex(V value) {
        this.value = value;
        this.edges = new ArrayList<>();
        predecessor = null;
        color = null;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public ArrayList<Edge<V>> getEdges() {
        return this.edges;
    }

    public void setEdges(ArrayList<Edge<V>> edges) {
        this.edges = edges;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Vertex<V> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(Vertex<V> predecessor) {
        this.predecessor = predecessor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void addEdge(Edge<V> edge) {
        edges.add(edge);
    }

    public void removeEdge() {
        for (Edge<V> edge : edges) {
            Vertex<V> vertex = compareEdge(edge);
            if (vertex != null) {
                vertex.getEdges().remove(edge);
            }
        }
    }

    private Vertex<V> compareEdge(Edge<V> edge) {
        if (this != edge.getSource()) {
            return edge.getSource();
        } else if (this != edge.getDestination()) {
            return edge.getDestination();
        } else
            return null;
    }

}

