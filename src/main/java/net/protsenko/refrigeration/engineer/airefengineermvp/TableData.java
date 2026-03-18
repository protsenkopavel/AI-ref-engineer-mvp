package net.protsenko.refrigeration.engineer.airefengineermvp;

import java.util.List;

public record TableData(
        String sheetName,
        List<String> headers,
        List<List<String>> rows
) {}
