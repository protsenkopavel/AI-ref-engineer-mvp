package net.protsenko.refrigeration.engineer.airefengineermvp;

import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AiRefEngineerMvpApplication {

    static void main(String[] args) {
        ZipSecureFile.setMinInflateRatio(0.001);
        SpringApplication.run(AiRefEngineerMvpApplication.class, args);
    }

}
