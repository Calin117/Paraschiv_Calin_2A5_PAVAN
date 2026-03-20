package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.*;

public class NetworkAlgorithm {
    private City city;

    public NetworkAlgorithm(City city) {
        this.city = city;
    }

    public void rezolva(int k) {
        Graph<Intersection, DefaultWeightedEdge> graf = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

         for (Intersection i : city.getIntersections()) {
            graf.addVertex(i);
        }

        for (Street s : city.getStreets()) {
            DefaultWeightedEdge muchie = graf.addEdge(s.getStart(), s.getEnd());
            if (muchie != null) {
                graf.setEdgeWeight(muchie, s.getLength());
            }
        }

        KruskalMinimumSpanningTree<Intersection, DefaultWeightedEdge> kruskal = new KruskalMinimumSpanningTree<>(graf);
        var optim = kruskal.getSpanningTree();

        List<Double> costuri = new ArrayList<>();
        costuri.add(optim.getWeight());

        for (DefaultWeightedEdge muchie : optim.getEdges()) {
            double costVechi = graf.getEdgeWeight(muchie);
            Intersection a = graf.getEdgeSource(muchie);
            Intersection b = graf.getEdgeTarget(muchie);

            graf.removeEdge(muchie);

            var kruskalSecundar = new KruskalMinimumSpanningTree<>(graf);
            try {
                var alternativ = kruskalSecundar.getSpanningTree();
                 if (alternativ.getEdges().size() == city.getIntersections().size() - 1) {
                    costuri.add(alternativ.getWeight());
                }
            } catch (Exception e) {}

            DefaultWeightedEdge muchieNoua = graf.addEdge(a, b);
            graf.setEdgeWeight(muchieNoua, costVechi);
        }

        Collections.sort(costuri);
        System.out.println("\nTop " + k + " solutii de instalare a muchiilor:");
        for (int i = 0; i < Math.min(k, costuri.size()); i++) {
            System.out.println("Solutia " + (i + 1) + " -> Cost: " + String.format("%.2f", costuri.get(i)));
        }
    }
}