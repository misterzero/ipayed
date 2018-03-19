package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Customer.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "address_1", nullable = false)
    private String address1;

    @Column(name = "address_2")
    private String address2;

    @Column(name = "business_name")
    private String businessName;

    @NotNull
    @Column(name = "city", nullable = false)
    private String city;

    @NotNull
    @Column(name = "country", nullable = false)
    private String country;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private String createDate;

    @NotNull
    @Column(name = "dob", nullable = false)
    private String dob;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "inactive_date")
    private String inactiveDate;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "is_politically_exposed", nullable = false)
    private String isPoliticallyExposed;

    @NotNull
    @Column(name = "state", nullable = false)
    private String state;

    @NotNull
    @Column(name = "status", nullable = false)
    private String status;

    @NotNull
    @Column(name = "tin", nullable = false)
    private String tin;

    @Column(name = "tin_type")
    private String tinType;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "zip", nullable = false)
    private String zip;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public Customer address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Customer address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Customer businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public Customer city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public Customer country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCreateDate() {
        return createDate;
    }

    public Customer createDate(String createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDob() {
        return dob;
    }

    public Customer dob(String dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public Customer email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getInactiveDate() {
        return inactiveDate;
    }

    public Customer inactiveDate(String inactiveDate) {
        this.inactiveDate = inactiveDate;
        return this;
    }

    public void setInactiveDate(String inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Customer middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public Customer phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsPoliticallyExposed() {
        return isPoliticallyExposed;
    }

    public Customer isPoliticallyExposed(String isPoliticallyExposed) {
        this.isPoliticallyExposed = isPoliticallyExposed;
        return this;
    }

    public void setIsPoliticallyExposed(String isPoliticallyExposed) {
        this.isPoliticallyExposed = isPoliticallyExposed;
    }

    public String getState() {
        return state;
    }

    public Customer state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public Customer status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTin() {
        return tin;
    }

    public Customer tin(String tin) {
        this.tin = tin;
        return this;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getTinType() {
        return tinType;
    }

    public Customer tinType(String tinType) {
        this.tinType = tinType;
        return this;
    }

    public void setTinType(String tinType) {
        this.tinType = tinType;
    }

    public String getTitle() {
        return title;
    }

    public Customer title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZip() {
        return zip;
    }

    public Customer zip(String zip) {
        this.zip = zip;
        return this;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Customer customer = (Customer) o;
        if (customer.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", businessName='" + getBusinessName() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", dob='" + getDob() + "'" +
            ", email='" + getEmail() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", inactiveDate='" + getInactiveDate() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", isPoliticallyExposed='" + getIsPoliticallyExposed() + "'" +
            ", state='" + getState() + "'" +
            ", status='" + getStatus() + "'" +
            ", tin='" + getTin() + "'" +
            ", tinType='" + getTinType() + "'" +
            ", title='" + getTitle() + "'" +
            ", zip='" + getZip() + "'" +
            "}";
    }
}
