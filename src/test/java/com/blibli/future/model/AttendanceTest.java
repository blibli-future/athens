package com.blibli.future.model;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceTest {
    private static LocalDate TAP_DATE = LocalDate.of(2017, 4, 1);
    private static String NIK = "abc";
    @Test
    public void assignTest_NoTapOutNewTapAfterTapIn() {
        LocalTime tapIn = LocalTime.of(9, 0, 0);
        LocalTime tapOut = LocalTime.of(21, 0, 0);

        Attendance expected = new Attendance(NIK, TAP_DATE, tapIn, tapOut);
        Attendance actual = new Attendance(NIK, TAP_DATE, tapIn);

        actual.assign(tapOut);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void assignTest_NoTapOutNewTapBeforeTapIn() {
        LocalTime tapIn = LocalTime.of(10, 0, 0);
        LocalTime tapOut = LocalTime.of(19, 0, 0);

        Attendance expected = new Attendance(NIK, TAP_DATE, tapIn, tapOut);
        Attendance actual = new Attendance(NIK, TAP_DATE, tapOut);

        actual.assign(tapIn);

        Assert.assertEquals(expected, actual);
    }
}
