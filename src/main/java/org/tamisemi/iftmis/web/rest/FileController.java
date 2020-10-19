package org.tamisemi.iftmis.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.domain.FileResource;
import org.tamisemi.iftmis.payload.UploadFileResponse;
import org.tamisemi.iftmis.service.FileResourceService;
import org.tamisemi.iftmis.service.FileStorageService;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;
import org.tamisemi.iftmis.service.mapper.FileResourceMapper;

import liquibase.util.file.FilenameUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.security.DigestInputStream;
@RestController
@RequestMapping("/api")
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	@Autowired
	private FileStorageService fileStorageService;

	
	@Autowired
    private  FileResourceService fileResourceService;
    
    @PostMapping("/upload-file")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) throws URISyntaxException {
        String fileName = fileStorageService.storeFile(file);
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download-file/")
                .path(fileName)
                .toUriString();
        
        String md5 = fileStorageService.getMd5(file);
        String extension = fileStorageService.getExtension(file);
        double size =   file.getSize();

        
        FileResourceDTO fileResourceDTO = new FileResourceDTO();

        
        try {
        	fileResourceDTO.setContentType(file.getContentType());
        	fileResourceDTO.setContextMd5(md5);
        	fileResourceDTO.setName(fileName);
        	fileResourceDTO.setPath(fileDownloadUri);
        	fileResourceDTO.setSize(size);
        	fileResourceDTO.setType(extension);
		} catch (Exception e) {
			// TODO: handle exception
		}
        

        FileResourceDTO result = fileResourceService.save(fileResourceDTO);

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), extension,file.getSize(), md5, result);
    
    }



    @GetMapping("/download-file/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            logger.info("file path", contentType);
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
