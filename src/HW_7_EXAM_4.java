/**
 * This program fulfills the requirements for HW #7 AND Exam #4.   It finds both the fastest and shortest paths
 * between airports and prints the information to the console.
 */

import java.util.*;

public class HW_7_EXAM_4 {

    static HashMap<String, Vertex> map = new HashMap();

    public static void main (String[] args) {

        //Create vertices, which contain airports
        Vertex<Airport> atl = new Vertex(new Airport("ATL"));
        Vertex<Airport> lax = new Vertex(new Airport("LAX"));
        Vertex<Airport> ord = new Vertex(new Airport("ORD"));
        Vertex<Airport> dfw = new Vertex(new Airport("DFW"));
        Vertex<Airport> den = new Vertex(new Airport("DEN"));
        Vertex<Airport> jfk = new Vertex(new Airport("JFK"));
        Vertex<Airport> sfo = new Vertex(new Airport("SFO"));
        Vertex<Airport> brw = new Vertex(new Airport("BRW"));


        /***Connect airports**/

        //ATL
        atl.connect(dfw,2);
        atl.connect(jfk,2);

        //LAX
        lax.connect(dfw,3);
        lax.connect(jfk,7);
        lax.connect(sfo,1);

        //ORD
        ord.connect(den, 2);
        ord.connect(jfk,2);

        //DFW
        dfw.connect(atl,2);
        dfw.connect(lax,3);

        //DEN
        den.connect(ord,2);
        den.connect(sfo,3);

        //JFK
        jfk.connect(atl,2);
        jfk.connect(lax,7);
        jfk.connect(ord,2);
        jfk.connect(sfo,7);

        //SFO
        sfo.connect(lax,1);
        sfo.connect(den,3);
        sfo.connect(jfk,7);

        //Add to hashmap
        map.put("ATL", atl);
        map.put("LAX", lax);
        map.put("ORD", ord);
        map.put("DFW", dfw);
        map.put("DEN", den);
        map.put("JFK", jfk);
        map.put("SFO", sfo);
        map.put("BRW", brw);

        //Evaluate and print paths to console
        pathPrinter(jfk, brw);
        pathPrinter(jfk, sfo);
        pathPrinter(ord, den);
        pathPrinter(lax, ord);
        pathPrinter(dfw, sfo);

        //Print connected flight info to console
        System.out.println("Connected flights:\n");

        for (Vertex v : map.values()) {
            Iterator neighorIterator = v.getNeighborIterator();

            System.out.println("***" + v + "***");
            while (neighorIterator.hasNext()) {
                System.out.println(v + " is connected to " + neighorIterator.next());
            }
            System.out.println();

        }



            }

    /**
     * This method calls both path methods and displays the relevant data
     * @param source the source vertex
     * @param destination the destination vertex
     */
    static void pathPrinter(Vertex source, Vertex destination) {

        //Linked lists to pass to path methods
        LinkedList<Vertex> path1 = new LinkedList<>();
        LinkedList<Vertex> path2 = new LinkedList<>();

        /**SHORTEST PATH**/
        //Display info
        System.out.println("Shortest Path between " + source + " and " + destination + ":");

        //Get shortest path weight
        int shortestPath = getShortestPath(source, destination, path1);

        //Print results of getShortestPath()
        if (shortestPath < 1) {                 //if airport isn't connected
            System.out.println("There is no path between " + source + " and " + destination + ".");
            System.out.println();
        } else {                                //print path
            while (!path1.isEmpty()) {

                Vertex vertex = path1.pop();
                System.out.print(vertex);
                if (vertex != destination) {
                    System.out.print(" > ");
                }

            }

            //Print length(weight) of path
            System.out.println();
            System.out.println("Total length of path: " + shortestPath);
            System.out.println();
        }

        /**FASTEST PATH**/
        //Display info
        System.out.println("Fastest Path between " + source + " and " + destination + ":");

        //Evaluate fastest path
        int fastestPath = getFastestPath(source, destination, path2);

        //Print results
        if (fastestPath < 1) {              //if airport isn't connected
            System.out.println("There is no path between " + source + " and " + destination + ".");
            System.out.println();
        } else {                            //print path
            while (!path2.isEmpty()) {

                Vertex vertex = path2.pop();
                System.out.print(vertex);
                if (vertex != destination) {
                    System.out.print(" > ");
                }
            }

            //Print time(cost) of path
            System.out.println();
            System.out.println("Total time of path: " + fastestPath + ".");
            System.out.println();


        }
    }

