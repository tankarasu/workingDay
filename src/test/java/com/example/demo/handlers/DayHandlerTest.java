package com.example.demo.handlers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DayHandlerTest {
  @ParameterizedTest
  @ValueSource(strings = {"2022-05-11", "2022-05-12", "2022-05-13"})
  @DisplayName("Given we want a working day")
  void isWorkingDay(LocalDate date) {
    System.out.println("now: " + date);
    System.out.println("getDayOfMonth: " + date.getDayOfMonth());
    System.out.println("getDayOfWeek: " + date.getDayOfWeek());
    System.out.println("getMonth: " + date.getMonth());
    System.out.println("getMonthValue: " + date.getMonthValue());
    System.out.println("getYear: " + date.getYear());
    assertTrue(DayHandler.isWorkingDay(date));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "2022-05-14", "2021-01-01", "2020-05-01", "2020-05-08",
      "2020-07-14", "2022-08-15", "2022-11-01", "2022-11-11", "2020-12-25"
  })
  @DisplayName("Given we want a non-working day on STATIC days")
  void isNotWorkingDay(LocalDate date) {
    assertFalse(DayHandler.isWorkingDay(date));
  }

  @ParameterizedTest
  @ValueSource(strings = {"2022-04-18", "2022-05-26", "2022-06-06",
      "2023-04-10", "2023-05-18", "2023-05-28"})
  @DisplayName("Given we want a non-working day on NON-STATIC days")
  void isNotWorkingDayNonStatic(LocalDate date) {
    assertFalse(DayHandler.isWorkingDay(date));
  }
}

