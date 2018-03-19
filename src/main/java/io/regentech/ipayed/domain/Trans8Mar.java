package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Trans8Mar.
 */
@Entity
@Table(name = "trans_8_mar")
public class Trans8Mar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Float amount;

    @NotNull
    @Column(name = "begin_account_balance", nullable = false)
    private Float beginAccountBalance;

    @NotNull
    @Column(name = "is_cd", nullable = false)
    private String isCd;

    @NotNull
    @Column(name = "currency", nullable = false)
    private String currency;

    @NotNull
    @Column(name = "datetime", nullable = false)
    private String datetime;

    @NotNull
    @Column(name = "end_account_balance", nullable = false)
    private Float endAccountBalance;

    @NotNull
    @Column(name = "entity_fee", nullable = false)
    private Float entityFee;

    @NotNull
    @Column(name = "linked_ipayed_trans", nullable = false)
    private String linkedIpayedTrans;

    @NotNull
    @Column(name = "linked_trans", nullable = false)
    private String linkedTrans;

    @NotNull
    @Column(name = "trans_family_id", nullable = false)
    private String transFamilyId;

    @ManyToOne
    private BankAccount bankAccount;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private TransactionType transTypeCode;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAmount() {
        return amount;
    }

    public Trans8Mar amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getBeginAccountBalance() {
        return beginAccountBalance;
    }

    public Trans8Mar beginAccountBalance(Float beginAccountBalance) {
        this.beginAccountBalance = beginAccountBalance;
        return this;
    }

    public void setBeginAccountBalance(Float beginAccountBalance) {
        this.beginAccountBalance = beginAccountBalance;
    }

    public String getIsCd() {
        return isCd;
    }

    public Trans8Mar isCd(String isCd) {
        this.isCd = isCd;
        return this;
    }

    public void setIsCd(String isCd) {
        this.isCd = isCd;
    }

    public String getCurrency() {
        return currency;
    }

    public Trans8Mar currency(String currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDatetime() {
        return datetime;
    }

    public Trans8Mar datetime(String datetime) {
        this.datetime = datetime;
        return this;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Float getEndAccountBalance() {
        return endAccountBalance;
    }

    public Trans8Mar endAccountBalance(Float endAccountBalance) {
        this.endAccountBalance = endAccountBalance;
        return this;
    }

    public void setEndAccountBalance(Float endAccountBalance) {
        this.endAccountBalance = endAccountBalance;
    }

    public Float getEntityFee() {
        return entityFee;
    }

    public Trans8Mar entityFee(Float entityFee) {
        this.entityFee = entityFee;
        return this;
    }

    public void setEntityFee(Float entityFee) {
        this.entityFee = entityFee;
    }

    public String getLinkedIpayedTrans() {
        return linkedIpayedTrans;
    }

    public Trans8Mar linkedIpayedTrans(String linkedIpayedTrans) {
        this.linkedIpayedTrans = linkedIpayedTrans;
        return this;
    }

    public void setLinkedIpayedTrans(String linkedIpayedTrans) {
        this.linkedIpayedTrans = linkedIpayedTrans;
    }

    public String getLinkedTrans() {
        return linkedTrans;
    }

    public Trans8Mar linkedTrans(String linkedTrans) {
        this.linkedTrans = linkedTrans;
        return this;
    }

    public void setLinkedTrans(String linkedTrans) {
        this.linkedTrans = linkedTrans;
    }

    public String getTransFamilyId() {
        return transFamilyId;
    }

    public Trans8Mar transFamilyId(String transFamilyId) {
        this.transFamilyId = transFamilyId;
        return this;
    }

    public void setTransFamilyId(String transFamilyId) {
        this.transFamilyId = transFamilyId;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public Trans8Mar bankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        return this;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Trans8Mar customer(Customer customer) {
        this.customer = customer;
        return this;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public TransactionType getTransTypeCode() {
        return transTypeCode;
    }

    public Trans8Mar transTypeCode(TransactionType transactionType) {
        this.transTypeCode = transactionType;
        return this;
    }

    public void setTransTypeCode(TransactionType transactionType) {
        this.transTypeCode = transactionType;
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
        Trans8Mar trans8Mar = (Trans8Mar) o;
        if (trans8Mar.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), trans8Mar.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Trans8Mar{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", beginAccountBalance=" + getBeginAccountBalance() +
            ", isCd='" + getIsCd() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", datetime='" + getDatetime() + "'" +
            ", endAccountBalance=" + getEndAccountBalance() +
            ", entityFee=" + getEntityFee() +
            ", linkedIpayedTrans='" + getLinkedIpayedTrans() + "'" +
            ", linkedTrans='" + getLinkedTrans() + "'" +
            ", transFamilyId='" + getTransFamilyId() + "'" +
            "}";
    }
}
