package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.valueObject.ShiftVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import net.minidev.json.JSONArray;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftControllerTest {
    @InjectMocks
    private ShiftController shiftController;
    @Mock
    private ShiftService shiftService;

    private MockMvc mockMvc;

    private final String BASE_PATH = "/shift";
    private final String PATH_WITH_ID = BASE_PATH + "/test";
    private final String TEST = "test";

    private Shift shift1;
    private Shift shift2;
    private List collectionMock;
    private ShiftVO shiftVOMock;

    ObjectWriter objectWriter = new ObjectMapper().writer();

    @Test
    public void getAllShiftTest() throws Exception {
        Mockito.when(shiftService.getAllShift()).thenReturn(collectionMock);

        mockMvc.perform(
                MockMvcRequestBuilders.get(BASE_PATH)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSONArray.toJSONString(collectionMock)));

        Mockito.verify(shiftService).getAllShift();
    }

    @Test
    public void createNewShiftTest() throws Exception {
        Mockito.when(shiftService.createShift(shiftVOMock)).thenReturn(shift1);

        String shiftVo = objectWriter.writeValueAsString(shiftVOMock);
        String jsonResult = objectWriter.writeValueAsString(shift1);

        mockMvc.perform(
                MockMvcRequestBuilders.post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).createShift(shiftVOMock);
    }

    @Test
    public void getShiftByIdTest_Ok() throws Exception {
        Mockito.when(shiftService.getShiftById(TEST)).thenReturn(shift1);

        String jsonResult = objectWriter.writeValueAsString(shift1);

        mockMvc.perform(
                MockMvcRequestBuilders.get(PATH_WITH_ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).getShiftById(TEST);
    }

    @Test
    public void getShiftById_Error() throws Exception {
        Mockito.when(shiftService.getShiftById(TEST)).thenThrow(new IdNotFoundException(TEST));

        mockMvc.perform(
                MockMvcRequestBuilders.get(PATH_WITH_ID)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).getShiftById(TEST);
    }

    @Test
    public void updateShiftTest_Ok() throws Exception {
        Mockito.when(shiftService.updateShift(TEST, shiftVOMock)).thenReturn(shift2);

        String shiftVo = objectWriter.writeValueAsString(shiftVOMock);
        String jsonResult = objectWriter.writeValueAsString(shift2);

        mockMvc.perform(
                MockMvcRequestBuilders.put(PATH_WITH_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).updateShift(TEST, shiftVOMock);
    }

    @Test
    public void updateShiftTest_Error() throws Exception {
        Mockito.when(shiftService.updateShift(TEST, shiftVOMock)).thenThrow(new IdNotFoundException(TEST));

        String shiftVo = objectWriter.writeValueAsString(shiftVOMock);

        mockMvc.perform(
                MockMvcRequestBuilders.put(PATH_WITH_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).updateShift(TEST, shiftVOMock);
    }

    @Test
    public void deleteShiftTest_Ok() throws Exception {
        Mockito.doNothing().when(shiftService).deleteShift(TEST);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(PATH_WITH_ID)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(shiftService).deleteShift(TEST);
    }

    @Test
    public void deleteShiftTest_Error() throws Exception {
        Mockito.doThrow(new IdNotFoundException(TEST)).when(shiftService).deleteShift(TEST);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(PATH_WITH_ID)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).deleteShift(TEST);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(this.shiftController).build();

        shift1 = new Shift();
        shift2 = new Shift();

        this.collectionMock = new ArrayList(Arrays.asList(shift1, shift2));

        shiftVOMock = new ShiftVO();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(shiftService);
    }
}
