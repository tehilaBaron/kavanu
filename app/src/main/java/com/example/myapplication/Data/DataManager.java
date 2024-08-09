package com.example.myapplication.Data;

import com.example.myapplication.Enums.Day;
import com.example.myapplication.Enums.TreatmentEnum;
import com.example.myapplication.Models.Clinic;
import com.example.myapplication.Models.Treatment;
import com.example.myapplication.Models.Week;
import com.example.myapplication.Models.WorkDay;

public class DataManager {

    public static Clinic createClinicWithTreatments() {
        Clinic clinic = new Clinic();

        clinic.getAllTreatments().put("1_GEL", new Treatment()
                .setTreatmentName("לק ג'ל")
                .setTreatmentPrice(130L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.GEL));

        clinic.getAllTreatments().put("2_FIX_STRUCTURE", new Treatment()
                .setTreatmentName("תיקון מבנה אנטומי")
                .setTreatmentPrice(150L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.FIX_STRUCTURE));

        clinic.getAllTreatments().put("3_SIMPLE_DRAW", new Treatment()
                .setTreatmentName("ציור פשוט")
                .setTreatmentPrice(5L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(0L)
                .setType(TreatmentEnum.SIMPLE_DRAW));

        clinic.getAllTreatments().put("4_REGULAR_DRAW", new Treatment()
                .setTreatmentName("ציור רגיל")
                .setTreatmentPrice(10L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(5L)
                .setType(TreatmentEnum.REGULAR_DRAW));

        clinic.getAllTreatments().put("5_COMPLICATED_DRAW", new Treatment()
                .setTreatmentName("ציור מורכב")
                .setTreatmentPrice(20L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(10L)
                .setType(TreatmentEnum.COMPLICATED_DRAW));

        clinic.getAllTreatments().put("6_FILL_POLYGEL", new Treatment()
                .setTreatmentName("מילוי בפוליג'ל")
                .setTreatmentPrice(180L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(60L)
                .setType(TreatmentEnum.FILL_POLYGEL));

        clinic.getAllTreatments().put("7_POLYGEL", new Treatment()
                .setTreatmentName("בניה בפוליג'ל")
                .setTreatmentPrice(280L)
                .setIsChecked(false)
                .setTreatmentTimeInMinuets(120L)
                .setType(TreatmentEnum.POLYGEL));

        clinic.getAllTreatments().put("8_NAIL_REPAIR", new Treatment()
                .setTreatmentName("השלמת ציפורן")
                .setTreatmentPrice(15L)
                .setIsChecked(false)
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


}
