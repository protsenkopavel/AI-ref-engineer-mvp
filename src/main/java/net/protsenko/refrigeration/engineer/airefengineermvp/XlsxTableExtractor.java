package net.protsenko.refrigeration.engineer.airefengineermvp;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XlsxTableExtractor {

    public List<TableData> extractTables(InputStream input) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(input)) {
            DataFormatter fmt = new DataFormatter();
            FormulaEvaluator eval = workbook.getCreationHelper()
                    .createFormulaEvaluator();

            List<TableData> result = new ArrayList<>();
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                result.add(readSheet(sheet, fmt, eval));
            }
            return result;
        }
    }

    private TableData readSheet(XSSFSheet sheet,
                                DataFormatter fmt,
                                FormulaEvaluator eval) {
        List<String> headers = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        for (Row row : sheet) {
            List<String> cells = new ArrayList<>();
            for (int c = 0; c < row.getLastCellNum(); c++) {
                Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cells.add(fmt.formatCellValue(cell, eval));
            }
            if (row.getRowNum() == sheet.getFirstRowNum()) {
                headers = cells;
            } else {
                rows.add(cells);
            }
        }
        return new TableData(sheet.getSheetName(), headers, rows);
    }
}
