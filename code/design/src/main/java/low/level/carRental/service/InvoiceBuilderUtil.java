package low.level.carRental.service;

import java.util.UUID;

import low.level.carRental.model.account.User;
import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.VehicleFixedCosts;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleRepository;

public class InvoiceBuilderUtil {
    public static Invoice buildCancelledInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap.get(vehicleReservation.getAccocatedVehicleId());
        double fixedCost = VehicleFixedCosts.vehicleFixedCost.get(hireableVehicle.getVehicleType());
        double taxes = fixedCost * .18;
        invoice.setUsageCharges(fixedCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(fixedCost + taxes);
        return invoice;
    }
}
