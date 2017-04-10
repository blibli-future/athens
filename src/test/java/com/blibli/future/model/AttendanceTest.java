package com.blibli.future.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceTest {
    private static LocalDate TAP_DATE = LocalDate.of(2017, 4, 1);
    private static LocalTime EARLY_TIME = LocalTime.of(10, 0, 0);
    private static LocalTime MIDDLE_TIME = LocalTime.of(12, 0, 0);
    private static LocalTime LATE_TIME = LocalTime.of(19, 0, 0);
    private static String NIK = "abc";
    @Test
    public void assignTest_NoTapOutNewTapAfterTapIn() {
        Attendance expected = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);
        Attendance actual = new Attendance(NIK, TAP_DATE, LATE_TIME);

        actual.assign(EARLY_TIME);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assignTest_NoTapOutNewTapBeforeTapIn() {
        Attendance expected = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);
        Attendance actual = new Attendance(NIK, TAP_DATE, EARLY_TIME);

        actual.assign(LATE_TIME);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assignTest_newTapBeforeTapIn() {
        Attendance expected = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);
        Attendance actual = new Attendance(NIK, TAP_DATE, MIDDLE_TIME, LATE_TIME);

        actual.assign(EARLY_TIME);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assignTest_newTapBetweenTap() {
        Attendance expected = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);
        Attendance actual = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);

        actual.assign(MIDDLE_TIME);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assignTest_newTapAfterTapIn() {
        Attendance expected = new Attendance(NIK, TAP_DATE, EARLY_TIME, LATE_TIME);
        Attendance actual = new Attendance(NIK, TAP_DATE, EARLY_TIME, MIDDLE_TIME);

        actual.assign(LATE_TIME);

        Assert.assertEquals(expected, actual);
    }
}
