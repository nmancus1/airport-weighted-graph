import java.util.HashMap;

public class HW_7 {

    public static void main (String[] args) {


        Vertex<Airport> atl = new Vertex(new Airport("ATL"));
        Vertex<Airport> lax = new Vertex(new Airport("LAX"));
        Vertex<Airport> ord = new Vertex(new Airport("ORD"));
        Vertex<Airport> dfw = new Vertex(new Airport("DFW"));
        Vertex<Airport> den = new Vertex(new Airport("DEN"));
        Vertex<Airport> jfk = new Vertex(new Airport("JFK"));
        Vertex<Airport> sfo = new Vertex(new Airport("SFO"));
        Vertex<Airport> brw = new Vertex(new Airport("BRW"));


        atl.connect(dfw,2);
        atl.connect(jfk,2);

        lax.connect(dfw,3);
        lax.connect(jfk,7);
        lax.connect(sfo,1);

        ord.connect(den, 2);
        ord.connect(jfk,2);

        dfw.connect(atl,2);
        dfw.connect(lax,3);

        den.connect(ord,2);
        den.connect(sfo,3);

        jfk.connect(atl,2);
        jfk.connect(lax,7);
        jfk.connect(ord,2);
        jfk.connect(sfo,7);

        sfo.connect(lax,1);
        sfo.connect(den,3);
        sfo.connect(jfk,7);

        HashMap<String, Vertex> map = new HashMap();
        map.put("ATL", atl);
        map.put("LAX", lax);
        map.put("ORD", ord);
        map.put("DFW", )






    }

}
