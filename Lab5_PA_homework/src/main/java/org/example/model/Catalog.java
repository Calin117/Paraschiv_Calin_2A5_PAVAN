package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private String name;
    private List<Resource> items = new ArrayList<>();

    public Catalog() {}

    public Catalog(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Resource> getItems() { return items; }
    public void setItems(List<Resource> items) { this.items = items; }

    public void add(Resource item) {
        items.add(item);
    }
}