package com.blibli.future.service;

import com.blibli.future.model.Shift;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.valueObject.ShiftVO;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShiftServiceImplTest {
    @InjectMocks
    private ShiftServiceImpl shiftService;

    @Mock
    private ShiftRepository shiftRepository;

    private final String TEST = "test";

    private List<Shift> listMock;
    private Shift shift1;
    private ShiftVO shiftVOMock;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getAllShiftTest() throws Exception {
        Mockito.when(shiftRepository.findAll()).thenReturn(listMock);

        List<Shift> actual = shiftService.getAllShift();

        Mockito.verify(shiftRepository).findAll();

        Assert.assertEquals(listMock, actual);
    }

    @Test
    public void createShiftTest() throws Exception  {
        Mockito.when(shiftRepository.save(shift1)).thenReturn(shift1);

        Shift actual = shiftService.createShift(shiftVOMock);

        Mockito.verify(shiftRepository).save(shift1);

        Assert.assertEquals(shift1, actual);
    }

    @Test
    public void getShiftById_Success() throws Exception {
        Mockito.when(shiftRepository.findOne(TEST)).thenReturn(shift1);

        Shift actual = shiftService.getShiftById(TEST);

        Mockito.verify(shiftRepository).findOne(TEST);

        Assert.assertEquals(shift1, actual);
    }

    //TODO: How to test this?
    public void getShiftById_ThrowIdNotFoundException() throws Exception {

    }

    @Test
    public void updateShift_Success() throws Exception {
        Mockito.when(shiftRepository.findOne(TEST)).thenReturn(shift1);
        Mockito.when(shiftRepository.save(shift1)).thenReturn(shift1);

        Shift actual = shiftService.updateShift(TEST, shiftVOMock);

        Assert.assertEquals(shift1, actual);

        Mockito.verify(shiftRepository).findOne(TEST);
        Mockito.verify(shiftRepository).save(shift1);
    }

    public void updateShift_ThrowIdNotFoundException() throws Exception {

    }

    @Test
    public void deleteShift_Success() throws Exception  {
        Mockito.when(shiftRepository.exists(TEST)).thenReturn(true);
        Mockito.doNothing().when(shiftRepository).delete(TEST);

        shiftService.deleteShift(TEST);

        Mockito.verify(shiftRepository).exists(TEST);
        Mockito.verify(shiftRepository).delete(TEST);
    }

    public void deleteShift_ThrowIdNotFoundException() throws Exception  {

    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shift1 = new Shift();

        listMock = new ArrayList(Arrays.asList(shift1));

        shiftVOMock = new ShiftVO();
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(shiftRepository);
    }
}
