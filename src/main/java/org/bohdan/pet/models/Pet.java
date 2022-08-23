package org.bohdan.pet.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

public class Pet
{
    private int id;

    @NotEmpty(message = "Please, enter name of your pet")
    @Size(min = 2, max = 20, message = "Name should has minimum 2 symbols")
    private String name;

    @NotEmpty(message = "Please, enter breed of your pet")
    @Size(min = 2, max = 20, message = "Name should has minimum 2 symbols")
    private String breed;

    @NotEmpty(message = "Please, enter type of your pet")
    @Size(min = 2, max = 20, message = "Name should has minimum 2 symbols")
    private String type;

    @NotEmpty(message = "Please, enter description about your pet")
    @Size(min = 10, max = 255, message = "Description should has minimum 10 symbols")
    private String description;

    private Date visit_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) { this.type = type; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getVisit_date() {
        return visit_date;
    }

    public void setVisit_date(Date visit_date) {
        this.visit_date = visit_date;
    }
}
