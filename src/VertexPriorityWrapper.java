/**
 * This class wraps the vertex class so that it can be sorted by a priority queue, using compareTo
 *
 * @author Nick Mancuso
 * @since 11/22/18
 */

public class VertexPriorityWrapper implements Comparable {

    //Data fields
    Vertex vertex;
    double cost;
    Vertex previous;

    //Constructor
    public VertexPriorityWrapper(Vertex vertex, double cost, Vertex previous) {
        this.vertex = vertex;
        this.cost = cost;
        this.previous = previous;
    }

    //Accessors and Mutators
    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }

    /**
     * This method enables VertexPriorityWrappers to be sorted correctly in a priority queue
     * @param o what we're comparing this VPW to
     * @return int representing the relationship between the two VPWs
     */
    @Override
    public int compareTo(Object o) {

        //Make sure we're comparing VPW
        if (o instanceof VertexPriorityWrapper) {

            //Readability
            VertexPriorityWrapper wrapper = (VertexPriorityWrapper) o;

            //If this VPW cost is greater than what we're comparing it to
            if (this.cost > wrapper.getCost()) {
                return 1;

                //If this VPW cost is less than what we're comparing it to
            } else if (this.cost < wrapper.getCost()) {
                return -1;
            }

        }
        //If they're the same or what we're passing in isn't a VPW
        return 0;
    }
}