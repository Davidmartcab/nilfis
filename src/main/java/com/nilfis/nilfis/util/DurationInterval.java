package com.nilfis.nilfis.util;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
        String intervalWithoutSpaces = interval.replace(" ", "");
        int[] time;

        if (intervalWithoutSpaces.contains("days")) {
            String[] parts = intervalWithoutSpaces.split("days");
            this.days = Integer.parseInt(parts[0]);
            if (parts.length > 1) {
                time = this.defragment(parts[1]);
                this.hours = time[0];
                this.minutes = time[1];
            } else {
                this.hours = 0;
                this.minutes = 0;
            }
        } else {
            time = this.defragment(intervalWithoutSpaces);
            this.days = 0;
            this.hours = time[0];
            this.minutes = time[1];
        }

        System.out.println(this.getInterval());
    }

    private int[] defragment(String interval) {
        int[] time = new int[3];
        String[] timeParts = interval.split(":");
        for (int i = 0; i < timeParts.length; i++) {
            time[i] = Integer.parseInt(timeParts[i]);
        }

        return time;
    }




}
