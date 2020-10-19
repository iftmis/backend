package org.tamisemi.iftmis.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tamisemi.iftmis.config.FileStorageProperties;
import org.tamisemi.iftmis.exception.FileStorageException;
import org.tamisemi.iftmis.exception.MyFileNotFoundException;

import liquibase.pro.packaged.gt;
import liquibase.util.file.FilenameUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;



import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
    	
    	String fileName ;
        // Normalize file name
    	String lUUID = String.format("%040d", new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
    	String fileNameWithOutExt = FilenameUtils.removeExtension(originalFileName);
    	String fileExtension = FilenameUtils.getExtension(originalFileName);

        
     
        
        
        fileName = originalFileName.replace(originalFileName, lUUID+"."+fileExtension);
        
//        System.out.println("getOriginalFilename: "+file.getOriginalFilename());
//        System.out.println("fileNameWithOutExt: "+fileNameWithOutExt);
//        System.out.println("fileExtension: "+fileExtension);
//        System.out.println("getContentType: "+file.getContentType());
//        System.out.println("lUUID: "+lUUID);
        
        
        
        


        

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    
    
    
    public String getMd5(MultipartFile file) {
        try {
            byte[] uploadBytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(uploadBytes);
            return new BigInteger(1, digest).toString(16);
        } catch (Exception e) {
        }
        return null;
    }
    
    public String getExtension(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
        	String fileExtension = FilenameUtils.getExtension(originalFileName);
        	return fileExtension;
        } catch (Exception e) {
        }
        return null;
    }
    

    

    
    

}
