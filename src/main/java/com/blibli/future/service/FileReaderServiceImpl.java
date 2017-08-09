package com.blibli.future.service;

import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.service.api.FileReaderService;
import com.blibli.future.vo.TapData;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Service
public class FileReaderServiceImpl implements FileReaderService {
    @Override
    public List<TapData> readFileAsListOfTapData(MultipartFile file) throws UnreadableFile {
        String filename = file.getOriginalFilename().toLowerCase();

        if(filename.endsWith(".csv")) {
            return readCsvAsList(file);
        } else if(filename.endsWith(".xlsx")){
            return readXlsxAsList(file);
        } else if(filename.endsWith(".xls")) {
            return readXlsAsList(file);
        }

        throw new UnreadableFile("Unknown extension");

    }

    private List<TapData> readCsvAsList(MultipartFile csvFile) {
        List<TapData> result = new ArrayList<>();

        try (InputStream inputStream = csvFile.getInputStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null) {
                List<String> tapDataString = Arrays.asList(line.split(","));

                TapData tapData = new TapData();

                tapData.setNik(tapDataString.get(0));
                tapData.setTapDate(LocalDate.parse(tapDataString.get(1), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                tapData.setTapTime(LocalTime.parse(tapDataString.get(2), DateTimeFormatter.ofPattern("HH:mm:ss")));

                result.add(tapData);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
            return null;
        }

        return result;
    }

    private List<TapData> readXlsxAsList(MultipartFile xlsxFile) {
        List<TapData> result = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(xlsxFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            result = readExcelAsTapData(sheet);

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return result;
    }

    private List<TapData> readXlsAsList(MultipartFile xlsFile) {
        List<TapData> result = new ArrayList<>();
        try {
            Workbook workbook = new HSSFWorkbook(xlsFile.getInputStream());
            Sheet sheet = workbook.getSheetAt(0);

            readExcelAsTapData(sheet);

        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }

        return result;
    }

    private List<TapData> readExcelAsTapData(Sheet sheet) {
        List<TapData> result = new ArrayList<>();

        for(Row row : sheet) {
            Iterator<Cell> cellIterator = row.cellIterator();

            TapData tapData = new TapData();

            while(cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();

                System.out.println(columnIndex);

                switch (columnIndex) {
                    case 0:
                        tapData.setNik(String.valueOf(cell.getNumericCellValue()));
                        break;
                    case 1:
                        if(cell.getStringCellValue() != null) {
                            tapData.setTapDate(LocalDate.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                        }
                        break;
                    case 2:
                        if(cell.getStringCellValue() != null) {
                            tapData.setTapTime(LocalTime.parse(cell.getStringCellValue(), DateTimeFormatter.ofPattern("HH:mm:ss")));
                        }
                        break;
                }
            }

            result.add(tapData);
        }

        return result;
    }
}