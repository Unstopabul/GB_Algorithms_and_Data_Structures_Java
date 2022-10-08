package ru.gb.hw7;

import java.util.ArrayList;
import java.util.function.Consumer;

public interface Graph {

    boolean addVertex(String label);

    // FIXME: 03.10.2022 Добавить возможность указывать вес ребра.
    //  Вес должен попадать в матрицу смежности.
    boolean addEdge(String from, String to, int weight);

    // FIXME: 03.10.2022 * Реализовать поиск кратчайшего расстояния между двумя узлами.
    FastestRouteRet getFastestRoute(String from, String to);

    public class FastestRouteRet {
        public ArrayList<String> route;
        public int distance;

        public FastestRouteRet(ArrayList<String> route, int distance) {
            this.route = route;
            this.distance = distance;
        }
    }
}
