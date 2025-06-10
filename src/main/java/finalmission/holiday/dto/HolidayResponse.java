package finalmission.holiday.dto;

import java.time.LocalDate;

public record HolidayResponse(
        LocalDate date,
        String dateName
) {
}
