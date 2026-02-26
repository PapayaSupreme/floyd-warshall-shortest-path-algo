package structures;

import java.util.*;

/**
 * Graph structure designed to support multiple independent graphs at runtime.
 * optimized to iterate through as Floyd-Warshall algorithm needs it ofc
 * edges.get(u.getId()).get(v.getId()) -> Edge from node u to node v
 */
public final class Graph {
    private final Map<Integer, Node> nodes = new HashMap<>();
    private final Map<Integer, Map<Integer, Edge>> edges = new HashMap<>();
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
    }


    public Node addNode(int id) {
        return this.nodes.computeIfAbsent(id, Node::new);
    }

    /**
     * Adds or replaces an originId -> (destId -> Edge) with given weight.
     * For undirected graphs, also adds/replaces destId -> (originId -> Edge) with same weight.
     * If multiple edges for same pair are given over time, this keeps the minimum weight.
     */
    public void addEdge(int originId, int destinationId, int weight) {
        //here u can add a condition for forbidding eddge that loop

        Node origin = addNode(originId);
        Node destination = addNode(destinationId);

        putMinEdge(origin, destination, weight);

        if (!this.directed) {
            putMinEdge(destination, origin, weight);
        }
    }

    private void putMinEdge(Node origin, Node destination, int weight) {
        Map<Integer, Edge> out = this.edges.computeIfAbsent(origin.id(), k -> new HashMap<>());
        Edge existing = out.get(destination.id());

        if (existing == null || weight < existing.weight()) {
            out.put(destination.id(), new Edge(origin, destination, weight));
        }
    }

    /**
     * Returns the Edge originId -> destinationId, or null if none exists.
     */
    public Edge getEdge(int originId, int destinationId) {
        Map<Integer, Edge> out = this.edges.get(originId);
        return out == null ? null : out.get(destinationId);
    }

    /**
     * Returns weight of Edge originId -> destinationId if edge exists, or empty int if none exists.
     */
    public OptionalInt getWeight(int originId, int destinationId) {
        Edge e = getEdge(originId, destinationId);
        return e == null ? OptionalInt.empty() : OptionalInt.of(e.weight());
    }

    /**
     * Returns outgoing edges from a node, or empty map if none exists.
     */
    public Map<Integer, Edge> outgoingFrom(int originId) {
        Map<Integer, Edge> out = edges.get(originId);
        return out == null ? Map.of() : Collections.unmodifiableMap(out);
    }

    public boolean isDirected() { return this.directed; }

    public Collection<Node> getNodes() { return Collections.unmodifiableCollection(this.nodes.values()); }

    public Map<Integer, Node> getNodesById() { return Collections.unmodifiableMap(this.nodes); }

    /**
     * originId -> (destId -> Edge)
     */
    public Map<Integer, Map<Integer, Edge>> getEdges() { return Collections.unmodifiableMap(this.edges); }
}
