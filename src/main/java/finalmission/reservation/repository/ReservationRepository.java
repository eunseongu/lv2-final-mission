package finalmission.reservation.repository;

import finalmission.reservation.domain.Reservation;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByMemberId(Long memberId);

    List<Reservation> findAll();
}
