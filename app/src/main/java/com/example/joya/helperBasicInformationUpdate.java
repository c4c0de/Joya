package com.example.joya;

public class helperBasicInformationUpdate {

    String name, mobileNumber, email, rollNumber, DOB, gender, bloodGroup, PermanentAdress, pinCode;


    public helperBasicInformationUpdate(String name, String mobileNumber, String email, String rollNumber, String DOB, String gender, String bloodGroup, String permanentAdress, String pinCode) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.rollNumber = rollNumber;
        this.DOB = DOB;
        this.gender = gender;
        this.bloodGroup = bloodGroup;
        PermanentAdress = permanentAdress;
        this.pinCode = pinCode;
    }

    public String getName() {
        return name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNumber() {
        return rollNumber;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
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
