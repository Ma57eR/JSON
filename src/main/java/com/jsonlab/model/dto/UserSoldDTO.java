package com.jsonlab.model.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.*;
import java.util.Set;

@XmlRootElement(name = "users_sold")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserSoldDTO {

    @Expose
    @XmlElement
    private String firstName;

    @Expose
    @XmlElement
    private String lastName;

    @Expose
    @XmlElement
    private Set<ProductWithBuyerDTO> soldProducts;

    public UserSoldDTO() {
    }

    public UserSoldDTO(UserSoldDTO userSoldDTO) {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<ProductWithBuyerDTO> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Set<ProductWithBuyerDTO> soldProducts) {

        this.soldProducts = soldProducts;
    }
}
