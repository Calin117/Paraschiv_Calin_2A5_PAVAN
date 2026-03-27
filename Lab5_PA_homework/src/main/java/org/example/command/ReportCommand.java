package org.example.command;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.example.exception.CatalogException;
import org.example.model.Catalog;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class ReportCommand implements Command {
    private Catalog catalog;

    public ReportCommand(Catalog catalog) { this.catalog = catalog; }

    @Override
    public void execute() throws CatalogException {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setClassForTemplateLoading(this.getClass(), "/");

            Map<String, Object> data = new HashMap<>();
            data.put("catalogName", catalog.getName());
            data.put("items", catalog.getItems());

            Template template = cfg.getTemplate("report.ftl");

            File outputFile = new File("catalog_report.html");
            Writer fileWriter = new FileWriter(outputFile);
            template.process(data, fileWriter);
            fileWriter.close();

            Desktop.getDesktop().browse(outputFile.toURI());

        } catch (Exception e) {
            throw new CatalogException("Eroare la generarea raportului HTML", e);
        }
    }
}