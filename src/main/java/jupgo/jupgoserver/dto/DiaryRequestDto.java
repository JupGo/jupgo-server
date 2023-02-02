package jupgo.jupgoserver.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DiaryRequestDto {
    private LocalDate date;
    private String location;
    private int distance;
    private Duration duration;
    private String photo;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateString);
        Instant instant = Instant.ofEpochMilli(date.getTime());
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zonedDateTime.toLocalDate();
        this.date = localDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(String durationString) {
        String[] timeIndexes = durationString.split(":");
        String durationFormat =
                "PT" + timeIndexes[0] + "H" + timeIndexes[1] + "M" + timeIndexes[2] + "S";
        this.duration = Duration.parse(durationFormat);
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("date: ").append(date).append("\n")
                .append("location: ").append(location).append("\n")
                .append("distance: ").append(distance).append("\n")
                .append("duration: ").append(duration).append("\n")
                .append("photo: ").append(photo);
        return stringBuilder.toString();
    }
}
