package com.blibli.future.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadFileService {

    public boolean processFile(MultipartFile uploadedFile) {
        String fileName = uploadedFile.getOriginalFilename();

        if(fileName.endsWith(".xlsx")) {
//            processXlsx(uploadedFile);
        } else if(fileName.endsWith(".xls")) {
//            processXls(uploadedFile);
        } else if(fileName.endsWith(".csv")) {
//            processCsv(uploadedFile);
        } else {
            return false;
        }

        return true;
    }


    }
}
