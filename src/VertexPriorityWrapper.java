public class VertexPriorityWrapper implements Comparable{

    Vertex vertex;
    double cost;
    Vertex previous;

    public VertexPriorityWrapper(Vertex vertex, double cost, Vertex previous) {
        this.vertex = vertex;
        this.cost = cost;
        this.previous = previous;
    }


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

    @Override
    public int compareTo(Object o) {

        if (o instanceof VertexPriorityWrapper) {

            VertexPriorityWrapper wrapper = (VertexPriorityWrapper)o;
            if (this.cost > wrapper.getCost()) {
                return 1;
            } else if (this.cost < wrapper.getCost()) {
                return -1;
            }

            return 0;
        }

        return 0;
    }
}