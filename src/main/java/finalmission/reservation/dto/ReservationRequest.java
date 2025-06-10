package finalmission.reservation.dto;

import finalmission.member.domain.Member;
import finalmission.reservation.domain.Reservation;
import finalmission.site.domain.Site;
import java.time.LocalDate;

public record ReservationRequest(
        LocalDate date,
        Long memberId,
        Long siteId
) {
    public Reservation toReservation(ReservationRequest request, Member member, Site site) {
        return new Reservation(null, request.date, member, site);
    }

    public Reservation toReservation(Member member, Site site) {
        return new Reservation(null, date, member, site);
    }
}
