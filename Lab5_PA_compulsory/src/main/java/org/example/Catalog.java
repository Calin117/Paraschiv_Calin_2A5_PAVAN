package org.example;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa responsabila cu operatiile pe catalogul de resurse
 */
public class Catalog {
    private String name;
    private List<Resource> resources = new ArrayList<>();

    public Catalog(String name) {
        this.name = name;
    }

    /**
     * Adauga o resursa in catalog.
     * Arunca CatalogException daca resursa este invalida
     */
    public void addResource(Resource resource) throws CatalogException {
        if (resource == null) {
            throw new CatalogException("Eroare: Nu poti adauga o resursa nula in catalog!");
        }
        resources.add(resource);
        System.out.println("S-a adaugat: " + resource.getTitle());
    }

    /**
     * Deschide resursa folosind aplicatia nativa a sistemului de operare (Desktop)
     */
    public void openResource(Resource resource) throws CatalogException {
        if (!Desktop.isDesktopSupported()) {
            throw new CatalogException("Eroare: Clasa Desktop nu este suportata pe acest sistem!");
        }

        Desktop desktop = Desktop.getDesktop();
        String location = resource.getLocation();

        try {
            if (location.startsWith("http://") || location.startsWith("https://")) {
                desktop.browse(new URI(location));
            } else {
                File file = new File(location);
                if (!file.exists()) {
                    throw new CatalogException("Fisierul local nu exista la locatia: " + location);
                }
                desktop.open(file);
            }

            System.out.println("Se deschide resursa: " + resource.getTitle());

        } catch (Exception e) {
            throw new CatalogException("A aparut o problema la deschiderea resursei: " + resource.getTitle(), e);
        }
    }
}