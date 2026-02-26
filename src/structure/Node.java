package structure;

public record Node(int id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) { // same reference ?
            return true;
        }
        if (!(o instanceof Node(int tempId))) { //is the object even a node ? (its record struct, could be !(o instanceof Node node);
            return false;
        }
        return id == tempId; // is this the same node ? if  not in record strcut, do return id == o.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Node(ID = " + id + ")";
    }
}
