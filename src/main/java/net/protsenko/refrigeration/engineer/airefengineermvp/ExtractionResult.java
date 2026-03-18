package net.protsenko.refrigeration.engineer.airefengineermvp;

import java.util.List;

public record ExtractionResult(
        String text,
        List<TableData> tables,
        List<byte[]> images
) {}