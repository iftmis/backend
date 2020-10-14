package org.tamisemi.iftmis.web.rest;

/**
 * @author : Nickyrabit
 **/
import java.io.IOException;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.tamisemi.iftmis.service.StorageService;

@RestController
@RequestMapping("/api")
public class FileUploadResource {
    private final StorageService storageService;
    private final Logger log = LoggerFactory.getLogger(FileUploadResource.class);

    @Autowired
    public FileUploadResource(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/allFiles")
    public String listUploadedFiles(Model model) throws IOException {
        model.addAttribute(
            "files",
            storageService
                .loadAll()
                .map(
                    path ->
                        MvcUriComponentsBuilder
                            .fromMethodName(FileUploadResource.class, "serveFile", path.getFileName().toString())
                            .build()
                            .toUri()
                            .toString()
                )
                .collect(Collectors.toList())
        );

        return "uploadForm";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity
            .ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
            .body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");
        log.debug("You have sucessfully uploaded  " + file.getOriginalFilename() + "   at the ");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
