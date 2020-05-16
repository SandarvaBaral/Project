package com.sandarva.medtrack;

public class Appointment_List {
    private String FirstName, SecondName, PhoneNumber, EmailAddress, AppointmentDate, Time;

    private Appointment_List(){}

    private Appointment_List(String FirsName, String SecondName, String PhoneNumber, String EmailAddress, String AppointmentDate, String Time )
    {
        this.FirstName = FirsName;
        this.SecondName = SecondName;
        this.PhoneNumber = PhoneNumber;
        this.EmailAddress = EmailAddress;
        this.AppointmentDate = AppointmentDate;
        this.Time = Time;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getSecondName() {
        return SecondName;
    }

    public void setSecondName(String secondName) {
        SecondName = secondName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getAppointmentDate() {
        return AppointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        AppointmentDate = appointmentDate;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
