package low.level.carRental.service;

import low.level.carRental.model.reservation.Invoice;
import low.level.carRental.model.reservation.VehicleReservation;

public interface InvoiceService {
    Invoice computeInvoice(VehicleReservation vehicleReservation);
}
