package com.example.demo.handlers;

import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Data
public class DayHandler {
  public static boolean isWorkingDay(LocalDate date) {
    String day = String.valueOf(date.getDayOfWeek());
    String dayMonth = date.getDayOfMonth() + "-" + date.getMonthValue();

    List<String> staticNonWorkingDays = Arrays.asList(
        "1-1", "1-5", "8-5", "14-7", "15-8", "1-11", "11-11", "25-12"
    );

    // Algorithme pour le calcul de Pâques
    int cycleMeton = date.getYear() % 19;
    int centaine = date.getYear() / 100;
    int rangAnnee = date.getYear() % 100;
    int siecleBissextile = centaine / 4;
    int resteSiecleBisextille = centaine % 4;
    int cycleProemptose = (centaine + 8) / 25;
    int proemptose = (centaine - cycleProemptose + 1) / 3;
    int epacte = (19 * cycleMeton + centaine - siecleBissextile - resteSiecleBisextille - proemptose + 15) % 30;
    int anneBissextile = rangAnnee / 4;
    int resteAnneBissextile = rangAnnee % 4;
    int lettreDominicale =
        (2 * resteSiecleBisextille + 2 * anneBissextile - epacte - resteAnneBissextile + 32) % 7;
    int correction = (cycleMeton + 11 * epacte + 22 * lettreDominicale) / 451;
    int easterMonth = (epacte + lettreDominicale - 7 * correction + 114) / 31;
    int easterSaturday = (epacte + lettreDominicale - 7 * correction + 114) % 31;

    LocalDate easter = LocalDate.of(
        date.getYear(),
        easterMonth,
        easterSaturday);

    LocalDate easterMonday = easter.plusDays(2);
    LocalDate pentecost = easterMonday.plusDays(49);
    LocalDate assumption = easterMonday.plusDays(38);

    // Vérification pour Lundi de Pâques
    if (date.getMonthValue() == easterMonth && date.getDayOfMonth() == easterSaturday + 2)
      return false;

    // Vérification pour le jour de Pentecôte et Assomption
    if (date.isEqual(assumption) || date.isEqual(pentecost))
      return false;

    // Vérifications des jours fériés STATIQUES
    if (staticNonWorkingDays.contains(dayMonth)) return false;

    // Vérification des jours de week-end
    return day != "SUNDAY" && day != "SATURDAY";
  }
}
