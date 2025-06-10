package finalmission.holiday.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import io.restassured.RestAssured;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class HolidayServiceTest {

    private final RestClient.Builder testBuilder = RestClient.builder();
    private final MockRestServiceServer server = MockRestServiceServer.bindTo(testBuilder).build();

    @LocalServerPort
    int port;
    @Value("${holiday.secret-key}")
    private String secretKey;
    @Autowired
    private final HolidayService holidayService = new HolidayService(secretKey);

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @DisplayName("공휴일 데이터 요청")
    @Test
    void test() {
        String uri =
                "http://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getAnniversaryInfo?solYear=2025&solMonth=08&ServiceKey="
                        + secretKey + "_type=json";
        server.expect(MockRestRequestMatchers.requestTo(uri))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess("""
                        {
                          "date": "20250815",
                          "dateName": "광복절"
                        }
                        """, APPLICATION_JSON));

        boolean isHoliday = holidayService.isHoliday(LocalDate.of(2025, 8, 15));
        assertThat(isHoliday).isTrue();
    }

}