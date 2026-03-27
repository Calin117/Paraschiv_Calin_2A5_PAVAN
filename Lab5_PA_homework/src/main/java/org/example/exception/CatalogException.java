package org.example.exception;

/**
 * Exceptie personalizata pentru a semnala probleme in catalog
 */
public class CatalogException extends Exception {

    public CatalogException(String message) {
        super(message);
    }

    public CatalogException(String message, Throwable cause) {
        super(message, cause);
    }
}