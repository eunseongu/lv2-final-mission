package finalmission.reservation.dto;

import finalmission.reservation.domain.Reservation;
import java.time.LocalDate;

public record ReservationResponse(
        Long id,
        LocalDate date,
        Long memberId,
        Long siteId
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(reservation.getId(), reservation.getDate(), reservation.getMember().getId(),
                reservation.getSite().getId());
    }
}
