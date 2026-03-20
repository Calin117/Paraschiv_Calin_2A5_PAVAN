package org.example;

/**
 * Exceptie personalizata (Checked Exception) pentru a semnala probleme in catalog.
 */
public class CatalogException extends Exception {

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}