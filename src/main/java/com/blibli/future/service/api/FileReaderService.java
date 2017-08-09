package com.blibli.future.service.api;

import com.blibli.future.exception.UnreadableFile;
import com.blibli.future.vo.TapData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileReaderService {
    List<TapData> readFileAsListOfTapData(MultipartFile file) throws UnreadableFile;
}
