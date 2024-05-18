package com.API.kanitas.Controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;

@RestController
public class ImageController {
    
    ///Filepath to store the images
    private static final String UPLOAD_DIR = "C:\\Users\\Starl1ght\\Pictures\\";

    ///This method is intended to be used alongside other calls in ProductController
    ///Renames the image to the id of the product it is linked and then moves/copies it to the filepath defined above
    public static String uploadImage(@RequestParam int product_id, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = Integer.toString(product_id) + ".png";
            String filePath = UPLOAD_DIR + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);
            return "Image uploaded successfully.";
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload image.";
        }
    }

    ///Retrives the image using the id of the product is it linked
    @CrossOrigin
    @GetMapping("/api/images/{id}")
    public ResponseEntity<Resource> getImage(@PathVariable int id) {
        try {
            String fileName = Integer.toString(id) + ".png";
            String filePath = UPLOAD_DIR + fileName;
            Path imagePath = Paths.get(filePath);
            byte[] imageBytes = Files.readAllBytes(imagePath);
            ByteArrayResource resource = new ByteArrayResource(imageBytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }
}
