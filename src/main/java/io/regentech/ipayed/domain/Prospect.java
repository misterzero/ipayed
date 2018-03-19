package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Prospect.
 */
@Entity
@Table(name = "prospect")
public class Prospect implements Serializable {

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
    @Column(name = "customer_type", nullable = false)
    private String customerType;

    @NotNull
    @Column(name = "dob", nullable = false)
    private String dob;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotNull
    @Column(name = "is_politically_exposed", nullable = false)
    private String isPoliticallyExposed;

    @NotNull
    @Column(name = "prospect_id", nullable = false)
    private String prospectId;

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

    public Prospect address1(String address1) {
        this.address1 = address1;
        return this;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public Prospect address2(String address2) {
        this.address2 = address2;
        return this;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Prospect businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCity() {
        return city;
    }

    public Prospect city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public Prospect country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomerType() {
        return customerType;
    }

    public Prospect customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getDob() {
        return dob;
    }

    public Prospect dob(String dob) {
        this.dob = dob;
        return this;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public Prospect email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Prospect firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Prospect lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public Prospect middleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public Prospect phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIsPoliticallyExposed() {
        return isPoliticallyExposed;
    }

    public Prospect isPoliticallyExposed(String isPoliticallyExposed) {
        this.isPoliticallyExposed = isPoliticallyExposed;
        return this;
    }

    public void setIsPoliticallyExposed(String isPoliticallyExposed) {
        this.isPoliticallyExposed = isPoliticallyExposed;
    }

    public String getProspectId() {
        return prospectId;
    }

    public Prospect prospectId(String prospectId) {
        this.prospectId = prospectId;
        return this;
    }

    public void setProspectId(String prospectId) {
        this.prospectId = prospectId;
    }

    public String getState() {
        return state;
    }

    public Prospect state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public Prospect status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTin() {
        return tin;
    }

    public Prospect tin(String tin) {
        this.tin = tin;
        return this;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public String getTinType() {
        return tinType;
    }

    public Prospect tinType(String tinType) {
        this.tinType = tinType;
        return this;
    }

    public void setTinType(String tinType) {
        this.tinType = tinType;
    }

    public String getTitle() {
        return title;
    }

    public Prospect title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZip() {
        return zip;
    }

    public Prospect zip(String zip) {
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
        Prospect prospect = (Prospect) o;
        if (prospect.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prospect.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Prospect{" +
            "id=" + getId() +
            ", address1='" + getAddress1() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", businessName='" + getBusinessName() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", dob='" + getDob() + "'" +
            ", email='" + getEmail() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", middleName='" + getMiddleName() + "'" +
            ", phone='" + getPhone() + "'" +
            ", isPoliticallyExposed='" + getIsPoliticallyExposed() + "'" +
            ", prospectId='" + getProspectId() + "'" +
            ", state='" + getState() + "'" +
            ", status='" + getStatus() + "'" +
            ", tin='" + getTin() + "'" +
            ", tinType='" + getTinType() + "'" +
            ", title='" + getTitle() + "'" +
            ", zip='" + getZip() + "'" +
            "}";
    }
}
