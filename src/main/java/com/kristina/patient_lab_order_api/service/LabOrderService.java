package com.kristina.patient_lab_order_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kristina.patient_lab_order_api.model.LabOrder;
import com.kristina.patient_lab_order_api.repository.LabOrderRepository;

@Service
public class LabOrderService {

    private final LabOrderRepository repository;

    public LabOrderService(LabOrderRepository repository) {
        this.repository = repository;
    }

    public List<LabOrder> getAllOrders() {
        return repository.findAll();
    }

    public LabOrder saveOrder(LabOrder order) {
        order.setOutcome(determineOutcome(order));
        order.setFollowUpTimeframe(determineFollowUpTimeframe(order));
        order.setFollowUpAvailability(determineFollowUpAvailability(order));
        order.setDischargeInstructions(generateDischargeInstructions(order));
        order.setSurgeryReady(determineReadiness(order));
        order.setReadinessReason(determineReadinessReason(order));
        order.setEstimatedDurationMinutes(determineEstimatedDuration(order));
        order.setOperatingRoom(assignOperatingRoom(order));
        return repository.save(order);
    }

    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }

    private String determineOutcome(LabOrder order) {
        if ("DENIED".equalsIgnoreCase(order.getInsuranceStatus())) {
            return "DELAYED_INSURANCE";
        }

        if (!"RESULTED".equalsIgnoreCase(order.getLabStatus())) {
            return "DELAYED_LABS";
        }

        if ("STAT".equalsIgnoreCase(order.getPriority())) {
            return "URGENT_CASE";
        }

        if ("PACU".equalsIgnoreCase(order.getSurgicalStatus())) {
            return "IN_RECOVERY";
        }

        if ("DISCHARGED".equalsIgnoreCase(order.getSurgicalStatus())) {
            return "COMPLETED";
        }

        return "READY_FOR_SURGERY";
    }

    private boolean determineReadiness(LabOrder order) {
        return "VERIFIED".equalsIgnoreCase(order.getInsuranceStatus())
                && "RESULTED".equalsIgnoreCase(order.getLabStatus())
                && !"CANCELLED".equalsIgnoreCase(order.getSurgicalStatus());
    }

    private String determineReadinessReason(LabOrder order) {
        if (!"VERIFIED".equalsIgnoreCase(order.getInsuranceStatus())) {
            return "Insurance Hold";
        }

        if (!"RESULTED".equalsIgnoreCase(order.getLabStatus())) {
            return "Pending Labs";
        }

        if ("CANCELLED".equalsIgnoreCase(order.getSurgicalStatus())) {
            return "Case Cancelled";
        }

        return "Ready For Surgery";
    }

    private String determineFollowUpTimeframe(LabOrder order) {
        String procedure = order.getProcedureName();

        if (procedure == null) {
            return "Follow-up timeframe not assigned";
        }

        if (procedure.equalsIgnoreCase("Cystoscopy")) {
            return "Follow up in 1-2 weeks";
        }

        if (procedure.equalsIgnoreCase("TURP")) {
            return "Follow up in 1 week for catheter/post-op review";
        }

        if (procedure.equalsIgnoreCase("Ureteroscopy")) {
            return "Follow up in 5-7 days for possible stent removal";
        }

        if (procedure.equalsIgnoreCase("Hernia Repair")) {
            return "Follow up in 2 weeks for incision check";
        }

        if (procedure.equalsIgnoreCase("Tonsillectomy")) {
            return "Follow up in 10-14 days";
        }

        return "Follow up in 2-4 weeks";
    }

    private String determineFollowUpAvailability(LabOrder order) {
        String surgeon = order.getSurgeonName();

        if (surgeon == null) {
            return "No surgeon availability assigned";
        }

        if (surgeon.equalsIgnoreCase("Dr. Smith")) {
            return "Available Monday, Wednesday, Friday";
        }

        if (surgeon.equalsIgnoreCase("Dr. Johnson")) {
            return "Available Tuesday and Thursday";
        }

        if (surgeon.equalsIgnoreCase("Dr. Patel")) {
            return "Available Monday and Thursday";
        }

        if (surgeon.equalsIgnoreCase("Dr. Nguyen")) {
            return "Available Wednesday and Friday";
        }

        if (surgeon.equalsIgnoreCase("Dr. Carter")) {
            return "Available Tuesday and Friday";
        }

        return "Availability not found";
    }

    private String generateDischargeInstructions(LabOrder order) {
        String procedure = order.getProcedureName();
        String scenario = order.getPostOpScenario();

        if (procedure == null) {
            return "No discharge instructions available.";
        }

        if (procedure.equalsIgnoreCase("Ureteroscopy")
                && scenario != null
                && scenario.equalsIgnoreCase("Stent Placed")) {
            return "Synthetic post-op template: Drink fluids as directed, monitor for fever or severe pain, and follow up for stent removal.";
        }

        if (procedure.equalsIgnoreCase("TURP")
                && scenario != null
                && scenario.equalsIgnoreCase("Catheter Placed")) {
            return "Synthetic post-op template: Follow catheter care instructions, monitor urine color, avoid heavy lifting, and attend follow-up visit.";
        }

        if (procedure.equalsIgnoreCase("Cystoscopy")) {
            return "Synthetic post-op template: Mild burning may occur, drink fluids as directed, and call the office for fever or heavy bleeding.";
        }

        if (procedure.equalsIgnoreCase("Hernia Repair")) {
            return "Synthetic post-op template: Keep incision clean and dry, avoid heavy lifting, and attend incision check follow-up.";
        }

        if (procedure.equalsIgnoreCase("Tonsillectomy")) {
            return "Synthetic post-op template: Stay hydrated, follow soft-food instructions, and seek care for bleeding or breathing concerns.";
        }

                return "Synthetic post-op template: Follow provider instructions and attend scheduled follow-up.";
    }

    public LabOrder updateOrder(Long id, LabOrder updatedOrder) {
        LabOrder existingOrder = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        existingOrder.setPatientName(updatedOrder.getPatientName());
        existingOrder.setProcedureName(updatedOrder.getProcedureName());
        existingOrder.setSurgeonName(updatedOrder.getSurgeonName());
        existingOrder.setSurgeryDate(updatedOrder.getSurgeryDate());
        existingOrder.setLabStatus(updatedOrder.getLabStatus());
        existingOrder.setInsuranceStatus(updatedOrder.getInsuranceStatus());
        existingOrder.setSurgicalStatus(updatedOrder.getSurgicalStatus());
        existingOrder.setPatientLocation(updatedOrder.getPatientLocation());
        existingOrder.setPriority(updatedOrder.getPriority());
        existingOrder.setPostOpScenario(updatedOrder.getPostOpScenario());
        existingOrder.setNotes(updatedOrder.getNotes());

        existingOrder.setOutcome(determineOutcome(existingOrder));
        existingOrder.setFollowUpTimeframe(determineFollowUpTimeframe(existingOrder));
        existingOrder.setFollowUpAvailability(determineFollowUpAvailability(existingOrder));
        existingOrder.setDischargeInstructions(generateDischargeInstructions(existingOrder));
        existingOrder.setSurgeryReady(determineReadiness(existingOrder));
        existingOrder.setReadinessReason(determineReadinessReason(existingOrder));

        return repository.save(existingOrder);
    }
    private Integer determineEstimatedDuration(LabOrder order) {
    String procedure = order.getProcedureName();

    if (procedure == null) {
        return 60;
    }

    if (procedure.equalsIgnoreCase("Cystoscopy")) {
        return 30;
    }

    if (procedure.equalsIgnoreCase("Ureteroscopy")) {
        return 60;
    }

    if (procedure.equalsIgnoreCase("TURP")) {
        return 90;
    }

    if (procedure.equalsIgnoreCase("Hernia Repair")) {
        return 120;
    }

    if (procedure.equalsIgnoreCase("Tonsillectomy")) {
        return 60;
    }

    return 60;
}

private String assignOperatingRoom(LabOrder order) {
    String surgeon = order.getSurgeonName();

    if (surgeon == null) {
        return "OR Pending";
    }

    if (surgeon.equalsIgnoreCase("Dr. Smith")) {
        return "OR 1";
    }

    if (surgeon.equalsIgnoreCase("Dr. Johnson")) {
        return "OR 2";
    }

    if (surgeon.equalsIgnoreCase("Dr. Patel")) {
        return "OR 3";
    }

    if (surgeon.equalsIgnoreCase("Dr. Nguyen")) {
        return "OR 4";
    }

    if (surgeon.equalsIgnoreCase("Dr. Carter")) {
        return "OR 5";
    }

    return "OR Pending";
    }
}
