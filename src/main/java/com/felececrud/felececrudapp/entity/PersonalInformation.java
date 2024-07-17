package com.felececrud.felececrudapp.entity;

import com.felececrud.felececrudapp.enums.Gender;
import com.felececrud.felececrudapp.enums.MaritalStatus;
import com.felececrud.felececrudapp.enums.MilitaryStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "personal_information")
public class PersonalInformation {
    // define our fields

    // define constructors

    // define getter setters

    // define toString

    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "birth_day")
    private String birthDay;

    @Column(name = "national_id")
    private int nationalId;
    @Column(name = "military_status")
    private MilitaryStatus militaryStatus;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "marital_status")
    private MaritalStatus maritalStatus;

    public PersonalInformation() {

    }

    public PersonalInformation(String birthDay, int nationalId, MilitaryStatus militaryStatus, Gender gender, MaritalStatus maritalStatus) {
        this.birthDay = birthDay;
        this.nationalId = nationalId;
        this.militaryStatus = militaryStatus;
        this.gender = gender;
        this.maritalStatus = maritalStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public MilitaryStatus getMilitaryStatus() {
        return militaryStatus;
    }

    public void setMilitaryStatus(MilitaryStatus militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "PersonalInformation{" +
                "id=" + id +
                ", birthDay='" + birthDay + '\'' +
                ", nationalId=" + nationalId +
                ", militaryStatus=" + militaryStatus +
                ", gender=" + gender +
                ", maritalStatus=" + maritalStatus +
                '}';
    }
}
