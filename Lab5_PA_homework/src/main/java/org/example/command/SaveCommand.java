package org.example.command;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.exception.CatalogException;
import org.example.model.Catalog;
import java.io.File;

public class SaveCommand implements Command {
    private Catalog catalog;
    private String filePath;

    public SaveCommand(Catalog catalog, String filePath) {
        this.catalog = catalog;
        this.filePath = filePath;
    }

    @Override
    public void execute() throws CatalogException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), catalog);
            System.out.println("Catalogul a fost salvat in " + filePath);
        } catch (Exception e) {
            throw new CatalogException("Eroare la salvarea fisierului JSON", e);
        }
    }
}