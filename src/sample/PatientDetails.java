package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PatientDetails {
    private final StringProperty name;
    private final StringProperty patientId;
    private final StringProperty age;
    private final StringProperty sex;
    private final StringProperty phno;
    private final StringProperty dateOB;
    private final StringProperty Address;
    private final StringProperty bloodgrp;
    private final StringProperty Photo;


    public PatientDetails(String patientId, String name, String bloodgrp, String age, String sex, String Address, String phno, String dateOB, String Photo) {
        this.name = new SimpleStringProperty(name);
        this.patientId = new SimpleStringProperty(patientId);
        this.age = new SimpleStringProperty(age);
        this.sex = new SimpleStringProperty(sex);
        this.phno = new SimpleStringProperty(phno);
        this.dateOB = new SimpleStringProperty(dateOB);
        this.Address = new SimpleStringProperty(Address);
        this.bloodgrp = new SimpleStringProperty(bloodgrp);
        this.Photo = new SimpleStringProperty(Photo);

    }


    public String getName() {
        return name.get();
    }

    public String getPatientId() {
        return patientId.get();
    }

    public String getAge() {
        return age.get();
    }

    public String getPhoto() {
        return Photo.get();
    }

    public void setName(String value) {
        name.setValue(value);
    }

    public void setPatientId(String value) {
        patientId.setValue(value);
    }

    public void setAge(String value) {
        age.setValue(value);
    }

    public void setBloodgrp(String value) {
        bloodgrp.setValue(value);
    }

    public void setPhno(String value) {
        phno.setValue(value);
    }

    public void setSex(String value) {
        sex.setValue(value);
    }

    public void setAddress(String value) {
        Address.setValue(value);
    }

    public void setPhoto(String value) {
        Photo.setValue(value);
    }

    public StringProperty photoProperty() {
        return Photo;
    }

    public void setDateOB(String value) {
        dateOB.setValue(value);
    }

    public StringProperty patientIdProperty() {
        return patientId;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty ageProperty() {
        return age;
    }

    public String getSex() {
        return sex.get();
    }

    public StringProperty sexProperty() {
        return sex;
    }

    public String getPhno() {
        return phno.get();
    }

    public StringProperty phnoProperty() {
        return phno;
    }

    public String getDateOB() {
        return dateOB.get();
    }

    public StringProperty dateOBProperty() {
        return dateOB;
    }

    public String getAddress() {
        return Address.get();
    }

    public StringProperty addressProperty() {
        return Address;
    }

    public String getBloodgrp() {
        return bloodgrp.get();
    }

    public StringProperty bloodgrpProperty() {
        return bloodgrp;
    }

}
