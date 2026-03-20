package org.example;

import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        City oras = new City("Bucuresti");

        System.out.println("Generam 10 noduri random");

        List<Intersection> listaNoduri = IntStream.rangeClosed(1, 10)
                .mapToObj(i -> new Intersection(faker.address().streetName()))
                .collect(Collectors.toList());

        for (Intersection n : listaNoduri) {
            oras.addIntersection(n);
        }

        Random rand = new Random();
        for (int i = 0; i < 30; i++) {
            Intersection a = listaNoduri.get(rand.nextInt(10));
            Intersection b = listaNoduri.get(rand.nextInt(10));

            if (!a.equals(b)) {
                double distanta = 1.0 + rand.nextDouble() * 9.0;
                oras.addStreet(new Street(faker.address().streetName(), distanta, a, b));
            }
        }

        oras.afiseazaStrazi(5.0);

        NetworkAlgorithm alg = new NetworkAlgorithm(oras);
        alg.rezolva(3);
    }
}