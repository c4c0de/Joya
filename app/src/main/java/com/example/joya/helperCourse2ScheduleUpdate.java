package com.example.joya;

public class helperCourse2ScheduleUpdate {

    String course1date, course1MorningClass, Course1EveningClass;

    public helperCourse2ScheduleUpdate(String course1date, String course1MorningClass, String course1EveningClass) {
        this.course1date = course1date;
        this.course1MorningClass = course1MorningClass;
        Course1EveningClass = course1EveningClass;
    }

    public String getCourse1date() {
        return course1date;
    }

    public String getCourse1MorningClass() {
        return course1MorningClass;
    }

    public String getCourse1EveningClass() {
        return Course1EveningClass;
    }

    public void setCourse1date(String course1date) {
        this.course1date = course1date;
    }

    public void setCourse1MorningClass(String course1MorningClass) {
        this.course1MorningClass = course1MorningClass;
    }

    public void setCourse1EveningClass(String course1EveningClass) {
        Course1EveningClass = course1EveningClass;
    }
}
