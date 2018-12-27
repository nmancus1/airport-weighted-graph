/**
 * A class that represents a vertex in a graph.  This implementation is a modified form of
 * Frank Carrano's VertexInterface and Vertex class from the textbook.
 *
 * @author Frank Carrano (modified by Nick Mancuso)
 * @since 11/22/18
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class Vertex<T> implements java.io.Serializable {
    private T label;                                    // type of data in vertex
    private LinkedList<Edge> edgeList;                  // edges to neighbors
    private boolean visited;                            // true if visited
    private Vertex<T> previousVertex;                   // on path to this vertex
    private double cost;                                // of path to this vertex


    //Constructor
    public Vertex(T vertexLabel) {
        label = vertexLabel;
        edgeList = new LinkedList<>();
        visited = false;
        previousVertex = null;
        cost = 0;
    } // end constructor

    /**
     * Gets the data of this vertex
     * @return T
     */
    public T getLabel() {
        return label;
    } // end getLabel

    /**
     * This method connects vertices with an edge weight
     * @param endVertex
     * @param edgeWeight
     * @return
     */
    public boolean connect(Vertex<T> endVertex, double edgeWeight) {

        boolean result = false;

        if (!this.equals(endVertex)) { // vertices are distinct
            Iterator<Vertex<T>> neighbors = this.getNeighborIterator();
            boolean duplicateEdge = false;

            while (!duplicateEdge && neighbors.hasNext()) {
                Vertex<T> nextNeighbor = neighbors.next();
                if (endVertex.equals(nextNeighbor)) {
                    duplicateEdge = true;
                }
            } // end while

            if (!duplicateEdge) {
                edgeList.add(new Edge(endVertex, edgeWeight));
                result = true;
            } // end if
        } // end if

        return result;
    } // end connect

    /**
     * This method returns a neighbor iterator
     * @return
     */
    public Iterator<Vertex<T>> getNeighborIterator() {
        return new neighborIterator();
    } // end getNeighborIterator

    /**
     * This method returns a edge weight iterator
     * @return
     */
    public Iterator<Double> getWeightIterator() {
        return new weightIterator();
    } // end getWeightIterator

    /**
     * This method returns an unvisited neighbor
     * @return
     */
    public Vertex<T> getUnvisitedNeighbor() {
        Vertex<T> result = null;

        Iterator<Vertex<T>> neighbors = getNeighborIterator();
        while (neighbors.hasNext() && (result == null)) {
            Vertex<T> nextNeighbor = neighbors.next();
            if (!nextNeighbor.isVisited()) {
                result = nextNeighbor;
            }
        } // end while

        return result;
    } // end getUnvisitedNeighbor

    /**
     * This method checks if this vertex has a previous vertex
     * @return
     */
    public boolean hasPredecessor() {
        return previousVertex != null;
    } // end hasPredecessor

    /**
     * This method returns the previous vertex
     * @return
     */
    public Vertex<T> getPredecessor() {
        return previousVertex;
    } // end getPredecessor

    /**
     * This method sets a previous vertex for this vertex
     *
     * @param predecessor
     */
    public void setPredecessor(Vertex<T> predecessor) {
        previousVertex = predecessor;
    } // end setPredecessor

    /**
     * This method marks this vertex as visited for traversal
     */
    public void visit() {
        visited = true;
    } // end visit

    /**
     * This method marks this vertex as not visited, for traversal
     */
    public void unvisit() {
        visited = false;
    } // end unvisit

    /**
     * This method checks the visited status of this vertex
     * @return
     */
    public boolean isVisited() {
        return visited;
    } // end isVisited

    /**
     * This method gets the cost of visiting this vertex
     * @return
     */
    public double getCost() {
        return cost;
    } // end getCost

    /**
     * This method sets the cost of visiting this vertex
     * @param newCost
     */
    public void setCost(double newCost) {
        cost = newCost;
    } // end setCost

    /**
     * This method checks if two vertices are the same
     * @param other other vertex to compare
     * @return
     */
    public boolean equals(Object other) {
        boolean result;

        if ((other == null) || (getClass() != other.getClass())) {
            result = false;
        } else {
            Vertex<T> otherVertex = (Vertex<T>) other;
            result = label.equals(otherVertex.label);
        } // end if

        return result;
    } // end equals

    /**
     * This method returns the vertex data's toString method
     * @return
     */
    public String toString() {
        return label.toString();
    } // end toString

    /**
     * Inner class edge represents the paths between vertices
     */
    public class Edge {
        //Data fields
        private Vertex<T> vertex;
        private double weight;

        protected Edge(Vertex<T> endVertex, double edgeWeight) {
            vertex = endVertex;
            weight = edgeWeight;
        } // end constructor

        protected Vertex<T> getEndVertex() {
            return vertex;
        } // end getEndVertex

        protected double getWeight() {
            return weight;
        } // end getWeight

        public String toString() // for testing only
        {
            return vertex.toString() + " " + weight;
        } // end toString
    } // end Edge

    /**
     * Inner class neighborIterator creates an iterator to traverse elements of this vertex's
     * edge list and return vertex's neighbors
     */
    private class neighborIterator implements Iterator<Vertex<T>> {
        private Iterator<Edge> edges;

        private neighborIterator() {
            edges = edgeList.listIterator(0);
        } // end default constructor

        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext

        public Vertex<T> next() {
            Vertex<T> nextNeighbor = null;

            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                nextNeighbor = edgeToNextNeighbor.getEndVertex();
            } else {
                throw new NoSuchElementException();
            }

            return nextNeighbor;
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end neighborIterator

    /**
     * Inner class weightIterator creates an iterator to traverse the elements of this vertex's edge list
     * and return the weights of the edges between neighbors
     */
    private class weightIterator implements Iterator<Double> {
        private Iterator<Edge> edges;

        private weightIterator() {
            edges = edgeList.listIterator(0);
        } // end default constructor

        public boolean hasNext() {
            return edges.hasNext();
        } // end hasNext

        public Double next() {
            Double edgeWeight = new Double(0);

            if (edges.hasNext()) {
                Edge edgeToNextNeighbor = edges.next();
                edgeWeight = edgeToNextNeighbor.getWeight();
            } else {
                throw new NoSuchElementException();
            }

            return edgeWeight;
        } // end next

        public void remove() {
            throw new UnsupportedOperationException();
        } // end remove
    } // end weightIterator
} // end Vertex
