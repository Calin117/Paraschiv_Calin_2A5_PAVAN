package org.example.command;
import org.example.exception.CatalogException;

/**
 * Interfata de baza pentru toate comenzile aplicatiei.
 */
public interface Command {
    void execute() throws CatalogException;
}