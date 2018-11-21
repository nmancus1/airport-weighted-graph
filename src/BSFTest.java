import java.util.LinkedList;

public class BSFTest {

    public static void main (String[] args) {

        WeightedGraph graph = new WeightedGraph();

        Vertex<Airport> atl = new Vertex(new Airport("ATL"));
        Vertex<Airport> lax = new Vertex(new Airport("LAX"));
        Vertex<Airport> ord = new Vertex(new Airport("ORD"));
        Vertex<Airport> dfw = new Vertex(new Airport("DFW"));
        Vertex<Airport> den = new Vertex(new Airport("DEN"));
        Vertex<Airport> jfk = new Vertex(new Airport("JFK"));
        Vertex<Airport> sfo = new Vertex(new Airport("SFO"));
        Vertex<Airport> brw = new Vertex(new Airport("BRW"));


        graph.addVertex(atl);
        graph.addVertex(lax);
        graph.addVertex(ord);
        graph.addVertex(dfw);
        graph.addVertex(den);
        graph.addVertex(jfk);
        graph.addVertex(sfo);
        graph.addVertex(brw);

        //ATL
        graph.addEdge(2, atl, dfw);
        graph.addEdge(2, atl, jfk);

        //LAX
        graph.addEdge(2, lax, dfw);
        graph.addEdge(7, lax, jfk);
        graph.addEdge(1, lax, sfo);

        //ORD
        graph.addEdge(2, ord, den);
        graph.addEdge(2, ord, jfk);

        //DFW
        graph.addEdge(2, dfw, atl);

        //DEN
        graph.addEdge(2, den, ord);
        graph.addEdge(2, den, sfo);

        //JFK
        graph.addEdge(2, jfk, atl);
        graph.addEdge(7, jfk, lax);
        graph.addEdge(2, jfk, ord);
        graph.addEdge(7, jfk, sfo);

        //SFO
        graph.addEdge(1, sfo, lax);
        graph.addEdge(3, sfo, den);
        graph.addEdge(7, sfo, jfk);

        //graph.BFS(lax);
        LinkedList ll = new LinkedList();
        //LinkedList DFS = graph.recursiveDFS(ll,sfo,atl);

        int hours = graph.recursiveDFS(ll,lax,sfo);
        System.out.println("Hours: " + hours);
        graph.interativeDFS(lax,sfo);

        LinkedList ll2 = graph.interativeDFS(lax,sfo);
        System.out.println(ll2);

        //System.out.println(DFS);
        //System.out.println(ll);

    }
}
