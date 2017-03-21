package com.blibli.future.controller;

import com.blibli.future.service.UploadFileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class AttendanceControllerTest {
    @InjectMocks
    private AttendanceController attendanceController;
    @Mock
    private UploadFileService uploadFileService;

    private MockMvc mockMvc;

    private static final String XLSX_FILENAME = "example.xlsx";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.attendanceController).build();
    }

    @Test
    public void uploadAttendanceFileTest() throws Exception {
        MediaType mediaType = new MediaType("excel", "vnd.ms-excel");
        MockMultipartFile multipartFile = new MockMultipartFile("file",  "testing".getBytes("UTF-8"));

        Mockito.when(uploadFileService.processFile(multipartFile))
                .thenReturn(true);

        mockMvc.perform(
                MockMvcRequestBuilders.fileUpload("/employees/taps/upload")
                        .file(multipartFile)
                        .accept(mediaType)
                        .contentType(mediaType)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(uploadFileService).processFile(multipartFile);
    }
}
