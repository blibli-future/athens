package com.blibli.future.service;

import com.blibli.future.exception.IdNotFoundException;
import com.blibli.future.model.Shift;
import com.blibli.future.repository.ShiftRepository;
import com.blibli.future.vo.ShiftVo;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
    private ShiftVo shiftVOMock;

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

    @Test
    public void getShiftById_ThrowIdNotFoundException() throws Exception {
        Mockito.when(shiftRepository.findOne(TEST)).thenReturn(null);

        try {
            shiftService.getShiftById(TEST);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(IdNotFoundException.class));
        }

        Mockito.verify(shiftRepository).findOne(TEST);
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

    @Test
    public void updateShift_ThrowIdNotFoundException() throws Exception {
        Mockito.when(shiftRepository.findOne(TEST)).thenReturn(null);

        try {
            shiftService.updateShift(TEST, shiftVOMock);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(IdNotFoundException.class));
        }

        Mockito.verify(shiftRepository).findOne(TEST);
    }

    @Test
    public void deleteShift_Success() throws Exception  {
        Mockito.when(shiftRepository.exists(TEST)).thenReturn(true);
        Mockito.doNothing().when(shiftRepository).delete(TEST);

        shiftService.deleteShift(TEST);

        Mockito.verify(shiftRepository).exists(TEST);
        Mockito.verify(shiftRepository).delete(TEST);
    }

    @Test
    public void deleteShift_ThrowIdNotFoundException() throws Exception  {
        Mockito.when(shiftRepository.exists(TEST)).thenReturn(false);

        try {
            shiftService.deleteShift(TEST);
        } catch (Exception e) {
            Assert.assertThat(e, Matchers.instanceOf(IdNotFoundException.class));
        }

        Mockito.verify(shiftRepository).exists(TEST);
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        shift1 = new Shift("1", "shift-1", LocalTime.NOON, LocalTime.MIDNIGHT, DayOfWeek.MONDAY, "dept-1", "location-1");

        listMock = Arrays.asList(shift1);

        shiftVOMock = new ShiftVo(shift1);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(shiftRepository);
    }
}
