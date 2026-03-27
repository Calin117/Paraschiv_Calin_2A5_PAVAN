package org.example.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.CatalogException;
import org.example.model.Catalog;
import java.io.File;

public class LoadCommand implements Command {
    private String filePath;
    private Catalog loadedCatalog;

    public LoadCommand(String filePath) { this.filePath = filePath; }

    @Override
    public void execute() throws CatalogException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            this.loadedCatalog = mapper.readValue(new File(filePath), Catalog.class);
            System.out.println("Catalogul a fost incarcat din " + filePath);
        } catch (Exception e) {
            throw new CatalogException("Eroare la incarcarea fisierului JSON", e);
        }
    }

    public Catalog getLoadedCatalog() { return loadedCatalog; }
}