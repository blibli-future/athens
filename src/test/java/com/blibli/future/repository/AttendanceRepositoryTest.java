package com.blibli.future.repository;

import com.blibli.future.model.Attendance;
import com.blibli.future.model.primaryKey.AttendanceKey;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceRepositoryTest {
    private final LocalTime EARLY_TIME = LocalTime.of(9, 0);
    private final LocalTime LATE_TIME = LocalTime.of(19, 0);

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    public void findByAttendanceKeyTest() throws Exception {
        AttendanceKey key = new AttendanceKey("NIK-1", LocalDate.of(2017, 3, 1));
        Assert.assertThat(this.attendanceRepository.findByAttendanceKey(key), Matchers.equalTo(1));
    }

    @Test
    public void findByAttendanceKeyDateBetweenTest() throws Exception {
        LocalDate firstDate = LocalDate.of(2017, 2, 1);
        LocalDate secondDate = LocalDate.of(2017, 4, 1);
        Assert.assertThat(this.attendanceRepository.findByAttendanceKeyDateBetween(firstDate, secondDate), Matchers.equalTo(2));
    }

    @Before
    public void setUp() throws Exception{
        Attendance attendance1 = new Attendance("NIK-1", LocalDate.of(2017, 3, 1), EARLY_TIME, LATE_TIME);
        Attendance attendance2 = new Attendance("NIK-1", LocalDate.of(2017, 5, 1), EARLY_TIME, LATE_TIME);
        Attendance attendance3 = new Attendance("NIK-2", LocalDate.of(2017, 3, 1), EARLY_TIME, LATE_TIME);

        this.attendanceRepository.save(attendance1);
        this.attendanceRepository.save(attendance2);
        this.attendanceRepository.save(attendance3);
    }

    @After
    public void tearDown() throws Exception {
        this.attendanceRepository.deleteAll();
    }
}
