package com.blibli.future.service;

import com.blibli.future.service.api.FileReaderComponent;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileReaderComponentImpl implements FileReaderComponent {
    @Override
    public List<String> readFileAsStrings(MultipartFile file) {
        String filename = file.getContentType();
        List<String> fileContent;

        if(filename.endsWith(".csv")) {
            fileContent = readCsvAsList(file);
        } else {
            return null; //Or should it throws some "Unsupported file Type" ?
        }
        return fileContent;
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
