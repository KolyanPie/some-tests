package labs.home_work;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private Graph graph = new Graph();
    private int[] weightMatrix = {11, 2, 4, 5, 1, 2, 5, 3, 3, 3, 6, 4, 3};

    {
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addVertex();
        graph.addEdge(0, 0);
        graph.addEdge(0, 5);
        graph.addEdge(0, 4);
        graph.addEdge(0, 3);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(3, 2);
        graph.addEdge(3, 1);
        graph.addEdge(4, 3);
        graph.addEdge(4, 1);
        graph.addEdge(5, 2);
        graph.addEdge(5, 4);
    }

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите x");
        int x = scanner.nextInt();
        main.printCycles(main.graph, x);
    }

    private void printCycles(Graph graph, int x) {
        for (int i = 0; i < graph.countVertex(); i++) {
            ArrayList<Path> list = findCycles(graph, i);
            for (Path p : list) {
                if (p.length == x) {
                    System.out.println(p.path);
                }
            }
        }
    }

    private ArrayList<Path> findCycles(Graph graph, int vertex) {
        ArrayList<Path> list = new ArrayList<>();
        ArrayList<ArrayList<Graph.Incidence>> incidenceMatrix = graph.getIncidenceMatrix();
        for (int i = 0; i < graph.countEdges(); i++) {
            if (incidenceMatrix.get(vertex).get(i).equals(Graph.Incidence.OUT)) {
                int nextVertex = graph.getVertexEnd(i);
                if (nextVertex == vertex) {
                    System.out.println("12345");
                    Cycle cycle = new Cycle(vertex);
                    cycle.step(vertex, weightMatrix[i]);
                    list.add(new Path(cycle));
                } else {
                    findCycles(list, graph, i, vertex);
                }
            }
        }
        return list;
    }

    private void findCycles(ArrayList<Path> list, Graph graph, int edge, int beginVertex) {
        Cycle cycle = new Cycle(beginVertex);
        cycle.step(graph.getVertexEnd(edge), weightMatrix[edge]);
        findCycles(list, graph, edge, beginVertex, cycle);
    }

    private void findCycles(ArrayList<Path> list, Graph graph, int edge, int beginVertex, Cycle link) {
        ArrayList<ArrayList<Graph.Incidence>> incidenceMatrix = graph.getIncidenceMatrix();
        for (int i = 0; i < graph.countEdges(); i++) {
            if (incidenceMatrix.get(graph.getVertexEnd(edge)).get(i).equals(Graph.Incidence.OUT)) {
                int nextVertex = graph.getVertexEnd(i);
                if (link.step(nextVertex, weightMatrix[i])) {
                    if (nextVertex == beginVertex) {
                        list.add(new Path(link));
                    } else {
                        findCycles(list, graph, i, beginVertex, link);
                    }
                    link.undoStep(weightMatrix[i]);
                }
            }
        }
    }

    private class Path {
        private String path;
        private int length;

        private Path(Cycle cycle) {
            path = cycle.toString();
            length = cycle.length;
        }
    }

    private class Cycle implements Cloneable {
        private ArrayList<Integer> path;
        private int length;
        private int root;

        private Cycle(int vertex) {
            root = vertex;
            path = new ArrayList<>();
            length = 0;
        }

        private boolean step(int vertex, int stepLength) {
            if (path.contains(vertex)) {
                return false;
            }
            length += stepLength;
            path.add(vertex);
            return true;
        }

        private void undoStep(int stepLength) {
            path.remove(path.size() - 1);
            length -= stepLength;
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(root);
            for (int i : path) {
                stringBuilder.append("->").append(i);
            }
            return stringBuilder.toString();
        }

    }
}
