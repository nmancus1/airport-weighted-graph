import java.util.*;

public class HW_7 {

    static HashMap<String, Vertex> map = new HashMap();

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
        //jfk.connect(sfo,7);   commented this out for testing

        sfo.connect(lax,1);
        sfo.connect(den,3);
        sfo.connect(jfk,7);

        map.put("ATL", atl);
        map.put("LAX", lax);
        map.put("ORD", ord);
        map.put("DFW", dfw);
        map.put("DEN", den);
        map.put("JFK", jfk);
        map.put("SFO", sfo);
        map.put("BRW", brw);

        System.out.println("Shortest Path: ");

        LinkedList<Vertex> path1 = new LinkedList<>();

        int shortestPath = getShortestPath(jfk, brw, path1);

        //TODO: Add logic to catch an airport that doesn't connect

        while(!path1.isEmpty()) {
            System.out.println(path1.pop());
        }
        System.out.println(shortestPath);
        System.out.println();


        System.out.println("Fastest Path: ");
        LinkedList<Vertex> path2 = new LinkedList<>();

        int fastestPath = getFastestPath(jfk, brw, path2);

        //TODO: Add logic to catch an airport that doesn't connect


        while(!path2.isEmpty()) {
            System.out.println(path2.pop());
        }

        System.out.println(fastestPath);






    }


    static int getFastestPath(Vertex source, Vertex destination, LinkedList<Vertex> path) {

        //Reset visited status
        for(Vertex v: map.values()) {
            v.unvisit();
        }

        boolean done = false;

        //LL for holding vertices in queue
        LinkedList<Vertex> queue = new LinkedList<>();

        //Mark source as visited
        source.visit();

        queue.add(source);

        while(!done && !queue.isEmpty()){

            Vertex front = queue.pollFirst();
            Iterator neighbors = front.getNeighborIterator();
            Iterator neighborWeight = front.getWeightIterator();

            while(!done && neighbors.hasNext() && neighborWeight.hasNext()){

                Vertex nextNeighbor = (Vertex) neighbors.next();
                double weight = (double)neighborWeight.next();

                if(!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();
                    nextNeighbor.setPredecessor(front);
                    Airport airport = (Airport) nextNeighbor.getLabel();
                    nextNeighbor.setCost(weight + nextNeighbor.getPredecessor().getCost() + airport.getLayover());
                    queue.add(nextNeighbor);
                }

                if(nextNeighbor.equals(destination)) {
                    done = true;
                }
            }
        }

        Airport airport = (Airport) destination.getLabel();
        int pathLength = (int)destination.getCost() - airport.getLayover();

        path.push(destination);

        Vertex vertex = destination;

        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex);
        }

        return pathLength;

    }

    static int getShortestPath(Vertex source, Vertex destination, LinkedList<Vertex> path) {

        //Reset visited status
        for(Vertex v: map.values()) {
            v.unvisit();
        }

        boolean done = false;

        PriorityQueue<VertexPriorityWrapper> queue = new PriorityQueue<>();

        queue.add(new VertexPriorityWrapper(source,0, null));

        while(!done && !queue.isEmpty()) {
            VertexPriorityWrapper frontWrapper = queue.remove();
            Vertex front = frontWrapper.getVertex();

            if(!front.isVisited()) {
                front.visit();
                front.setCost(frontWrapper.getCost());
                front.setPredecessor(frontWrapper.getPrevious());

                if(front.equals(destination)) {
                    done = true;
                }else {

                    Iterator<Double> edgeWeights = front.getWeightIterator();
                    Iterator<Vertex> neighbors = front.getNeighborIterator();

                    while(neighbors.hasNext()) {

                        Vertex nextNeighbor = neighbors.next();
                        double weightOfEdge = edgeWeights.next();

                        if (!nextNeighbor.isVisited()) {

                            double nextCost = weightOfEdge + front.getCost(); // +2 here?

                            queue.add(new VertexPriorityWrapper(nextNeighbor,nextCost,front));
                        }

                    }
                }
            }
        }

        int pathCost = (int)destination.getCost();
        path.push(destination);

        Vertex vertex = destination;

        while(vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex);
        }

        return pathCost;
    }


}
