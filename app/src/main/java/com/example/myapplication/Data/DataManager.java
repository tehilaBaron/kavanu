package com.example.myapplication.Data;

import com.example.myapplication.Enums.Day;
import com.example.myapplication.Enums.TreatmentEnum;
import com.example.myapplication.Models.Appointment;
import com.example.myapplication.Models.Appointments;
import com.example.myapplication.Models.Clinic;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.Models.Week;
import com.example.myapplication.Models.WorkDay;

import java.util.Arrays;
import java.util.Collections;

public class DataManager {

    public static Clinic createClinicWithTreatments() {
        Clinic clinic = new Clinic();

        clinic.getAllTreatments().put("1_GEL", new Treatment()
                .setTreatmentName("לק ג'ל")
                .setTreatmentPrice(130L)
                .setIsChecked(false)
                .setKeyName("1_GEL")
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.GEL));

        clinic.getAllTreatments().put("2_FIX_STRUCTURE", new Treatment()
                .setTreatmentName("תיקון מבנה אנטומי")
                .setTreatmentPrice(150L)
                .setIsChecked(false)
                .setKeyName("2_FIX_STRUCTURE")
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.FIX_STRUCTURE));

        clinic.getAllTreatments().put("3_SIMPLE_DRAW", new Treatment()
                .setTreatmentName("ציור פשוט")
                .setTreatmentPrice(5L)
                .setIsChecked(false)
                .setKeyName("3_SIMPLE_DRAW")
                .setTreatmentTimeInMinuets(0L)
                .setType(TreatmentEnum.SIMPLE_DRAW));

        clinic.getAllTreatments().put("4_REGULAR_DRAW", new Treatment()
                .setTreatmentName("ציור רגיל")
                .setTreatmentPrice(10L)
                .setIsChecked(false)
                .setKeyName("4_REGULAR_DRAW")
                .setTreatmentTimeInMinuets(5L)
                .setType(TreatmentEnum.REGULAR_DRAW));

        clinic.getAllTreatments().put("5_COMPLICATED_DRAW", new Treatment()
                .setTreatmentName("ציור מורכב")
                .setTreatmentPrice(20L)
                .setIsChecked(false)
                .setKeyName("5_COMPLICATED_DRAW")
                .setTreatmentTimeInMinuets(10L)
                .setType(TreatmentEnum.COMPLICATED_DRAW));

        clinic.getAllTreatments().put("6_FILL_POLYGEL", new Treatment()
                .setTreatmentName("מילוי בפוליג'ל")
                .setTreatmentPrice(180L)
                .setIsChecked(false)
                .setKeyName("6_FILL_POLYGEL")
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.FILL_POLYGEL));

        clinic.getAllTreatments().put("7_POLYGEL", new Treatment()
                .setTreatmentName("בניה בפוליג'ל")
                .setTreatmentPrice(280L)
                .setIsChecked(false)
                .setKeyName("7_POLYGEL")
                .setTreatmentTimeInMinuets(120L)
                .setType(TreatmentEnum.POLYGEL));

        clinic.getAllTreatments().put("8_NAIL_REPAIR", new Treatment()
                .setTreatmentName("השלמת ציפורן")
                .setTreatmentPrice(15L)
                .setIsChecked(false)
                .setKeyName("8_NAIL_REPAIR")
                .setTreatmentTimeInMinuets(5L)
                .setType(TreatmentEnum.NAIL_REPAIR));


        return clinic;
    }

    public static Week createWeekWithWorkDays() {
        Week week = new Week();

        week.getAllWorkDays().put("1_SUNDAY", new WorkDay()
                .setDayEnum(Day.SUNDAY)
                .setDayName("ראשון")
                .setEndHour("17:00")
                .setIsOpen(true)
                .setStartHour("09:00"));
        week.getAllWorkDays().put("2_MONDAY", new WorkDay()
                .setDayEnum(Day.MONDAY)
                .setDayName("שני")
                .setEndHour("17:00")
                .setIsOpen(Boolean.TRUE)
                .setStartHour("09:00"));
        week.getAllWorkDays().put("3_TUESDAY", new WorkDay()
                .setDayEnum(Day.TUESDAY)
                .setDayName("שלישי")
                .setEndHour("15:00")
                .setIsOpen(Boolean.TRUE)
                .setStartHour("08:00"));
        week.getAllWorkDays().put("4_WEDNESDAY", new WorkDay()
                .setDayEnum(Day.WEDNESDAY)
                .setDayName("רביעי")
                .setEndHour("17:00")
                .setIsOpen(Boolean.TRUE)
                .setStartHour("08:00"));
        week.getAllWorkDays().put("5_THURSDAY", new WorkDay()
                .setDayEnum(Day.THURSDAY)
                .setDayName("חמישי")
                .setEndHour("17:00")
                .setIsOpen(Boolean.TRUE)
                .setStartHour("09:00"));
        week.getAllWorkDays().put("6_FRIDAY", new WorkDay()
                .setDayEnum(Day.FRIDAY)
                .setDayName("שישי")
                .setEndHour(null)
                .setIsOpen(Boolean.FALSE)
                .setStartHour(null));
        week.getAllWorkDays().put("7_SATURDAY", new WorkDay()
                .setDayEnum(Day.SATURDAY)
                .setDayName("שבת")
                .setEndHour(null)
                .setIsOpen(Boolean.FALSE)
                .setStartHour(null));


        return week;
    }

    public static Appointments createAppointments() {
        Appointments appointments = new Appointments();

        appointments.getAllAppointments().put("344212133", new Appointment()
                .setClientName("תהילה ברון")
                .setTreatments(Arrays.asList(TreatmentEnum.GEL))
                .setDay(Day.SUNDAY)
                .setStartHour("09:00")
                .setEndHour("10:00"));

        appointments.getAllAppointments().put("314949493", new Appointment()
                .setClientName("שירה בירה")
                .setTreatments(Arrays.asList(TreatmentEnum.GEL, TreatmentEnum.FILL_POLYGEL))
                .setDay(Day.SUNDAY)
                .setStartHour("11:30")
                .setEndHour("13:30"));

        appointments.getAllAppointments().put("242423522", new Appointment()
                .setClientName("דנה דן")
                .setTreatments(Arrays.asList(TreatmentEnum.POLYGEL, TreatmentEnum.REGULAR_DRAW))
                .setDay(Day.MONDAY)
                .setStartHour("10:00")
                .setEndHour("12:05"));

        appointments.getAllAppointments().put("133342422", new Appointment()

                .setClientName("מור מורן")
                .setTreatments(Arrays.asList(TreatmentEnum.GEL, TreatmentEnum.NAIL_REPAIR, TreatmentEnum.COMPLICATED_DRAW, TreatmentEnum.REGULAR_DRAW))
                .setDay(Day.SUNDAY)
                .setStartHour("13:30")
                .setEndHour("14:50"));


        return appointments;
    }


}
