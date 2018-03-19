package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BankAccount.
 */
@Entity
@Table(name = "bank_account")
public class BankAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tos_flag", nullable = false)
    private String tosFlag;

    @NotNull
    @Column(name = "date_of_tos", nullable = false)
    private String dateOfTos;

    @NotNull
    @Column(name = "account_begin_date", nullable = false)
    private String accountBeginDate;

    @NotNull
    @Column(name = "account_end_date", nullable = false)
    private String accountEndDate;

    @ManyToOne
    private BankAccountType accountTypeCode;

    @ManyToOne
    private Customer customer;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTosFlag() {
        return tosFlag;
    }

    public BankAccount tosFlag(String tosFlag) {
        this.tosFlag = tosFlag;
        return this;
    }

    public void setTosFlag(String tosFlag) {
        this.tosFlag = tosFlag;
    }

    public String getDateOfTos() {
        return dateOfTos;
    }

    public BankAccount dateOfTos(String dateOfTos) {
        this.dateOfTos = dateOfTos;
        return this;
    }

    public void setDateOfTos(String dateOfTos) {
        this.dateOfTos = dateOfTos;
    }

    public String getAccountBeginDate() {
        return accountBeginDate;
    }

    public BankAccount accountBeginDate(String accountBeginDate) {
        this.accountBeginDate = accountBeginDate;
        return this;
    }

    public void setAccountBeginDate(String accountBeginDate) {
        this.accountBeginDate = accountBeginDate;
    }

    public String getAccountEndDate() {
        return accountEndDate;
    }

    public BankAccount accountEndDate(String accountEndDate) {
        this.accountEndDate = accountEndDate;
        return this;
    }

    public void setAccountEndDate(String accountEndDate) {
        this.accountEndDate = accountEndDate;
    }

    public BankAccountType getAccountTypeCode() {
        return accountTypeCode;
    }

    public BankAccount accountTypeCode(BankAccountType bankAccountType) {
        this.accountTypeCode = bankAccountType;
        return this;
    }

    public void setAccountTypeCode(BankAccountType bankAccountType) {
        this.accountTypeCode = bankAccountType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public BankAccount customer(Customer customer) {
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
        BankAccount bankAccount = (BankAccount) o;
        if (bankAccount.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccount.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAccount{" +
            "id=" + getId() +
            ", tosFlag='" + getTosFlag() + "'" +
            ", dateOfTos='" + getDateOfTos() + "'" +
            ", accountBeginDate='" + getAccountBeginDate() + "'" +
            ", accountEndDate='" + getAccountEndDate() + "'" +
            "}";
    }
}
