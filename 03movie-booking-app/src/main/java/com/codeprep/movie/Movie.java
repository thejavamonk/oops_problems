package com.codeprep.movie;

import java.time.Duration;

public class Movie {

    private final String title;
    private final String genre;
    private final int durationInMinutes;

    public Movie(String title, String genre, int durationInMinutes) {
        this.title = title;
        this.genre = genre;
        this.durationInMinutes = durationInMinutes;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public Duration getDurationInMinutes() {
        return Duration.ofMinutes(durationInMinutes);
    }
}
