package low.level.carRental.service;

import java.time.Duration;
import java.util.UUID;

import low.level.carRental.model.account.User;
import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.VehicleFixedCosts;
import low.level.carRental.model.reservation.VehicleHourlyCosts;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.repository.UserRepository;

public class HourInvoiceService implements InvoiceService {

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        return buildInvoice(vehicleReservation);
    }

    private Invoice buildInvoice(VehicleReservation vehicleReservation) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(UUID.randomUUID().toString());
        invoice.setReservationId(vehicleReservation.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        invoice.setUserId(user.getEmail());
        Duration rentedDuration;
        if (vehicleReservation.getReturnDate() == null)
            rentedDuration =
                    Duration.between(vehicleReservation.getFromDate(),
                            vehicleReservation.getFromDate().plusHours(1));
        else
            rentedDuration = Duration.between(vehicleReservation.getFromDate(),
                    vehicleReservation.getReturnDate());

        double hours = Math.ceil(rentedDuration.toHours());

        if (hours == 0)
            hours = 1;

        double hourlyCost = VehicleHourlyCosts.vehicleHourlyCost.get(vehicleReservation.getVehicleType());
        double fixedCost = VehicleFixedCosts.vehicleFixedCost.get(vehicleReservation.getVehicleType());

        double vehicleAddonCost = AddonCostUtil.computeEquipmentCost(vehicleReservation);
        invoice.setAddonCost(vehicleAddonCost);
        double addonServiceCost = AddonCostUtil.computeServiceCost(vehicleReservation);
        invoice.setAddonServicesCost(addonServiceCost);
        double rentalCost = hours * hourlyCost + fixedCost + vehicleAddonCost + addonServiceCost;
        double taxes = rentalCost * .18;

        invoice.setUsageCharges(rentalCost);
        invoice.setTaxes(taxes);
        invoice.setTotal(rentalCost + taxes);
        return invoice;
    }
}