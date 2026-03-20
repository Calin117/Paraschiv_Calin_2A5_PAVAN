package org.example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class City {
    private String name;
    private Set<Intersection> intersections = new HashSet<>();
    private List<Street> streets = new LinkedList<>();

    public City(String name) {
        this.name = name;
    }

    public void addIntersection(Intersection i) { intersections.add(i); }
    public void addStreet(Street s) { streets.add(s); }

    public Set<Intersection> getIntersections() { return intersections; }
    public List<Street> getStreets() { return streets; }

    public void afiseazaStrazi(double minLength) {
        Map<Intersection, Long> gradNoduri = streets.stream()
                .flatMap(s -> Stream.of(s.getStart(), s.getEnd()))
                .collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        System.out.println("\nStrazi mai lungi de " + minLength + " cu minim 3 conexiuni:");

        streets.stream()
                .filter(s -> s.getLength() > minLength)
                .filter(s -> {
                    long vecini = gradNoduri.get(s.getStart()) + gradNoduri.get(s.getEnd()) - 2;
                    return vecini >= 3;
                })
                .forEach(System.out::println);
    }
}