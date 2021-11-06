package com.fatec.livrariaecommerce.models.utils;

import java.util.List;

public class ConverterDate {

    public static String translateMonth(String month) {
        if (month.equals("JANUARY")) {
            return "Janeiro";
        } else if (month.equals("FEBRUARY")) {
            return "Feveiro";
        } else if (month.equals("MARCH")) {
            return "Março";
        } else if (month.equals("APRIL")) {
            return "Abril";
        } else if (month.equals("MAY")) {
            return "Maio";
        } else if (month.equals("JUNE")) {
            return "Junho";
        } else if (month.equals("JULY")) {
            return "Julho";
        } else if (month.equals("AUGUST")) {
            return "Agosto";
        } else if (month.equals("SEPTEMBER")) {
            return "Setembro";
        } else if (month.equals("OCTOBER")) {
            return "Outubro";
        } else if (month.equals("NOVEMBER")) {
            return "Novembro";
        } else if (month.equals("DECEMBER")) {
            return "Dezembro";
        }
        return "";
    }

}
