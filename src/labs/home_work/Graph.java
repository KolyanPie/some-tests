package labs.home_work;

import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Incidence>> incidenceMatrix;

    public ArrayList<ArrayList<Incidence>> getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public Graph() {
        incidenceMatrix = new ArrayList<>();
    }

    public int countVertex() {
        return incidenceMatrix.size();
    }

    public int countEdges() {
        if (countVertex() > 0) {
            return incidenceMatrix.get(0).size();
        }
        return 0;
    }

    public void addVertex() {
        ArrayList<Incidence> temp = new ArrayList<>(countEdges());
        for (int i = 0; i < countEdges(); i++) {
            temp.add(Incidence.NONE);
        }
        incidenceMatrix.add(temp);
    }

    public void addEdge(int vStart, int vEnd) throws IndexOutOfBoundsException {
        if (vStart < 0 || vStart > incidenceMatrix.size() - 1 || vEnd < 0 || vEnd > incidenceMatrix.size() - 1) {
            throw new IndexOutOfBoundsException();
        }
        if (vStart == vEnd) {
            System.out.println("cycle in v = " + vStart);
            for (int i = 0; i < vStart; i++) {
                incidenceMatrix.get(i).add(Incidence.NONE);
            }
            incidenceMatrix.get(vStart).add(Incidence.LOOP);
            for (int i = vStart + 1; i < incidenceMatrix.size(); i++) {
                incidenceMatrix.get(i).add(Incidence.NONE);
            }
            return;
        }
        for (int i = 0; i < incidenceMatrix.size(); i++) {
            if (i == vStart) {
                incidenceMatrix.get(i).add(Incidence.OUT);
                continue;
            }
            if (i == vEnd) {
                incidenceMatrix.get(i).add(Incidence.IN);
                continue;
            }
            incidenceMatrix.get(i).add(Incidence.NONE);
        }
    }

    public int getVertexEnd(int edge) throws IndexOutOfBoundsException {
        if (edge < 0 || edge > countEdges() - 1) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < incidenceMatrix.size(); i++) {
            if (incidenceMatrix.get(i).get(edge).equals(Incidence.IN)) {
                return i;
            }
        }
        throw new RuntimeException("have not end of this edge");
    }

    public enum Incidence {
        IN,
        OUT,
        LOOP,
        NONE
    }
}
