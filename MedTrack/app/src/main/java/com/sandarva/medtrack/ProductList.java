package com.sandarva.medtrack;


public class ProductList {
    public String name, manufacture, description, shape, dosage;

    public ProductList(){

    }

    public ProductList(String name, String manufacture, String description, String shape, String dosage)
    {
        this.name = name;
        this.manufacture = manufacture;
        this.description = description;
        this.shape = shape;
        this.dosage = dosage;
    }

    public String getName() {
        return name;
    }

    public String getManufacture() {
        return manufacture;
    }

    public String getDescription() {
        return description;
    }

    public String getShape() {
        return shape;
    }

    public String getDosage() {
        return dosage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }
}
