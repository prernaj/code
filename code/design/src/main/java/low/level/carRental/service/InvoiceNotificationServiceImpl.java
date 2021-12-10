package low.level.carRental.service;

import low.level.carRental.model.account.User;
import low.level.carRental.model.common.NotificationStatus;
import low.level.carRental.model.reservation.InvoiceNotification;
import low.level.carRental.model.reservation.VehicleReservation;
import low.level.carRental.repository.UserRepository;
import low.level.carRental.repository.VehicleReservationRepository;

public class InvoiceNotificationServiceImpl implements InvoiceNotificationService {

    public void notifyUser(InvoiceNotification invoiceNotification) {
        VehicleReservation vehicleReservation = VehicleReservationRepository.vehicleReservationMap.get(invoiceNotification.getReservationId());
        User user = UserRepository.userUserIdMap.get(vehicleReservation.getUsrId());
        System.out.println("Notification sent " + user.getContact().getEmail());
        invoiceNotification.setNotificationStatus(NotificationStatus.SENT);
    }
}
