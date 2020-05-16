package com.sandarva.medtrack;

public class ReminderData {
    private String name;
    private String time;
    private String shape;
    private String description;

    public ReminderData(){

    }

    public ReminderData(String name, String time,  String shape, String description){
        this.name = name;
        this.time = time;
        this.shape = shape;
        this.description = description;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
