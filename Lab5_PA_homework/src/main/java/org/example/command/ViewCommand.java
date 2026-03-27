package org.example.command;

import org.example.exception.CatalogException;
import org.example.model.Resource;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;

public class ViewCommand implements Command {
    private Resource resource;

    public ViewCommand(Resource resource) { this.resource = resource; }

    @Override
    public void execute() throws CatalogException {
        if (resource == null) throw new CatalogException("Resursa este null");
        if (!Desktop.isDesktopSupported()) throw new CatalogException("Desktop API nesuportat");

        try {
            Desktop desktop = Desktop.getDesktop();
            if (resource.getLocation().startsWith("http")) {
                desktop.browse(new URI(resource.getLocation()));
            } else {
                File file = new File(resource.getLocation());
                if (!file.exists()) throw new CatalogException("Fisierul local lipseste");
                desktop.open(file);
            }
            System.out.println("S-a deschis: " + resource.getTitle());
        } catch (Exception e) {
           throw new CatalogException("Eroare la deschiderea resursei: " + resource.getTitle(), e);
        }
    }
}