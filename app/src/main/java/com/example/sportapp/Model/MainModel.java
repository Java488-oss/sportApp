package com.example.sportapp.Model;

public class MainModel {

    // fields
    private int Id;

    private String nameField;

    private String value;

    // constructors
    public MainModel() {}

    public MainModel(int id, String nameField, String value) {
        this.Id = id;
        this.nameField = nameField;
        this.value=value;
    }

    // properties
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return this.Id;
    }

    public void setNameField(String nameField) {
        this.nameField = nameField;
    }

    public String getNameField() {
        return this.nameField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}