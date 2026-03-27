package org.example.command;
import org.example.model.Catalog;
import org.example.model.Resource;

public class ListCommand implements Command {
    private Catalog catalog;

    public ListCommand(Catalog catalog) { this.catalog = catalog; }

    @Override
    public void execute() {
        System.out.println("Catalog: " + catalog.getName() );
        for (Resource res : catalog.getItems()) {
            System.out.println(res.getTitle() + " (" + res.getYear() + ") by " + res.getAuthor());
        }
    }
}