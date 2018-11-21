import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class HW_7 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

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

        int sourceInt = 0;
        int destinationInt = 0;


        LinkedList airports = graph.getVertices();
        ListIterator airportIterator = airports.listIterator(0);

        do {

            int menuInt = 1;


            while (airportIterator.hasNext()) {
                System.out.println(menuInt + ") " + airportIterator.next());
                menuInt++;
            }

            System.out.print("Choose source airport: ");

            try {
                sourceInt = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter integer numbers only, or '-1' to quit. ");
            }

            System.out.print("Choose destination airport: ");

            try {
                destinationInt = scan.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please enter integer numbers only, or '-1' to quit. ");
            }

            //do stuff here


        } while (sourceInt != -1 && destinationInt != -1);


    }


}


