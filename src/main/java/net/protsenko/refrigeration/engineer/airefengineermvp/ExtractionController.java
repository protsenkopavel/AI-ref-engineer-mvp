package net.protsenko.refrigeration.engineer.airefengineermvp;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/extraction")
@RequiredArgsConstructor
public class ExtractionController {

    private final ExtractionService service;

    @PostMapping
    public ExtractionResult extract(
            @RequestPart(value = "spec") MultipartFile spec,
            @RequestPart(value = "table", required = false) MultipartFile table,
            @RequestPart(value = "floorPlan", required = false) MultipartFile floorPlan
    ) throws Exception {
        return service.extract(spec, table, floorPlan);
    }
}