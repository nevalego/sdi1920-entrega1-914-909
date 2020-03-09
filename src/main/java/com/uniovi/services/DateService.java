//package com.uniovi.services;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.time.LocalTime;
//
//public class DateService {
//
//	
//	public String dateToString(LocalDate date) {
//        try {
//            return date.toString("dd-MM-yyyy");
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la fecha no puede ser nula");
//        }
//    }
//
//    public String dateToStringWithHour(LocalDateTime localDateTime) {
//        try {
//            return localDateTime.toString("dd-MM-yyyy HH:mm");
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la fecha no puede ser nula");
//        }
//    }
//
//    public String dateToStringOnlyHour(LocalTime localTime) {
//        try {
//            return localTime.toString("HH:mm");
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la hora no puede ser nula");
//        }
//    }
//
//    public LocalDate stringToDate(String date) {
//        try {
//            if (date.isEmpty()) {
//                throw new IllegalArgumentException("la fecha no puede " +
//                        "estar vacía");
//            }
//            return LocalDate.parse(date, DateTimeFormat.forPattern(
//                    "dd-MM-yyyy"));
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la fecha no puede ser nula");
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("el formato de la fecha no" +
//                    " es válido");
//        }
//    }
//
//    public LocalTime stringToDateOnlyHour(String date) {
//        try {
//            if (date.isEmpty()) {
//                throw new IllegalArgumentException("la fecha no puede " +
//                        "estar vacía");
//            }
//            return LocalTime.parse(date, DateTimeFormat.forPattern("HH:mm"));
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la hora no puede ser nula");
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("el formato de la hora no" +
//                    " es válido");
//        }
//    }
//
//    public LocalDateTime stringToDateWithHour(String date) {
//        try {
//            if (date.isEmpty()) {
//                throw new IllegalArgumentException("la fecha no puede " +
//                        "estar vacía");
//            }
//            return LocalDateTime.parse(date,
//                    DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la fecha no puede ser nula");
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("el formato de la fecha no" +
//                    " es válido");
//        }
//    }
//
//    public LocalDateTime stringToDateWithHour(String date, String hour) {
//        try {
//            if (date.isEmpty()) {
//                throw new IllegalArgumentException("la fecha no puede " +
//                        "estar vacía");
//            }
//            if (hour.isEmpty()) {
//                throw new IllegalArgumentException("la fecha no puede " +
//                        "estar vacía");
//            }
//            String newDate = date + " " + hour;
//            return LocalDateTime.parse(newDate,
//                    DateTimeFormat.forPattern("dd-MM-yyyy HH:mm"));
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("la fecha no puede ser nula");
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException("el formato de la fecha no" +
//                    " es válido");
//        }
//    }
//
//    public boolean compareTwoDatesWithoutYear(
//            LocalDate first, LocalDate second) {
//        try {
//            return (first.getMonth() == second.getMonth())
//                    && (first.getDayOfWeek() == second.getDayOfWeek());
//        } catch (NullPointerException e) {
//            throw new IllegalArgumentException("las dos fechas a comparar no" +
//                    " deben ser nulas");
//        }
//    }
//}
