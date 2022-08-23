package org.bohdan.pet.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Veterinar
{
    private int id;

    @NotEmpty(message = "First name should be not empty")
    @Size(min = 2, max = 30, message = "First name should be has minimum 2 symbols")
    private String first_name;

    @NotEmpty(message = "Last name should be not empty")
    @Size(min = 2, max = 30, message = "Last name should be has minimum 2 symbols")
    private String last_name;

    @NotEmpty(message = "Specialty should be not empty")
    @Size(min = 2, max = 80, message = "Specialty should be has minimum 2 symbols")
    private String specialty;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() { return first_name; }
    public void setFirst_name(String first_name) { this.first_name = first_name; }
    public String getFullName()
    {
        return first_name + " " + last_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}
