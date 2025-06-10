package finalmission.reservation.service;

import finalmission.auth.dto.LoginMember;
import finalmission.member.domain.Member;
import finalmission.member.repository.MemberRepository;
import finalmission.reservation.domain.Reservation;
import finalmission.reservation.dto.MyReservationResponse;
import finalmission.reservation.dto.ReservationRequest;
import finalmission.reservation.dto.ReservationResponse;
import finalmission.reservation.repository.ReservationRepository;
import finalmission.site.domain.Site;
import finalmission.site.repository.SiteRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {


    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final SiteRepository siteRepository;

    public ReservationService(ReservationRepository reservationRepository, MemberRepository memberRepository,
                              SiteRepository siteRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.siteRepository = siteRepository;
    }

    public ReservationResponse create(ReservationRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다."));
        Site site = siteRepository.findById(request.siteId())
                .orElseThrow(() -> new IllegalArgumentException("장소 정보가 없습니다."));
        Reservation reservation = request.toReservation(member, site);
        Reservation savedReservation = reservationRepository.save(reservation);
        return ReservationResponse.from(savedReservation);
    }

    public List<MyReservationResponse> findAllMyReservations(LoginMember loginMember) {
        return reservationRepository.findByMemberId(loginMember.id()).stream()
                .map(MyReservationResponse::from)
                .toList();
    }

    public void delete(Long memberId) {
        reservationRepository.deleteById(memberId);
    }

    public List<ReservationResponse> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationResponse::from)
                .toList();
    }
}
