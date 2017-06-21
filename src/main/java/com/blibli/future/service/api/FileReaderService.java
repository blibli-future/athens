package com.blibli.future.service.api;

import com.blibli.future.exception.UnreadableFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReaderService {
    List<String> readFileAsStrings(MultipartFile file) throws UnreadableFile;
}
