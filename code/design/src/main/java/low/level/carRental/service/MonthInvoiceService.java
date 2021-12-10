package low.level.carRental.service;

import java.time.Duration;
import java.util.UUID;

import low.level.carRental.model.account.User;
import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.VehicleFixedCosts;
import low.level.carRental.model.reservation.VehicleMonthlyCosts;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.model.vehicle.HireableVehicle;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleRepository;

public class MonthInvoiceService implements InvoiceService {
    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusMonths(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        double days = Math.ceil(hours / 24) + hours % 24;

        double months = Math.ceil(days / 30);

        HireableVehicle hireableVehicle = VehicleRepository.vehicleMap
                .get(vehicleReservation.getAccocatedVehicleId());

        double monthlyCost = VehicleMonthlyCosts.vehicleMonthlyCost.get(hireableVehicle.getVehicleType());
        double fixedCost = VehicleFixedCosts.vehicleFixedCost.get(hireableVehicle.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        double addonServiceCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        invoice.setAddonCost(vehicleAddonCost);
        invoice.setAddonServicesCost(addonServiceCost);
        double rentalCost = months * monthlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}