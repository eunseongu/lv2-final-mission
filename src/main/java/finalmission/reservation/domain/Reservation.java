package finalmission.reservation.domain;

import finalmission.member.domain.Member;
import finalmission.site.domain.Site;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    private Member member;

    @ManyToOne
    private Site site;

    public Reservation(Long id, LocalDate date, Member member, Site site) {
        this.id = id;
        this.date = date;
        this.member = member;
        this.site = site;
    }

    public Reservation() {

    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Member getMember() {
        return member;
    }

    public Site getSite() {
        return site;
    }
}
