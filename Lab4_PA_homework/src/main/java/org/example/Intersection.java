package org.example;
import java.util.Objects;

/**
 * Clasa care reprezinta o intersectie din oras.
 */
public class Intersection implements Comparable<Intersection>{
    private String name;
    public Intersection(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    @Override
    public int compareTo(Intersection other){
        return this.name.compareTo(other.name);
    }
    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if (o==null || getClass()!=o.getClass())
            return false;
        Intersection inter= (Intersection) o;
        return Objects.equals(name, inter.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    @Override
    public String toString() {
        return "Intersection{" + "name=" + name + '\'' + '}';
    }
}
