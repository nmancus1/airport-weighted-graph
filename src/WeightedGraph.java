import java.util.*;

public class WeightedGraph {

    private LinkedList<Edge> edges;
    private LinkedList<Vertex> vertices;

    public WeightedGraph() {
        this.edges = new LinkedList<>();
        this.vertices = new LinkedList<>();
    }

    public void addEdge(int weight, Vertex source, Vertex destination) {

        Edge edge = new Edge(weight, source, destination);

        this.edges.add(edge);
    }

    public LinkedList<Edge> getEdges() {
        return edges;
    }

    public LinkedList<Edge> findEdges(Vertex vertex) {

        LinkedList connectedEdges = new LinkedList();

        ListIterator iterator = edges.listIterator(0);

        while (iterator.hasNext()) {

            Edge thisEdge = (Edge) iterator.next();

            if (((thisEdge.getPointA() == vertex) && !(thisEdge.getPointB() == vertex)) ||
                    (!(thisEdge.getPointA() == vertex) && (thisEdge.getPointB() == vertex))) {
                connectedEdges.add(thisEdge);
            }
        }

        return connectedEdges;
    }

    public void setEdges(LinkedList<Edge> edges) {
        this.edges = edges;
    }

    public LinkedList<Vertex> getVertices() {
        return vertices;
    }

    public void addVertex(Vertex vertex) {
        this.vertices.add(vertex);
    }

    public void BFS(Vertex source) {


        //LinkedList<Vertex>
        LinkedList<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> visitedVertices = new ArrayList<>();

        queue.add(source);
        visitedVertices.add(source);

        while (!queue.isEmpty()) {

            source = queue.poll();
            System.out.println(source + " ");

            Iterator iterator = edges.listIterator();

            while (iterator.hasNext()) {

                Edge thisEdge = (Edge) iterator.next();

                if (!(visitedVertices.contains(thisEdge.getPointA()))) {
                    visitedVertices.add(thisEdge.getPointA());
                    queue.add(thisEdge.getPointA());
                }

            }
        }

    }

    public LinkedList<Vertex> DFS(Vertex source, Vertex destination) {


        LinkedList<Vertex> queue = new LinkedList<>();
        LinkedList<Vertex> visited = new LinkedList<>();

        queue.add(source);

        while (!queue.isEmpty()) {

            Vertex vertex = queue.remove();
            if (source.equals(destination)) {
                return visited;
            }

            if (visited.contains(vertex)) {
                continue;
            }

            visited.add(vertex);

            for (Edge edge : edges) {
                if (edge.getPointA().compareTo(vertex) > 0) {
                    queue.add(edge.getPointB());

                    if (edge.getPointB().compareTo(destination) > 0) {
                        visited.add(destination);
                        return visited;
                    }
                }
            }
        }

        return null;
    }

    public int recursiveDFS(LinkedList visited,Vertex source, Vertex destination) {
        boolean[] searched = new boolean[vertices.size()];
        int hours = 0;

        return recursiveDFS(visited, source, destination, searched, hours);
    }

    public int recursiveDFS(LinkedList visited, Vertex source, Vertex destination, boolean[] searched, int hours) {


        searched[vertices.indexOf(source)] = true;


        if (source.compareTo(destination) > 0) {
            visited.add(destination);
            System.out.println(visited);
            System.out.println(hours);
        }

        visited.add(source);

        //Look through all edges
        for (Edge edge : edges) {

            //If the edges have the same source as the vertex were looking at
            if (edge.getPointA().compareTo(source) > 0) {
                int i = vertices.indexOf(edge.getPointB());

                if (!searched[i]) {

                    hours += edge.getWeight();


                    searched[vertices.indexOf(edge.getPointB())] = true;

                    recursiveDFS(visited, edge.getPointB(), destination, searched, hours);

                }

            }
        }

        return hours;
    }

    public LinkedList<Vertex> interativeDFS(Vertex source, Vertex destination) {
        Stack<Vertex> stack = new Stack();
        LinkedList visited = new LinkedList();

        stack.push(source);

        while(!stack.empty()) {
            Vertex current = stack.pop();
            visited.add(current);

            for(Edge edge: edges) {
                if(edge.getPointA().compareTo(current) > 0) {
                    if(edge.getPointB().compareTo(destination) > 0) {
                        return visited;
                    }
                    stack.push(current);
                }
            }
        }
        return null;
    }

}





