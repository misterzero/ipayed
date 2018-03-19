package io.regentech.ipayed.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A TransactionType.
 */
@Entity
@Table(name = "transaction_type")
public class TransactionType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ipayed_trans_type", nullable = false)
    private String ipayedTransType;

    @NotNull
    @Column(name = "ipayed_originator_fee", nullable = false)
    private Float ipayedOriginatorFee;

    @NotNull
    @Column(name = "ipayed_benefactor_fee", nullable = false)
    private Float ipayedBenefactorFee;

    @NotNull
    @Column(name = "loop_type", nullable = false)
    private String loopType;

    @NotNull
    @Column(name = "chargeup_fee", nullable = false)
    private Float chargeupFee;

    @NotNull
    @Column(name = "fee_to_ipayed", nullable = false)
    private String feeToIpayed;

    @NotNull
    @Column(name = "trans_type_code", nullable = false)
    private String transTypeCode;

    @NotNull
    @Column(name = "originator_account_type_code", nullable = false)
    private String originatorAccountTypeCode;

    @NotNull
    @Column(name = "benefactor_account_type_code", nullable = false)
    private String benefactorAccountTypeCode;

    @OneToMany(mappedBy = "transTypeCode")
    @JsonIgnore
    private Set<Trans8Mar> transTypeCodes = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpayedTransType() {
        return ipayedTransType;
    }

    public TransactionType ipayedTransType(String ipayedTransType) {
        this.ipayedTransType = ipayedTransType;
        return this;
    }

    public void setIpayedTransType(String ipayedTransType) {
        this.ipayedTransType = ipayedTransType;
    }

    public Float getIpayedOriginatorFee() {
        return ipayedOriginatorFee;
    }

    public TransactionType ipayedOriginatorFee(Float ipayedOriginatorFee) {
        this.ipayedOriginatorFee = ipayedOriginatorFee;
        return this;
    }

    public void setIpayedOriginatorFee(Float ipayedOriginatorFee) {
        this.ipayedOriginatorFee = ipayedOriginatorFee;
    }

    public Float getIpayedBenefactorFee() {
        return ipayedBenefactorFee;
    }

    public TransactionType ipayedBenefactorFee(Float ipayedBenefactorFee) {
        this.ipayedBenefactorFee = ipayedBenefactorFee;
        return this;
    }

    public void setIpayedBenefactorFee(Float ipayedBenefactorFee) {
        this.ipayedBenefactorFee = ipayedBenefactorFee;
    }

    public String getLoopType() {
        return loopType;
    }

    public TransactionType loopType(String loopType) {
        this.loopType = loopType;
        return this;
    }

    public void setLoopType(String loopType) {
        this.loopType = loopType;
    }

    public Float getChargeupFee() {
        return chargeupFee;
    }

    public TransactionType chargeupFee(Float chargeupFee) {
        this.chargeupFee = chargeupFee;
        return this;
    }

    public void setChargeupFee(Float chargeupFee) {
        this.chargeupFee = chargeupFee;
    }

    public String getFeeToIpayed() {
        return feeToIpayed;
    }

    public TransactionType feeToIpayed(String feeToIpayed) {
        this.feeToIpayed = feeToIpayed;
        return this;
    }

    public void setFeeToIpayed(String feeToIpayed) {
        this.feeToIpayed = feeToIpayed;
    }

    public String getTransTypeCode() {
        return transTypeCode;
    }

    public TransactionType transTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode;
        return this;
    }

    public void setTransTypeCode(String transTypeCode) {
        this.transTypeCode = transTypeCode;
    }

    public String getOriginatorAccountTypeCode() {
        return originatorAccountTypeCode;
    }

    public TransactionType originatorAccountTypeCode(String originatorAccountTypeCode) {
        this.originatorAccountTypeCode = originatorAccountTypeCode;
        return this;
    }

    public void setOriginatorAccountTypeCode(String originatorAccountTypeCode) {
        this.originatorAccountTypeCode = originatorAccountTypeCode;
    }

    public String getBenefactorAccountTypeCode() {
        return benefactorAccountTypeCode;
    }

    public TransactionType benefactorAccountTypeCode(String benefactorAccountTypeCode) {
        this.benefactorAccountTypeCode = benefactorAccountTypeCode;
        return this;
    }

    public void setBenefactorAccountTypeCode(String benefactorAccountTypeCode) {
        this.benefactorAccountTypeCode = benefactorAccountTypeCode;
    }

    public Set<Trans8Mar> getTransTypeCodes() {
        return transTypeCodes;
    }

    public TransactionType transTypeCodes(Set<Trans8Mar> trans8Mars) {
        this.transTypeCodes = trans8Mars;
        return this;
    }

    public TransactionType addTransTypeCode(Trans8Mar trans8Mar) {
        this.transTypeCodes.add(trans8Mar);
        trans8Mar.setTransTypeCode(this);
        return this;
    }

    public TransactionType removeTransTypeCode(Trans8Mar trans8Mar) {
        this.transTypeCodes.remove(trans8Mar);
        trans8Mar.setTransTypeCode(null);
        return this;
    }

    public void setTransTypeCodes(Set<Trans8Mar> trans8Mars) {
        this.transTypeCodes = trans8Mars;
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
        TransactionType transactionType = (TransactionType) o;
        if (transactionType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), transactionType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TransactionType{" +
            "id=" + getId() +
            ", ipayedTransType='" + getIpayedTransType() + "'" +
            ", ipayedOriginatorFee=" + getIpayedOriginatorFee() +
            ", ipayedBenefactorFee=" + getIpayedBenefactorFee() +
            ", loopType='" + getLoopType() + "'" +
            ", chargeupFee=" + getChargeupFee() +
            ", feeToIpayed='" + getFeeToIpayed() + "'" +
            ", transTypeCode='" + getTransTypeCode() + "'" +
            ", originatorAccountTypeCode='" + getOriginatorAccountTypeCode() + "'" +
            ", benefactorAccountTypeCode='" + getBenefactorAccountTypeCode() + "'" +
            "}";
    }
}
