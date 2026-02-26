package app;

import structures.Graph;

public class App {
    public static void main(String[] args) {
        //little test wip
        Graph g = new Graph(true);
        g.addEdge(1, 2, 5);
        g.addEdge(1, 3, 10);
        g.addEdge(2, 3, 4);

        // Duplicate pair (1,2) with higher weight -> ignored because we keep min
        g.addEdge(1, 2, 9);

        // Duplicate pair (1,2) with lower weight -> replaces
        g.addEdge(1, 2, 3);

        System.out.println("Nodes: " + g.getNodes());
        System.out.println("1->2: " + g.getEdge(1, 2));
        System.out.println("Outgoing from 1: " + g.outgoingFrom(1).values());
    }
}

//Created by Pablo Ferreira the 25/02/2026
//pablo.ferreiraa10@gmail.com
