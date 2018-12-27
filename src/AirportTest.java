/**
 * This program creates, connects, and displays airport information
 * @author Nick Mancuso
 * @since 11/22/18
 */

import java.util.HashMap;
import java.util.Iterator;

public class AirportTest {

    public static void main(String[] args) {

        //Create vertices
        Vertex<Airport> lns = new Vertex(new Airport("LNS"));
        Vertex<Airport> cth = new Vertex(new Airport("CTH"));
        Vertex<Airport> ilg = new Vertex(new Airport("ILG"));
        Vertex<Airport> phl = new Vertex(new Airport("PHL"));

        /**CREATE FLIGHTS**/

        //LNS
        lns.connect(phl, 3);
        lns.connect(cth, 1);
        lns.connect(ilg, 2);

        //CTH
        cth.connect(ilg, 1);
        cth.connect(lns, 1);
        cth.connect(phl, 1);

        //ILG
        ilg.connect(phl, 1);
        ilg.connect(cth, 1);
        ilg.connect(lns, 2);

        //PHL
        phl.connect(lns, 3);
        phl.connect(cth, 1);
        phl.connect(ilg, 1);

        //Map to contain vertices
        HashMap<String, Vertex> map = new HashMap();
        map.put("LNS", lns);
        map.put("CTH", cth);
        map.put("ILG", ilg);
        map.put("PHL", phl);


        //Iterate through map's values
        for (Vertex v : map.values()) {

            //Get iterators
            Iterator connectedFlights = v.getNeighborIterator();
            Iterator connectFlightTime = v.getWeightIterator();

            //While iterators iterating, print flights and times
            while (connectedFlights.hasNext() && connectFlightTime.hasNext()) {
                System.out.print(v + " is connected to ");
                System.out.print(connectedFlights.next());
                System.out.println(" with a flight time of " + connectFlightTime.next() + " hours.");

            }
        }

    }
}
