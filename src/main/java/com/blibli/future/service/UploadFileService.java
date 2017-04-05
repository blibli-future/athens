package com.blibli.future.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UploadFileService {
    public UploadFileService() {
    }

    public boolean processFile(MultipartFile uploadedFile) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        String fileName = uploadedFile.getOriginalFilename();

        if(fileName.endsWith(".csv")) {
            List<String> csvData = readCsvAsList(uploadedFile);

            for(String tappingData : csvData) {
                String[] splittedTappingData = tappingData.split(";");

                System.out.println(splittedTappingData[0]+"|"+
                        splittedTappingData[1]+"|"+
                        splittedTappingData[2]);
            }

        } else {
            return false;
        }

        return true;
    }

    private List<String> readCsvAsList(MultipartFile csvFile) {
        List<String> result = new ArrayList<>();

        try (InputStream inputStream = csvFile.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line= reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
