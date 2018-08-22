package co.hiddentrack.partners_demo;

import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import co.hiddentrack.partners_demo.model.Article;
import co.hiddentrack.partners_demo.model.Calendar;
import co.hiddentrack.partners_demo.model.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class Demo {

    private static final Logger logger = Logger.getLogger(Demo.class.getName());

    private static final String LINDER_PARTNERS_CMS_API_HOST = "https://api.partners.linder.kr";
    private static final String API_KEY = "PLEASE_REPLACE_AS_YOUR_API_KEY";

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {

        // 운영하고 계신 CMS 시스템에 저장된 컨텐츠의 레코드 예시입니다.
        Article noticeArticle  = new Article("KLPGA 2018 정규투어(10월대회신청)",
                "제19회 하이트진로 챔피언십\n" +
                        "KB금융 스타 챔피언십\n" +
                        "SK네트웍스 서울경제 레이디스 클래식", "KLPGA 홈페이지");

        // 해당 컨텐츠와 관련된 일정 데이터를 생성합니다.
        // 일정 데이터의 형태는 린더 파트너스 CMS API 스펙에 정의된 Event 객체입니다.
        Event hiteJinroChampionship = new Event("제19회 하이트진로 챔피언십",
                noticeArticle.getContents(),
                "https://klpga.co.kr/web/office/entry.do",
                "2018-10-04 00:00:00", "2018-10-07 00:00:00", true);
        Event kbStarChampionship = new Event("SK네트웍스 서울경제 레이디스 클래식",
                noticeArticle.getContents(),
                "https://klpga.co.kr/web/office/entry.do",
                "2018-10-18 00:00:00", "2018-10-21 00:00:00", true);
        Event skNetworks = new Event("SK네트웍스 서울경제 레이디스 클래식",
               noticeArticle.getContents(),
               "https://klpga.co.kr/web/office/entry.do",
               "2018-10-26 00:00:00", "2018-10-28 00:00:00", true);

        // 여러 일정 데이터(Event)를 담고 있는 Calendar 객체를 API로도 생성하실 수 있습니다.
        Calendar klpgaCalendar = new Calendar("KLPGA 경기 일정", "KLPGA 경기 일정을 받아보실 수 있습니다.");
        createCalendar(klpgaCalendar);

        // 린더 파트너스 CMS API에서 제공드리는 벌크 API를 사용하여 다량의 Event를 한번에 처리할 수 있습니다.
        klpgaCalendar.addEvent(hiteJinroChampionship);
        klpgaCalendar.addEvent(kbStarChampionship);
        klpgaCalendar.addEvent(skNetworks);
        registerMultipleEvents(klpgaCalendar.getEvents());
    }

    // 단일 캘린더 생성 API 호출
    static void createCalendar(Calendar calendar) {
        logger.info(String.format("Create a new Calendar: %s / %s", calendar.getName(), calendar.getDescription()));

        List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new BasicNameValuePair("X-API-KEY", API_KEY));

        String params = null;
        try {
            params = mapper.writeValueAsString(calendar);

            // API 요청 부분
            String response = HttpClient.post(LINDER_PARTNERS_CMS_API_HOST + "/calendars/", headers, params);
            logger.info(String.format("Result: %s\n", response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    // 대량 일정 생성 API 호출
    static void registerMultipleEvents(List<Event> multipleEvents) {
        StringBuilder msg = new StringBuilder("Insert multiple Events like below:");
        for (Event event: multipleEvents) {
            try {
                msg.append(String.format("\n%s / %s ~ %s", event.getTitle(), event.getStartedAt(),
                        event.getEndedAt()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        logger.info(msg.toString());

        List<NameValuePair> headers = new ArrayList<NameValuePair>();
        headers.add(new BasicNameValuePair("X-API-KEY", API_KEY));

        String params = null;
        try {
            params = mapper.writeValueAsString(multipleEvents);

            // API 요청 부분
            String response = HttpClient.post(LINDER_PARTNERS_CMS_API_HOST + "/bulk/events", headers, params);
            logger.info(String.format("Result: %s", response));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
