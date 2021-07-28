package com.example.joya;

public class helperCourse1ScheduleUpdate {

    String course1date, course1MorningClass, Course1EveningClass;

    public helperCourse1ScheduleUpdate(String course1date, String course1MorningClass, String Course1EveningClass) {
        this.course1date = course1date;
        this.course1MorningClass = course1MorningClass;
        this.Course1EveningClass = Course1EveningClass;
    }

    public String getCourse1date() {
        return course1date;
    }

    public String getCourse1MorningClass() {
        return course1MorningClass;
    }

    public String getGetCourse1EveningClass() {
        return Course1EveningClass;
    }

    public void setCourse1date(String course1date) {
        this.course1date = course1date;
    }

    public void setCourse1MorningClass(String course1MorningClass) {
        this.course1MorningClass = course1MorningClass;
    }

    public void setGetCourse1EveningClass(String Course1EveningClass) {
        this.Course1EveningClass = Course1EveningClass;
    }
}
