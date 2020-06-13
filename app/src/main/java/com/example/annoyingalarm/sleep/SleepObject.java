package com.example.annoyingalarm.sleep;

public class SleepObject {
    private int id;
    private int startToSleepHrs, startToSleepMinute;
    private int wakeUpTimeHrs, wakeUpTimeMinute;
    private float sleepDuration;
    private int day;
    private int month;
    private int year;

    public SleepObject() {
        id = -1;
        startToSleepHrs = -1;
        startToSleepMinute = -1;
        wakeUpTimeHrs = -1;
        wakeUpTimeMinute = -1;
        sleepDuration = -1;
        day = -1;
        month = -1;
        year = -1;
    }

    public SleepObject(int id, int startToSleepHrs, int startToSleepMinute, int wakeUpTimeHrs, int wakeUpTimeMinute, float sleepDuration, int day, int month, int year) {
        super();
        this.id = id;
        this.startToSleepHrs = startToSleepHrs;
        this.startToSleepMinute = startToSleepMinute;
        this.wakeUpTimeHrs = wakeUpTimeHrs;
        this.wakeUpTimeMinute = wakeUpTimeMinute;
        this.sleepDuration = sleepDuration;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public SleepObject(int startToSleepHrs, int startToSleepMinute, int wakeUpTimeMinute, int wakeUpTimeHrs, float sleepDuration, int day, int month, int year) {
        super();
        this.startToSleepHrs = startToSleepHrs;
        this.startToSleepMinute = startToSleepMinute;
        this.wakeUpTimeHrs = wakeUpTimeHrs;
        this.wakeUpTimeMinute = wakeUpTimeMinute;
        this.sleepDuration = sleepDuration;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStartToSleepHrs() {
        return startToSleepHrs;
    }

    public void setStartToSleepHrs(int startToSleepHrs) {
        this.startToSleepHrs = startToSleepHrs;
    }

    public int getStartToSleepMinute() {
        return startToSleepMinute;
    }

    public void setStartToSleepMinute(int startToSleepMinute) {
        this.startToSleepMinute = startToSleepMinute;
    }

    public int getWakeUpTimeHrs() {
        return wakeUpTimeHrs;
    }

    public void setWakeUpTimeHrs(int wakeUpTimeHrs) {
        this.wakeUpTimeHrs = wakeUpTimeHrs;
    }

    public int getWakeUpTimeMinute() {
        return wakeUpTimeMinute;
    }

    public void setWakeUpTimeMinute(int wakeUpTimeMinute) {
        this.wakeUpTimeMinute = wakeUpTimeMinute;
    }

    public float getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(float sleepDuration) {
        this.sleepDuration = sleepDuration;
    }


}