    /**
     * This method finds the fastest path from one vertex to another.  This implementation
     * borrows from Frank Carrano's getCheapestPath() method.
     * @param source the source vertex
     * @param destination the destination vertex
     * @param path linked list to hold vertices
     * @return integer that represents the number of hours of complete travel time
     */
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

        //Add source to queue
        queue.add(source);

        //Keep going until done is true and queue is empty
        while(!done && !queue.isEmpty()){

            //Get first element of queue
            Vertex front = queue.pollFirst();

            //Set up iterators
            Iterator neighbors = front.getNeighborIterator();
            Iterator neighborWeight = front.getWeightIterator();

            //Keep going until done and while iterators still have more elements
            while(!done && neighbors.hasNext() && neighborWeight.hasNext()){

                //Set neighbor and neighbor weight
                Vertex nextNeighbor = (Vertex) neighbors.next();
                double weight = (double)neighborWeight.next();

                //Check if this neighbor has been visited
                if(!nextNeighbor.isVisited()) {
                    nextNeighbor.visit();                           //set to visited
                    nextNeighbor.setPredecessor(front);             //set previous vertex
                    Airport airport = (Airport) nextNeighbor.getLabel();        //get airport for evaluating layover

                    //Set cost including layover time
                    nextNeighbor.setCost(weight + nextNeighbor.getPredecessor().getCost() + airport.getLayover());

                    //Add next neighbor to queue
                    queue.add(nextNeighbor);
                }

                //Check if we're done(found our destination)
                if(nextNeighbor.equals(destination)) {
                    done = true;
                }
            }
        }

        //Do some math on path length to account for no layover at destination
        Airport airport = (Airport) destination.getLabel();
        int pathLength = (int)destination.getCost() - airport.getLayover();

        //Add destination to path
        path.push(destination);

        //Just here to help readability
        Vertex vertex = destination;

        //Go backwards through visited vertices, adding them to linked list
        while (vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex);
        }

        ///Return total amount of hours
        return pathLength;

    }

    /**
     * This method finds the fastest path from one vertex to another.  This implementation
     * borrows from Frank Carrano's getShortestPath() method.
     * @param source the source vertex
     * @param destination the destination vertex
     * @param path linked list to hold vertices
     * @return integer that represents the length of the trip
     */
    static int getShortestPath(Vertex source, Vertex destination, LinkedList<Vertex> path) {

        //Reset visited status
        for(Vertex v: map.values()) {
            v.unvisit();
        }

        boolean done = false;

        //Need priority queue here, to evaluate shortest path using wrapped vertices
        PriorityQueue<VertexPriorityWrapper> queue = new PriorityQueue<>();

        //Add wrapped vertex to queue with no cost and no previous vertex
        queue.add(new VertexPriorityWrapper(source,0, null));

        //Keep going until done or queue is empty
        while(!done && !queue.isEmpty()) {

            //Create wrapped vertex
            VertexPriorityWrapper frontWrapper = queue.remove();
            Vertex front = frontWrapper.getVertex();

            //Check if this vertex has been visited
            if(!front.isVisited()) {
                front.visit();                          //set to visited
                front.setCost(frontWrapper.getCost());  //set cost from wrapper to vertex
                front.setPredecessor(frontWrapper.getPrevious());       //set previous from wrapper to vertex

                //If front is destination, we're done
                if(front.equals(destination)) {
                    done = true;

                    //If not, start adding neighbors to queue
                }else {

                    //Set up iterators
                    Iterator<Double> edgeWeights = front.getWeightIterator();
                    Iterator<Vertex> neighbors = front.getNeighborIterator();

                    //While iterators still have elements
                    while(neighbors.hasNext() && edgeWeights.hasNext()) {

                        //Get next neighboring vertex and get weight
                        Vertex nextNeighbor = neighbors.next();
                        double weightOfEdge = edgeWeights.next();

                        //Check if next neighbor has been visited
                        if (!nextNeighbor.isVisited()) {

                            //Set nextCost from the weight of this edge plus the cost of all previous vertices
                            double nextCost = weightOfEdge + front.getCost();

                            //Add wrapped vertex to queue, with appropriate weight and correct previous vertex
                            queue.add(new VertexPriorityWrapper(nextNeighbor,nextCost,front));
                        }

                    }
                }
            }
        }

        //Set path cost
        int pathCost = (int)destination.getCost();
        path.push(destination);

        //Readability
        Vertex vertex = destination;

        //Push all vertices on shortest path to linked list
        while(vertex.hasPredecessor()) {
            vertex = vertex.getPredecessor();
            path.push(vertex);
        }

        //Return pathCost
        return pathCost;
    }


}
