import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
/**
 * A class that represents a vertex in a graph.
 *
 * @author Frank M. Carrano
 * @version 2.0
 */
class Vertex<T> implements java.io.Serializable
{
    private T label;
    private LinkedList<Edge> edgeList;                 // edges to neighbors
    private boolean visited;                          // true if visited
    private Vertex<T> previousVertex;        // on path to this vertex
    private double cost;                              // of path to this vertex


    public Vertex(T vertexLabel)
    {
        label = vertexLabel;
        edgeList = new LinkedList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    public T getLabel()
    {
        return label;
    } // end getLabel

    public boolean connect(Vertex<T> endVertex,
                           double edgeWeight)
    {
        boolean result = false;

        if (!this.equals(endVertex))
        { // vertices are distinct
            Iterator<Vertex<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext())
            {
                Vertex<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor))
                    duplicateEdge = true;
            } // end while

            if (!duplicateEdge)
            {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    public boolean connect(Vertex<T> endVertex)
    {
        return connect(endVertex, 0);
    } // end connect

    public Iterator<Vertex<T>> getNeighborIterator()
    {
        return new neighborIterator();
    } // end getNeighborIterator

    public Iterator<Double> getWeightIterator()
    {
        return new weightIterator();
    } // end getWeightIterator

    public boolean hasNeighbor()
    {
        return !edgeList.isEmpty();
    } // end hasNeighbor

    public Vertex<T> getUnvisitedNeighbor()
    {
        Vertex<T> result = null;

        Iterator<Vertex<T>> neighbors = getNeighborIterator();
        while (neighbors.hasNext() && (result == null) )
        {
            Vertex<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited())
                result = nextNeighbor;
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    public boolean hasPredecessor()
    {
        return previousVertex != null;
    } // end hasPredecessor

    public void setPredecessor(Vertex<T> predecessor)
    {
        previousVertex = predecessor;
    } // end setPredecessor

    public Vertex<T> getPredecessor()
    {
        return previousVertex;
    } // end getPredecessor

    public void visit()
    {
        visited = true;
    } // end visit

    public void unvisit()
    {
        visited = false;
    } // end unvisit

    public boolean isVisited()
    {
        return visited;
    } // end isVisited

    public double getCost()
    {
        return cost;
    } // end getCost

    public void setCost(double newCost)
    {
        cost = newCost;
    } // end setCost

    public boolean equals(Object other)
    {
        boolean result;

        if ((other == null) || (getClass() != other.getClass()))
            result = false;
        else
        {
            Vertex<T> otherVertex = (Vertex<T>)other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    public String toString()
    {
        return label.toString();
    } // end toString

    public void display() // for testing
    {
        System.out.print(label + " " );
        Iterator<Vertex<T>> vertexIterator = getNeighborIterator();
        Iterator<Double> weightIterator = getWeightIterator();

        while (vertexIterator.hasNext())
        {
            Vertex<T> vert = (Vertex<T>)vertexIterator.next();
            System.out.print(vert + " " + weightIterator.next() + " ");

        } // end while
        System.out.println();
    } // end display

    // 31.10
    public class Edge
    {
        private Vertex<T> vertex; // end vertex
        private double weight;

        protected Edge(Vertex<T> endVertex, double edgeWeight)
        {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        protected Vertex<T> getEndVertex()
        {
            return vertex;
        } // end getEndVertex

        protected double getWeight()
        {
            return weight;
        } // end getWeight

        public String toString() // for testing only
        {
            return vertex.toString() + " " + weight;
        } // end toString
    } // end Edge

    private class neighborIterator implements Iterator<Vertex<T>>
    {
        private Iterator<Edge> edges;

        private neighborIterator()
        {
            edges = edgeList.listIterator(0);
        } // end default constructor

        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        public Vertex<T> next()
        {
            Vertex<T> nextNeighbor = null;

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            }
            else
                throw new NoSuchElementException();

            return nextNeighbor;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end neighborIterator

    private class weightIterator implements Iterator<Double>
    {
        private Iterator<Edge> edges;

        private weightIterator()
        {
            edges = edgeList.listIterator(0);
        } // end default constructor

        public boolean hasNext()
        {
            return edges.hasNext();
        } // end hasNext

        public Double next()
        {
            Double edgeWeight = new Double(0);

            if (edges.hasNext())
            {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            }
            else
                throw new NoSuchElementException();

            return edgeWeight;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end weightIterator
} // end Vertex
