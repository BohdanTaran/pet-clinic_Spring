package org.bohdan.pet.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Owner
{
    private int id;

    @NotEmpty(message = "The first and last name was entered incorrectly.")
    @Size(min = 5, max = 30, message = "The first and last name should has minimum 5 symbols")
    private String first_last_name;

    @NotEmpty(message = "The address was entered incorrectly.")
    @Size(min = 3, max = 30, message = "The address should has minimum 3 symbols")
    private String address;

    @NotEmpty(message = "The city was entered incorrectly.")
    @Size(min = 3, max = 30, message = "The city should has minimum 3 symbols")
    private String city;

    @NotEmpty(message = "The phone was entered incorrectly.")
    @Size(min = 10, max = 20, message = "The phone should has minimum 10 symbols")
    private String phone;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getFirst_last_name() {
        return first_last_name;
    }

    public void setFirst_last_name(String first_last_name) {
        this.first_last_name = first_last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
