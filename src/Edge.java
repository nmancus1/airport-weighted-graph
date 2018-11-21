public class Edge {

    private int weight;
    private Vertex pointA;
    private Vertex pointB;

    public Edge() {

    }

    public Edge(int weight, Vertex start, Vertex pointB) {
        this.weight = weight;
        this.pointA = start;
        this.pointB = pointB;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vertex getPointA() {
        return pointA;
    }

    public void setPointA(Vertex start) {
        this.pointA = start;
    }

    public Vertex getPointB() {
        return pointB;
    }

    public void setPointB(Vertex pointB) {
        this.pointB = pointB;
    }

    @Override
    public String toString() {
        return pointA + " is connected to " + pointB;
    }
}
