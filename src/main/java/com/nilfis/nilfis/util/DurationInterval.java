package com.nilfis.nilfis.util;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable
public class DurationInterval {

    private int days;

    private int hours;

    private int minutes;

    public String getInterval() {
        return days + " days " + hours + ":" + minutes + ":00";
    }

    public void setInterval(String interval) {
        try {
            Period period = parseInterval(interval);
            this.days = period.getDays();
            this.hours = 0;
            this.minutes = 0;
        } catch (DateTimeParseException e) {
            System.out.println("Error: " + e.getMessage());
            this.days = 0;
            this.hours = 0;
            this.minutes = 0;
        }
    }

    private Period parseInterval(String interval) {
        Pattern monthsPattern = Pattern.compile("(\\d+) months?");
        Pattern daysPattern = Pattern.compile("(\\d+) days?");

        Matcher monthsMatcher = monthsPattern.matcher(interval);
        Matcher daysMatcher = daysPattern.matcher(interval);

        int totalDays = 0;

        if (monthsMatcher.find()) {
            int months = Integer.parseInt(monthsMatcher.group(1));
            totalDays += months * 30;
        }

        if (daysMatcher.find()) {
            int days = Integer.parseInt(daysMatcher.group(1));
            totalDays += days;
        }

        return Period.ofDays(totalDays);
    }

}
