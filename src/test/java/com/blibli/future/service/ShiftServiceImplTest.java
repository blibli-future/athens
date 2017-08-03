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

    private Shift shift1;
    private Shift shift2;
    private List<Shift> shiftList;
    private ShiftVo shiftVo1;
    private ShiftVo shiftVo2;
    private List<ShiftVo> shiftVoList;

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getAllShiftTest() throws Exception {
        Mockito.when(shiftRepository.findAll()).thenReturn(shiftList);

        List<ShiftVo> actual = shiftService.getAllShift();

        Mockito.verify(shiftRepository).findAll();

        Assert.assertThat(actual, Matchers.containsInAnyOrder(shiftVo1, shiftVo2));
    }

    @Test
    public void createShiftTest() throws Exception  {
        this.shift1 = new Shift();
        this.shift2 = new Shift();

        shift1.setName("shift 1");
        shift1.setStartHour(LocalTime.NOON);
        shift1.setEndHour(LocalTime.MIDNIGHT);
        shift1.setStartDay(DayOfWeek.SUNDAY);
        shift1.setEndDay(DayOfWeek.FRIDAY);

        shift2.setId("1");
        shift2.setStartHour(LocalTime.NOON);
        shift2.setEndHour(LocalTime.MIDNIGHT);
        shift2.setStartDay(DayOfWeek.SUNDAY);
        shift2.setEndDay(DayOfWeek.FRIDAY);


        this.shiftVo1 = new ShiftVo();

        shiftVo1.setName("shift 1");
        shiftVo1.setStartHour(String.valueOf(LocalTime.NOON));
        shiftVo1.setEndHour(String.valueOf(LocalTime.MIDNIGHT));
        shiftVo1.setStartDay(DayOfWeek.SUNDAY.getValue());
        shiftVo1.setEndDay(DayOfWeek.FRIDAY.getValue());


        Mockito.when(shiftRepository.save(shift1)).thenReturn(shift2);

        Shift actual = shiftService.createShift(shiftVo1);

        Mockito.verify(shiftRepository).save(shift1);

        Assert.assertEquals(shift2, actual);
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

        Shift actual = shiftService.updateShift(TEST, shiftVo1);

        Assert.assertEquals(shift1, actual);

        Mockito.verify(shiftRepository).findOne(TEST);
        Mockito.verify(shiftRepository).save(shift1);
    }

    @Test
    public void updateShift_ThrowIdNotFoundException() throws Exception {
        Mockito.when(shiftRepository.findOne(TEST)).thenReturn(null);

        try {
            shiftService.updateShift(TEST, shiftVo1);
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

        this.shift1 = new Shift();
        this.shift2 = new Shift();

        shift1.setId("1");
        shift1.setName("shift 1");
        shift1.setStartHour(LocalTime.NOON);
        shift1.setEndHour(LocalTime.MIDNIGHT);
        shift1.setStartDay(DayOfWeek.SUNDAY);
        shift1.setEndDay(DayOfWeek.FRIDAY);

        shift2.setId("2");
        shift2.setName("shift 2");
        shift2.setStartHour(LocalTime.NOON);
        shift2.setEndHour(LocalTime.MIDNIGHT);
        shift2.setStartDay(DayOfWeek.SUNDAY);
        shift2.setEndDay(DayOfWeek.FRIDAY);

        this.shiftList = Arrays.asList(shift1, shift2);

        this.shiftVo1 = new ShiftVo();
        this.shiftVo2 = new ShiftVo();

        shiftVo1.setId("1");
        shiftVo1.setName("shift 1");
        shiftVo1.setStartHour(String.valueOf(LocalTime.NOON));
        shiftVo1.setEndHour(String.valueOf(LocalTime.MIDNIGHT));
        shiftVo1.setStartDay(DayOfWeek.SUNDAY.getValue());
        shiftVo1.setEndDay(DayOfWeek.FRIDAY.getValue());

        shiftVo2.setId("2");
        shiftVo2.setName("shift 2");
        shiftVo2.setStartHour(String.valueOf(LocalTime.NOON));
        shiftVo2.setEndHour(String.valueOf(LocalTime.MIDNIGHT));
        shiftVo2.setStartDay(DayOfWeek.SUNDAY.getValue());
        shiftVo2.setEndDay(DayOfWeek.FRIDAY.getValue());


        this.shiftVoList = Arrays.asList(shiftVo1, shiftVo2);
    }

    @After
    public void tearDown() {
        Mockito.verifyNoMoreInteractions(shiftRepository);
    }
}
