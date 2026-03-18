package net.protsenko.refrigeration.engineer.airefengineermvp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExtractionService {

    private final DocxTextExtractor docxExtractor;
    private final XlsxTableExtractor xlsxExtractor;

    public ExtractionResult extract(
            MultipartFile spec,
            MultipartFile table,
            MultipartFile floorPlan
    ) throws Exception {

        String text = docxExtractor.extractText(spec.getInputStream());

        List<TableData> tables = table != null
                ? xlsxExtractor.extractTables(table.getInputStream())
                : List.of();

        List<byte[]> images = floorPlan != null
                ? List.of(floorPlan.getBytes())
                : List.of();

        return new ExtractionResult(text, tables, images);
    }
}
