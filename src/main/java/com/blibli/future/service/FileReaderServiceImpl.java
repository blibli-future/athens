package com.blibli.future.service;

import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.service.api.FileReaderService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<String> readFileAsStrings(MultipartFile file) throws UnreadableFile {
        String filename = file.getContentType().toLowerCase();

        if(filename.endsWith(".csv")) {
            return readCsvAsList(file);
        } else if(filename.endsWith(".xlsx")){
            return readXlsxAsList(file);
        } else if(filename.endsWith(".xls")) {
            return readXlsAsList(file);
        }

        throw new UnreadableFile("Unknown extension");

    }

    private List<String> readCsvAsList(MultipartFile csvFile) {
        List<String> result = new ArrayList<>();

        try (InputStream inputStream = csvFile.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            //TODO(?): throw the reason(?)
            return null;
        }

        return result;
    }

    private List<String> readXlsxAsList(MultipartFile xlsxFile) {
        List<String> result = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(xlsxFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for(int i=0; i<sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String buffer = row.getCell(0) + "," + row.getCell(1) + "," + row.getCell(2);
                result.add(buffer);
            }
        } catch (IOException e) {
            //TODO(?): throw the reason(?)
            return null;
        }

        return result;
    }

    private List<String> readXlsAsList(MultipartFile xlsFile) {
        List<String> result = new ArrayList<>();
        try {
            Workbook workbook = new HSSFWorkbook(xlsFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            for(int i=0; i<sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                String buffer = row.getCell(0) + "," + row.getCell(1) + "," + row.getCell(2);
                result.add(buffer);
            }
        } catch (IOException e) {
            //TODO(?): throw the reason(?)
            return null;
        }

        return result;
    }
}