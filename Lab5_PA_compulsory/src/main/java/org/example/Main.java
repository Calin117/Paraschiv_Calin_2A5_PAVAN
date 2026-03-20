package org.example;

public class Main {
    public static void main(String[] args) {
        Catalog catalog = new Catalog("My Books");

        Resource book = new Resource("knuth67", "The Art of Computer Programming", "d:/books/programming/tacp.ps", 1967, "Donald E. Knuth");
        Resource urlDoc = new Resource("java25", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se25/jls25.pdf", 2025, "James Gosling & others");

        try {
            catalog.addResource(book);
            catalog.addResource(urlDoc);

            catalog.openResource(urlDoc);

        } catch (CatalogException e) {
            System.err.println("Exceptie in Catalog: " + e.getMessage());

            if (e.getCause() != null) {
                System.err.println("Cauza initiala: " + e.getCause().getMessage());
            }
        }
    }
}