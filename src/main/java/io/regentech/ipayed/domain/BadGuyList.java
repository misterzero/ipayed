package io.regentech.ipayed.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BadGuyList.
 */
@Entity
@Table(name = "bad_guy_list")
public class BadGuyList implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "unique_record", nullable = false)
    private String uniqueRecord;

    @NotNull
    @Column(name = "sdn_name", nullable = false)
    private String sdnName;

    @NotNull
    @Column(name = "sdn_type", nullable = false)
    private String sdnType;

    @NotNull
    @Column(name = "sanctions_program_name", nullable = false)
    private String sanctionsProgramName;

    @NotNull
    @Column(name = "title_of_individual", nullable = false)
    private String titleOfIndividual;

    @NotNull
    @Column(name = "vessel_call_sign", nullable = false)
    private String vesselCallSign;

    @NotNull
    @Column(name = "vessel_tonnage", nullable = false)
    private String vesselTonnage;

    @NotNull
    @Column(name = "gross_registered_tonnage", nullable = false)
    private String grossRegisteredTonnage;

    @NotNull
    @Column(name = "is_vessel", nullable = false)
    private String isVessel;

    @NotNull
    @Column(name = "vessel_owner", nullable = false)
    private String vesselOwner;

    @NotNull
    @Column(name = "remark", nullable = false)
    private String remark;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniqueRecord() {
        return uniqueRecord;
    }

    public BadGuyList uniqueRecord(String uniqueRecord) {
        this.uniqueRecord = uniqueRecord;
        return this;
    }

    public void setUniqueRecord(String uniqueRecord) {
        this.uniqueRecord = uniqueRecord;
    }

    public String getSdnName() {
        return sdnName;
    }

    public BadGuyList sdnName(String sdnName) {
        this.sdnName = sdnName;
        return this;
    }

    public void setSdnName(String sdnName) {
        this.sdnName = sdnName;
    }

    public String getSdnType() {
        return sdnType;
    }

    public BadGuyList sdnType(String sdnType) {
        this.sdnType = sdnType;
        return this;
    }

    public void setSdnType(String sdnType) {
        this.sdnType = sdnType;
    }

    public String getSanctionsProgramName() {
        return sanctionsProgramName;
    }

    public BadGuyList sanctionsProgramName(String sanctionsProgramName) {
        this.sanctionsProgramName = sanctionsProgramName;
        return this;
    }

    public void setSanctionsProgramName(String sanctionsProgramName) {
        this.sanctionsProgramName = sanctionsProgramName;
    }

    public String getTitleOfIndividual() {
        return titleOfIndividual;
    }

    public BadGuyList titleOfIndividual(String titleOfIndividual) {
        this.titleOfIndividual = titleOfIndividual;
        return this;
    }

    public void setTitleOfIndividual(String titleOfIndividual) {
        this.titleOfIndividual = titleOfIndividual;
    }

    public String getVesselCallSign() {
        return vesselCallSign;
    }

    public BadGuyList vesselCallSign(String vesselCallSign) {
        this.vesselCallSign = vesselCallSign;
        return this;
    }

    public void setVesselCallSign(String vesselCallSign) {
        this.vesselCallSign = vesselCallSign;
    }

    public String getVesselTonnage() {
        return vesselTonnage;
    }

    public BadGuyList vesselTonnage(String vesselTonnage) {
        this.vesselTonnage = vesselTonnage;
        return this;
    }

    public void setVesselTonnage(String vesselTonnage) {
        this.vesselTonnage = vesselTonnage;
    }

    public String getGrossRegisteredTonnage() {
        return grossRegisteredTonnage;
    }

    public BadGuyList grossRegisteredTonnage(String grossRegisteredTonnage) {
        this.grossRegisteredTonnage = grossRegisteredTonnage;
        return this;
    }

    public void setGrossRegisteredTonnage(String grossRegisteredTonnage) {
        this.grossRegisteredTonnage = grossRegisteredTonnage;
    }

    public String getIsVessel() {
        return isVessel;
    }

    public BadGuyList isVessel(String isVessel) {
        this.isVessel = isVessel;
        return this;
    }

    public void setIsVessel(String isVessel) {
        this.isVessel = isVessel;
    }

    public String getVesselOwner() {
        return vesselOwner;
    }

    public BadGuyList vesselOwner(String vesselOwner) {
        this.vesselOwner = vesselOwner;
        return this;
    }

    public void setVesselOwner(String vesselOwner) {
        this.vesselOwner = vesselOwner;
    }

    public String getRemark() {
        return remark;
    }

    public BadGuyList remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        BadGuyList badGuyList = (BadGuyList) o;
        if (badGuyList.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), badGuyList.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BadGuyList{" +
            "id=" + getId() +
            ", uniqueRecord='" + getUniqueRecord() + "'" +
            ", sdnName='" + getSdnName() + "'" +
            ", sdnType='" + getSdnType() + "'" +
            ", sanctionsProgramName='" + getSanctionsProgramName() + "'" +
            ", titleOfIndividual='" + getTitleOfIndividual() + "'" +
            ", vesselCallSign='" + getVesselCallSign() + "'" +
            ", vesselTonnage='" + getVesselTonnage() + "'" +
            ", grossRegisteredTonnage='" + getGrossRegisteredTonnage() + "'" +
            ", isVessel='" + getIsVessel() + "'" +
            ", vesselOwner='" + getVesselOwner() + "'" +
            ", remark='" + getRemark() + "'" +
            "}";
    }
}
