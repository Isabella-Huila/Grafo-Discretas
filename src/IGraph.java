public interface IGraph<V> {

    void addVertex(Vertex<V> vertex);

    boolean removeVertex(V vertex);

    void addEdge(V source, V destination, int cost);

    boolean removeEdge(V source, V destination);

    void BFS(int startVertexIndex);

    void DFS(int startVertexIndex);
}


