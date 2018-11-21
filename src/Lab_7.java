import java.util.LinkedList;
import java.util.ListIterator;

public class Lab_7 {

    public static void main(String[] args) {

        WeightedGraph graph = new WeightedGraph();

        Vertex<Airport> lns = new Vertex(new Airport("LNS"));
        Vertex<Airport> cth = new Vertex(new Airport("CTH"));
        Vertex<Airport> phl = new Vertex(new Airport("PHL"));
        Vertex<Airport> ilg = new Vertex(new Airport("ILG"));

        graph.addVertex(lns);
        graph.addVertex(cth);
        graph.addVertex(phl);
        graph.addVertex(ilg);

        graph.addEdge(3,phl,lns);
        graph.addEdge(1,phl,cth);
        graph.addEdge(1,phl,ilg);
        graph.addEdge(1,cth,ilg);
        graph.addEdge(1,cth,lns);
        graph.addEdge(2,lns,ilg);

        graph.addEdge(3,lns,phl);
        graph.addEdge(1,cth,phl);
        graph.addEdge(1,ilg,phl);
        graph.addEdge(1,ilg,cth);
        graph.addEdge(1,lns,cth);
        graph.addEdge(2,ilg,lns);

        LinkedList<Edge> edges = graph.getEdges();
        ListIterator iterator = edges.listIterator(0);

        while(iterator.hasNext()) {

            Edge edge = (Edge) iterator.next();

            System.out.printf(edge + ", with a %d hour travel time.\n", edge.getWeight());
        }

    }
}
