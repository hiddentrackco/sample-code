package co.hiddentrack.partners_demo.model;

import co.hiddentrack.partners_demo.Utility;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;


public class Event {
    private String uid;
    private String title;
    private String text;
    private String location = "";
    @JsonProperty("etag")
    private String eTag;
    private String url;
    @JsonProperty("start")
    private Date startedAt;
    @JsonProperty("end")
    private Date endedAt;
    @JsonProperty("is_all_day")
    private boolean isAllDay;
    private String calendar;

    public Event(String title, String text, String url, String startedAt, String endedAt, boolean isAllDay) {
        this.uid = UUID.randomUUID().toString();
        this.title = title;
        this.text = text;
        this.url = url;
        try {
            this.startedAt = Utility.stringToDate(startedAt);
            this.endedAt = Utility.stringToDate(endedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.isAllDay = isAllDay;
        this.eTag = UUID.randomUUID().toString();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }

    public String getStartedAt() throws ParseException {
        return Utility.dateToString(startedAt);
    }

    public void setStartedAt(String startedAt) {
        try {
            this.startedAt = Utility.stringToDate(startedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getEndedAt() throws ParseException {
        return Utility.dateToString(endedAt);
    }

    public void setEndedAt(String endedAt) {
        try {
            this.endedAt = Utility.stringToDate(endedAt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setCalendarUid(String calendarUid) {
        this.calendar = calendarUid;
    }

    public String getCalendar() {
        return this.calendar;
    }

}
