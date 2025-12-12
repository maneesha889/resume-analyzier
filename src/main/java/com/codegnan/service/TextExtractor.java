package com.codegnan.service;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public class TextExtractor {

    public static String extractText(MultipartFile file) {
        try (InputStream stream = file.getInputStream()) {

            BodyContentHandler handler = new BodyContentHandler(-1); // No text limit
            Metadata metadata = new Metadata();
            AutoDetectParser parser = new AutoDetectParser();
            ParseContext context = new ParseContext();

            parser.parse(stream, handler, metadata, context);

            return handler.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
