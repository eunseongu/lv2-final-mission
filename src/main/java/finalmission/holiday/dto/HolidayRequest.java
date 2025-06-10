package finalmission.holiday.dto;

import java.time.Month;

public record HolidayRequest(
        int solYear,
        Month solMonth,
        String serviceKey,
        String type
) {
}
