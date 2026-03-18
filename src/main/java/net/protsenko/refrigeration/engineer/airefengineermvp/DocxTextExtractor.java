package net.protsenko.refrigeration.engineer.airefengineermvp;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class DocxTextExtractor {

    public String extractText(InputStream input) throws IOException {
        try (XWPFDocument doc = new XWPFDocument(input)) {
            StringBuilder sb = new StringBuilder();
            for (XWPFParagraph para : doc.getParagraphs()) {
                String text = para.getText();
                if (text == null || text.isBlank()) {
                    sb.append("\n");
                    continue;
                }
                sb.append(text).append("\n");
            }
            return sb.toString().trim();
        }
    }
}
