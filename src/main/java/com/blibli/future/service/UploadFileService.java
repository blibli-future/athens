package com.blibli.future.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

    public boolean processFile(MultipartFile uploadedFile) {
        String fileName = uploadedFile.getOriginalFilename();

        if(fileName.matches("(xlsx?|csv)$")) {
            return true;
        }

        return false;
    }
}
