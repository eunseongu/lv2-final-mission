package finalmission.holiday.service;

import finalmission.holiday.dto.HolidayResponse;
import java.time.LocalDate;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class HolidayService {

    private static final String END_POINT = "https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getHoliDeInfo";
    private final String secretKey;

    public HolidayService(@Value("${holiday.secret-key}") final String secretKey) {
        this.secretKey = secretKey;
    }

    public boolean isHoliday(LocalDate date) {

        RestClient restClient = RestClient.create();

        HolidayResponse[] responses = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(END_POINT)
                        .queryParam("solYear", date.getYear())
                        .queryParam("solMonth", date.getMonthValue())
                        .queryParam("ServiceKey", secretKey)
                        .queryParam("_type", "json")
                        .build())
                .retrieve()
                .body(HolidayResponse[].class);

        return Arrays.stream(responses)
                .anyMatch(holidayResponse -> holidayResponse.date().isEqual(date));
    }
}
