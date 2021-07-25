package com.example.joya;

public class helperPersonalDetailsUpdate {

    String DOB, gender, bloodGroup, PermanentAdress, pinCode;

    public helperPersonalDetailsUpdate(String DOB, String gender, String bloodGroup, String permanentAdress, String pinCode) {
        this.DOB = DOB;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        PermanentAdress = permanentAdress;
        this.pinCode = pinCode;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getPermanentAdress() {
        return PermanentAdress;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public void setPermanentAdress(String permanentAdress) {
        PermanentAdress = permanentAdress;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
