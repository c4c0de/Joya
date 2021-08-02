package com.example.joya;

public class helperCourse2ScheduleUpdate {

    String course2date, course2MorningClass, Course2EveningClass;

    public helperCourse2ScheduleUpdate(String course1date, String course1MorningClass, String course1EveningClass) {
        this.course2date = course1date;
        this.course2MorningClass = course1MorningClass;
        Course2EveningClass = course1EveningClass;
    }

    public String getCourse2date() {
        return course2date;
    }

    public String getCourse2MorningClass() {
        return course2MorningClass;
    }

    public String getCourse2EveningClass() {
        return Course2EveningClass;
    }

    public void setCourse2date(String course1date) {
        this.course2date = course2date;
    }

    public void setCourse2MorningClass(String course2MorningClass) {
        this.course2MorningClass = course2MorningClass;
    }

    public void setCourse2EveningClass(String course2EveningClass) {
        Course2EveningClass = course2EveningClass;
    }
}
