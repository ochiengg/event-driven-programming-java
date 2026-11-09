package com.university;

// We import JavaFX properties to use observable variables
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
// These are observable properties. If they change, the UI updates automatically
private final IntegerProperty id = new SimpleIntegerProperty();
private final StringProperty regNumber = new SimpleStringProperty();
private final StringProperty firstName = new SimpleStringProperty();
private final StringProperty lastName = new SimpleStringProperty();

// Constructor to build a new Student object
public Student(int id, String regNumber, String firstName, String lastName) {
// We use .set() to assign the values to the JavaFX properties
this.id.set(id);
this.regNumber.set(regNumber);
this.firstName.set(firstName);
this.lastName.set(lastName);
}

// Property Getters (Required for TableView bindings)
public IntegerProperty idProperty() { return id; }
public StringProperty regNumberProperty() { return regNumber; }
public StringProperty firstNameProperty() { return firstName; }
public StringProperty lastNameProperty() { return lastName; }

// Standard Getters (Optional, but good practice)
public String getRegNumber() { return regNumber.get(); }
}