package platform.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity(name = "Code")
@JsonIgnoreProperties({"id", "dateTime", "timeRestricted", "viewsRestricted"})
public class Code {
    public static final String DEFAULT_STR_CODE = "public static void main(String[] args) {\n" +
            "    SpringApplication.run(CodeSharingPlatform.class, args);\n}";
    public static final Code DEFAULT_CODE = new Code();

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss");

    @Column
    private String code;
    @Column
    private LocalDateTime dateTime;
    @Id
    @GeneratedValue
    private UUID id;
    @Column
    private long time;
    @Column
    private long views;

    @Transient
    private boolean timeRestricted;
    @Transient
    private boolean viewsRestricted;

    public Code(String code, LocalDateTime dateTime, long time, long views) {
        this.code = code;
        this.dateTime = dateTime;
        this.time = time;
        this.views = views;
        timeRestricted = time > 0;
        viewsRestricted = views > 0;
    }

    public Code(String code, String date, long time, long views) {
        this(code, LocalDateTime.parse(date), time, views);
    }

    public Code() {
        this(DEFAULT_STR_CODE, LocalDateTime.now(), 0, 0);
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public boolean isTimeRestricted() {
        return timeRestricted;
    }

    public boolean isViewsRestricted() {
        return viewsRestricted;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonProperty("date")
    public String getDateAsString() {
        return dateTime.format(formatter);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime date) {
        this.dateTime = date;
    }

    public void setTimeRestricted() {
        this.timeRestricted = time > 0;
    }

    public void setViewsRestricted() {
        this.viewsRestricted = views > 0;
    }
}
