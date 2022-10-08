package ru.gb.hw7;

import java.util.*;

public class GraphImpl implements Graph {

    private final List<String> vertexes = new ArrayList<>();
    private final List<List<Integer>> adjMatrix = new ArrayList<>();

    @Override
    public boolean addVertex(String label) {
        Objects.requireNonNull(label);

        if (indexOf(label) >= 0) {
            return false;
        }

        vertexes.add(label);

        for (List<Integer> row : adjMatrix) {
            row.add(-1);
        }

        int size = vertexes.size();
        List<Integer> newRow = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            newRow.add(-1);
        }

        adjMatrix.add(newRow);

        return true;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexes.size(); i++) {
            if (label.equals(vertexes.get(i))) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean addEdge(String from, String to, int weight) {
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        if (weight <= 0)    // допущение
            return false;

        int indexOfFrom = indexOf(from);
        int indexOfTo = indexOf(to);

        if (indexOfFrom == -1 || indexOfTo == -1) {
            return false;
        }

        if (indexOfFrom == indexOfTo) {
            return false;
        }

        List<Integer> fromEdges = adjMatrix.get(indexOfFrom);
        fromEdges.set(indexOfTo, weight);

        return true;
    }

    @Override
    public FastestRouteRet getFastestRoute(String from, String to) {
        ArrayList<String> routeStart = new ArrayList<>();
        routeStart.add(from);
        FastestRouteRet routeRetStart = new FastestRouteRet(routeStart, 0);
        RoutesInfo routesInfo = new RoutesInfo();
        getFastestRoute(from, to, routeRetStart, routesInfo);

        return routesInfo.routes.get(routesInfo.routes.size() - 1);
    }

    private void getFastestRoute(String from, String to, FastestRouteRet currentRoute, RoutesInfo routesInfo) {
        int indexOfFrom = indexOf(from);

        List<String> siblings = getSibling(from);
        for (String sibling : siblings) {
            int weight = adjMatrix.get(indexOfFrom).get(indexOf(sibling));
            int newDistance = currentRoute.distance + weight;
            if (routesInfo.minDistance > 0 && newDistance >= routesInfo.minDistance) {
                continue;   // один из уже найденных маршрутов короче текущего, дальше не идём
            }
            ArrayList<String> newRoute = (ArrayList<String>) currentRoute.route.clone();
            newRoute.add(sibling);
            FastestRouteRet routeRet = new FastestRouteRet(newRoute, newDistance);
            if (sibling.equals(to)) {
                routesInfo.routes.add(routeRet);
                routesInfo.minDistance = newDistance;
                continue;
            }
            getFastestRoute(sibling, to, routeRet, routesInfo);
        }
    }

    private class RoutesInfo {
        public ArrayList<FastestRouteRet> routes = new ArrayList<>();
        public int minDistance = -1;
    }

    private List<String> getSibling(String label) {
        int indexOfLabel = indexOf(label);
        List<Integer> labelRow = adjMatrix.get(indexOfLabel);

        List<String> siblings = new ArrayList<>();
        for (int i = 0; i < labelRow.size(); i++) {
            if (labelRow.get(i) > 0) {
                String sibling = vertexes.get(i);
                siblings.add(sibling);
            }
        }

        return siblings;
    }
}
