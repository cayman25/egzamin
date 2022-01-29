package com.wwsi.lab6.controller;

import com.azure.core.util.BinaryData;
import com.wwsi.lab6.service.BlobService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ApiController {

    private final BlobService blobService;

    public ApiController(BlobService blobService) {
        this.blobService = blobService;
    }

    @PostMapping("/api/v1/document")
    public ResponseEntity uploadDocument(@RequestParam("file") MultipartFile file){
        try{
            blobService.uploadBlob(file.getOriginalFilename(), BinaryData.fromBytes(file.getBytes()));
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }



}
