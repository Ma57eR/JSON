package com.jsonlab.model.dto;

import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "products_with_buyer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithBuyerDTO {

    @Expose
    @XmlElement
    private String name;

    @Expose
    @XmlElement
    private BigDecimal price;

    @Expose
    @XmlElement(name = "buyer_first_name")
    private String buyerFirstName;

    @Expose
    @XmlElement(name = "buyer_last_name")
    private String buyerLastName;

    public ProductWithBuyerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
