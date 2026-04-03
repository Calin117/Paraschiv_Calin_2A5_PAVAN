package org.example;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.example.model.Movie;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerator {
    public void generateReport(List<Movie> movies) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
            cfg.setClassForTemplateLoading(this.getClass(), "/");

            Map<String, Object> data = new HashMap<>();
            data.put("movies", movies);

            Template template = cfg.getTemplate("report.ftl");
            File outputFile = new File("movies_report.html");

            try (Writer fileWriter = new FileWriter(outputFile)) {
                template.process(data, fileWriter);
            }

            Desktop.getDesktop().browse(outputFile.toURI());

        } catch (Exception e) {
            System.err.println("Eroare la generarea raportului: " + e.getMessage());
        }
    }
}