package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A CustomerActivity.
 */
@Entity
@Table(name = "customer_activity")
public class CustomerActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @NotNull
    @Column(name = "last_login_date", nullable = false)
    private String lastLoginDate;

    @NotNull
    @Column(name = "session_length_seconds", nullable = false)
    private String sessionLengthSeconds;

    @ManyToOne
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public CustomerActivity ipAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public CustomerActivity lastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getSessionLengthSeconds() {
        return sessionLengthSeconds;
    }

    public CustomerActivity sessionLengthSeconds(String sessionLengthSeconds) {
        this.sessionLengthSeconds = sessionLengthSeconds;
        return this;
    }

    public void setSessionLengthSeconds(String sessionLengthSeconds) {
        this.sessionLengthSeconds = sessionLengthSeconds;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerActivity customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
        CustomerActivity customerActivity = (CustomerActivity) o;
        if (customerActivity.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), customerActivity.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CustomerActivity{" +
            "id=" + getId() +
            ", ipAddress='" + getIpAddress() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", sessionLengthSeconds='" + getSessionLengthSeconds() + "'" +
            "}";
    }
}
