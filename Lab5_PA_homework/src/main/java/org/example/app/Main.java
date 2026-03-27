package org.example.app;

import org.example.command.*;
import org.example.exception.CatalogException;
import org.example.model.Catalog;
import org.example.model.Resource;

public class Main {
    public static void main(String[] args) {
        try {
            Catalog myCatalog = new Catalog("Java Resources");
            myCatalog.add(new Resource("java25", "The Java Language Spec", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", 2025, "James Gosling"));
            myCatalog.add(new Resource("knuth67", "The Art of Computer Prog", "https://google.com", 1967, "Donald E. Knuth"));

            Command listCmd = new ListCommand(myCatalog);
            listCmd.execute();

            Command saveCmd = new SaveCommand(myCatalog, "catalog.json");
            saveCmd.execute();

            LoadCommand loadCmd = new LoadCommand("catalog.json");
            loadCmd.execute();
            Catalog loadedCatalog = loadCmd.getLoadedCatalog();

            Command reportCmd = new ReportCommand(loadedCatalog);
            reportCmd.execute();

        } catch (CatalogException e) {
            System.err.println("Eroare in aplicatie: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Cauza: " + e.getCause().getMessage());
            }
        }
    }
}