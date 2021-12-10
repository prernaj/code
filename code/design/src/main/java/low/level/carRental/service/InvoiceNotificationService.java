package low.level.carRental.service;

import low.level.carRental.model.reservation.InvoiceNotification;

public interface InvoiceNotificationService {
    void notifyUser(InvoiceNotification invoice);
}
