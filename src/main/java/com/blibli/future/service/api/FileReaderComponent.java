package com.blibli.future.service.api;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReaderComponent {
    List<String> readFileAsStrings(MultipartFile file);
}
