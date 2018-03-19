package io.regentech.ipayed.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A BankAccountType.
 */
@Entity
@Table(name = "bank_account_type")
public class BankAccountType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "possible_ipayed_account_type", nullable = false)
    private String possibleIpayedAccountType;

    @NotNull
    @Column(name = "account_type_code", nullable = false)
    private String accountTypeCode;

    @OneToMany(mappedBy = "accountTypeCode")
    @JsonIgnore
    private Set<BankAccount> accountTypeCodes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPossibleIpayedAccountType() {
        return possibleIpayedAccountType;
    }

    public BankAccountType possibleIpayedAccountType(String possibleIpayedAccountType) {
        this.possibleIpayedAccountType = possibleIpayedAccountType;
        return this;
    }

    public void setPossibleIpayedAccountType(String possibleIpayedAccountType) {
        this.possibleIpayedAccountType = possibleIpayedAccountType;
    }

    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    public BankAccountType accountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
        return this;
    }

    public void setAccountTypeCode(String accountTypeCode) {
        this.accountTypeCode = accountTypeCode;
    }

    public Set<BankAccount> getAccountTypeCodes() {
        return accountTypeCodes;
    }

    public BankAccountType accountTypeCodes(Set<BankAccount> bankAccounts) {
        this.accountTypeCodes = bankAccounts;
        return this;
    }

    public BankAccountType addAccountTypeCode(BankAccount bankAccount) {
        this.accountTypeCodes.add(bankAccount);
        bankAccount.setAccountTypeCode(this);
        return this;
    }

    public BankAccountType removeAccountTypeCode(BankAccount bankAccount) {
        this.accountTypeCodes.remove(bankAccount);
        bankAccount.setAccountTypeCode(null);
        return this;
    }

    public void setAccountTypeCodes(Set<BankAccount> bankAccounts) {
        this.accountTypeCodes = bankAccounts;
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
        BankAccountType bankAccountType = (BankAccountType) o;
        if (bankAccountType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), bankAccountType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BankAccountType{" +
            "id=" + getId() +
            ", possibleIpayedAccountType='" + getPossibleIpayedAccountType() + "'" +
            ", accountTypeCode='" + getAccountTypeCode() + "'" +
            "}";
    }
}
