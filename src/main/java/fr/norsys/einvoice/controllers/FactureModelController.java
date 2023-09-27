package fr.norsys.einvoice.controllers;


import fr.norsys.einvoice.entities.FactureModel;
import fr.norsys.einvoice.entities.Invoice;
import fr.norsys.einvoice.services.FactureModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/FactureModels")
public class FactureModelController {
    @Autowired
    FactureModelService factureModelService;

    private final Path fileStoragePath = Paths.get("src/main/resources");
    @PostMapping("/save")
    public ResponseEntity<String> saveFactureModel(
            @RequestPart("logo") MultipartFile logo,
            @RequestPart("style") String style,
            @RequestPart("color") String color
    ) {
        try {
            // Check if the file is empty
            if (logo.isEmpty()) {
                return ResponseEntity.badRequest().body("Uploaded file is empty");
            }


            String filename = System.currentTimeMillis() + "_" + logo.getOriginalFilename();


            Path filePath = fileStoragePath.resolve(filename);
            Files.copy(logo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);


            FactureModel factureModel = new FactureModel();
            factureModel.setLogo(filePath.toString());
            factureModel.setStyle(style);
            factureModel.setColor(color);
            factureModelService.save(factureModel);


            return ResponseEntity.ok("File uploaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
        }
    }

    @GetMapping("/all")
    public List<FactureModel> findAll() {
        return factureModelService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<FactureModel> findById(@PathVariable UUID id) {
        return factureModelService.findById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody FactureModel p) {
        factureModelService.update(p);
    }
    @DeleteMapping("/delete")
    public void delete(@RequestBody FactureModel p) {
        factureModelService.delete(p);
    }


}
