package structures;

import java.util.Objects;

public record Edge(Node origin, Node destination, int weight) {
    public Edge(Node origin, Node destination, int weight) {
        this.origin = Objects.requireNonNull(origin);
        this.destination = Objects.requireNonNull(destination);
        this.weight = weight;
    }

    public Edge(Node origin, Node destination) {
        this(Objects.requireNonNull(origin), Objects.requireNonNull(destination), 1);
    }

    @Override
    public String toString() {
        return "Edge(Origin ID = " + this.origin.id() + ", Destination ID = " + this.destination.id() + ", Weight = " + this.weight + ")";
    }
}