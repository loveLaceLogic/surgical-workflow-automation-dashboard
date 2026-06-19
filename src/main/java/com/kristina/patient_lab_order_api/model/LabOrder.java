package com.kristina.patient_lab_order_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LabOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;
    private String procedureName;
    private String surgeonName;
    private String surgeryDate;

    private String labStatus;
    private String insuranceStatus;
    private String surgicalStatus;
    private String priority;

    private String postOpScenario;
    private String followUpTimeframe;
    private String followUpAvailability;
    private String dischargeInstructions;

    private String outcome;
    private String notes;
    private Boolean surgeryReady;
    private String readinessReason;
    private String patientLocation;
    private String operatingRoom;
    private Integer estimatedDurationMinutes;

    public LabOrder() {
    }

    public Long getId() {
        return id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getSurgeonName() {
        return surgeonName;
    }

    public void setSurgeonName(String surgeonName) {
        this.surgeonName = surgeonName;
    }

    public String getSurgeryDate() {
        return surgeryDate;
    }

    public void setSurgeryDate(String surgeryDate) {
        this.surgeryDate = surgeryDate;
    }

    public String getLabStatus() {
        return labStatus;
    }

    public void setLabStatus(String labStatus) {
        this.labStatus = labStatus;
    }

    public String getInsuranceStatus() {
        return insuranceStatus;
    }

    public void setInsuranceStatus(String insuranceStatus) {
        this.insuranceStatus = insuranceStatus;
    }

    public String getSurgicalStatus() {
        return surgicalStatus;
    }

    public void setSurgicalStatus(String surgicalStatus) {
        this.surgicalStatus = surgicalStatus;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getPostOpScenario() {
        return postOpScenario;
    }

    public void setPostOpScenario(String postOpScenario) {
        this.postOpScenario = postOpScenario;
    }

    public String getFollowUpTimeframe() {
        return followUpTimeframe;
    }

    public void setFollowUpTimeframe(String followUpTimeframe) {
        this.followUpTimeframe = followUpTimeframe;
    }

    public String getFollowUpAvailability() {
        return followUpAvailability;
    }

    public void setFollowUpAvailability(String followUpAvailability) {
        this.followUpAvailability = followUpAvailability;
    }

    public String getDischargeInstructions() {
        return dischargeInstructions;
    }

    public void setDischargeInstructions(String dischargeInstructions) {
        this.dischargeInstructions = dischargeInstructions;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
        public Boolean getSurgeryReady() {
        return surgeryReady;
    }

    public void setSurgeryReady(Boolean surgeryReady) {
        this.surgeryReady = surgeryReady;
    }

    public String getReadinessReason() {
        return readinessReason;
    }

    public void setReadinessReason(String readinessReason) {
        this.readinessReason = readinessReason;
    }
    public String getPatientLocation() {
    return patientLocation;
    }

    public void setPatientLocation(String patientLocation) {
    this.patientLocation = patientLocation;
    }
    public String getOperatingRoom() {
    return operatingRoom;
    }

    public void setOperatingRoom(String operatingRoom) {
    this.operatingRoom = operatingRoom;
    }

    public Integer getEstimatedDurationMinutes() {
    return estimatedDurationMinutes;
    }

    public void setEstimatedDurationMinutes(Integer estimatedDurationMinutes) {
    this.estimatedDurationMinutes = estimatedDurationMinutes;
    }
}
