import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class LAB_7 {

    public static void main(String[] args) {

        Vertex<Airport> lns = new Vertex(new Airport("LNS"));
        Vertex<Airport> cth = new Vertex(new Airport("CTH"));
        Vertex<Airport> ilg = new Vertex(new Airport("ILG"));
        Vertex<Airport> phl = new Vertex(new Airport("PHL"));

        lns.connect(phl,3);
        lns.connect(cth,1);
        lns.connect(ilg,2);

        cth.connect(ilg,1);
        cth.connect(lns,1);
        cth.connect(phl,1);

        ilg.connect(phl,1);
        ilg.connect(cth,1);
        ilg.connect(lns,2);

        phl.connect(lns,3);
        phl.connect(cth,1);
        phl.connect(ilg,1);

        HashMap<String, Vertex> map = new HashMap();
        map.put("LNS", lns);
        map.put("CTH", cth);
        map.put("ILG", ilg);
        map.put("PHL", phl);


        for(Vertex v: map.values()) {

            Iterator connectedFlights = v.getNeighborIterator();
            Iterator connectFlightTime = v.getWeightIterator();

            while (connectedFlights.hasNext() && connectFlightTime.hasNext()) {
                System.out.print(v + " is connected to ");
                System.out.print(connectedFlights.next());
                System.out.println(" with a flight time of " + connectFlightTime.next() + " hours.");

            }
        }

        }
}
