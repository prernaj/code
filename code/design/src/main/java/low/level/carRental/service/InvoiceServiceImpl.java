package low.level.carRental.service;

import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.ReservationStatus;
import low.level.carRental.model.reservation.VehicleReservation;

public class InvoiceServiceImpl implements InvoiceService {
    InvoiceServiceFactory invoiceServiceFactory = new InvoiceServiceFactory();

    @Override
    public Invoice computeInvoice(VehicleReservation vehicleReservation) {
        if (vehicleReservation.getStatus() == ReservationStatus.CANCELLED)
            return InvoiceBuilderUtil.buildCancelledInvoice(vehicleReservation);
        return invoiceServiceFactory.getInvoiceService(vehicleReservation.getVehicleReservationType())
                .computeInvoice(vehicleReservation);
    }
}