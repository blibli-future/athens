package com.blibli.future.controller;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.service.api.ShiftService;
import com.blibli.future.vo.ShiftVo;
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

import java.util.Arrays;
import java.util.List;

public class ShiftControllerTest {
    @InjectMocks
    private ShiftController shiftController;
    @Mock
    private ShiftService shiftService;

    private MockMvc mockMvc;

    private final String TEST = "test";

    private Shift shift1;
    private Shift shift2;
    private List<Shift> shiftList;
    private ShiftVo shiftVo1;
    private ShiftVo shiftVo2;
    private List<ShiftVo> shiftVoList;

    private ObjectWriter objectWriter = new ObjectMapper().writer();

    @Test
    public void getAllShiftTest() throws Exception {
        Mockito.when(shiftService.getAllShift()).thenReturn(shiftVoList);

        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.BASE_PATH)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(JSONArray.toJSONString(shiftVoList)));

        Mockito.verify(shiftService).getAllShift();
    }

    @Test
    public void createNewShiftTest() throws Exception {
        Mockito.when(shiftService.createShift(shiftVo1)).thenReturn(shift1);

        String shiftVo = objectWriter.writeValueAsString(shiftVo1);
        String jsonResult = objectWriter.writeValueAsString(shift1);

        mockMvc.perform(
                MockMvcRequestBuilders.post(shiftController.BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).createShift(shiftVo1);
    }

    @Test
    public void getShiftByIdTest_Ok() throws Exception {
        Mockito.when(shiftService.getShiftById(TEST)).thenReturn(shift1);

        String jsonResult = objectWriter.writeValueAsString(shift1);

        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).getShiftById(TEST);
    }

    @Test
    public void getShiftById_Error() throws Exception {
        Mockito.when(shiftService.getShiftById(TEST)).thenThrow(new IdNotFoundException(TEST));

        mockMvc.perform(
                MockMvcRequestBuilders.get(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).getShiftById(TEST);
    }

    @Test
    public void updateShiftTest_Ok() throws Exception {
        Mockito.when(shiftService.updateShift(TEST, shiftVo1)).thenReturn(shift2);

        String shiftVo = objectWriter.writeValueAsString(shiftVo1);
        String jsonResult = objectWriter.writeValueAsString(shift2);

        mockMvc.perform(
                MockMvcRequestBuilders.put(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonResult));

        Mockito.verify(shiftService).updateShift(TEST, shiftVo1);
    }

    @Test
    public void updateShiftTest_Error() throws Exception {
        Mockito.when(shiftService.updateShift(TEST, shiftVo1)).thenThrow(new IdNotFoundException(TEST));

        String shiftVo = objectWriter.writeValueAsString(shiftVo1);

        mockMvc.perform(
                MockMvcRequestBuilders.put(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(shiftVo)
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).updateShift(TEST, shiftVo1);
    }

    @Test
    public void deleteShiftTest_Ok() throws Exception {
        Mockito.doNothing().when(shiftService).deleteShift(TEST);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
        ).andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(shiftService).deleteShift(TEST);
    }

    @Test
    public void deleteShiftTest_Error() throws Exception {
        Mockito.doThrow(new IdNotFoundException(TEST)).when(shiftService).deleteShift(TEST);

        mockMvc.perform(
                MockMvcRequestBuilders.delete(shiftController.PATH_WITH_ID.replaceAll("\\{id\\}", TEST))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        Mockito.verify(shiftService).deleteShift(TEST);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(this.shiftController)
                .setControllerAdvice(new AthensControllerAdvice())
                .build();

        this.shift1 = new Shift();
        this.shift2 = new Shift();

        this.shiftList = Arrays.asList(shift1, shift2);

        this.shiftVo1 = new ShiftVo();
        this.shiftVo2 = new ShiftVo();

        this.shiftVoList = Arrays.asList(shiftVo1, shiftVo2);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(shiftService);
    }
}
