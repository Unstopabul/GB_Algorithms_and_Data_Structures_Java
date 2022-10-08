package ru.gb.hw7;

public class App {
    public static void main(String[] args) {
        Graph graph = new GraphImpl();

        for (int i = 0; i < 10; i++) {
            graph.addVertex(String.valueOf(i + 1));
        }

        graph.addEdge("1", "2", 3);
        graph.addEdge("1", "3", 4);
        graph.addEdge("1", "4", 2);

        graph.addEdge("2", "6", 3);
        graph.addEdge("3", "6", 6);
        graph.addEdge("4", "5", 5);
        graph.addEdge("4", "6", 2);
        graph.addEdge("5", "7", 6);
        graph.addEdge("5", "9", 12);

        graph.addEdge("6", "7", 8);
        graph.addEdge("6", "8", 7);
        graph.addEdge("7", "10", 4);
        graph.addEdge("8", "10", 3);
        graph.addEdge("9", "10", 11);


        Graph.FastestRouteRet fastestRoute = graph.getFastestRoute("1", "10");
        System.out.println("Из 1 в 10");
        System.out.println("Самый быстрый маршрут: " + fastestRoute.route);
        System.out.println("Расстояние: " + fastestRoute.distance);

        fastestRoute = graph.getFastestRoute("1", "7");
        System.out.println("\nИз 1 в 7");
        System.out.println("Самый быстрый маршрут: " + fastestRoute.route);
        System.out.println("Расстояние: " + fastestRoute.distance);
    }
}
