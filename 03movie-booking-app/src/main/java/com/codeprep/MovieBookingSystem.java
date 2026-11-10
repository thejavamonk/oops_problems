package com.codeprep;

import com.codeprep.cinema.Cinema;
import com.codeprep.cinema.Screening;
import com.codeprep.cinema.Seat;
import com.codeprep.manager.ScreeningManager;
import com.codeprep.movie.Movie;
import com.codeprep.price.Ticket;

import java.math.BigDecimal;
import java.util.List;

public class MovieBookingSystem {

    private final List<Movie> movies;
    private final List<Cinema> cinemas;
    private final ScreeningManager screeningManager;

    public MovieBookingSystem(List<Movie> movies, List<Cinema> cinemas, ScreeningManager screeningManager) {
        this.movies = movies;
        this.cinemas = cinemas;
        this.screeningManager = screeningManager;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void addScreening(Movie movie, Screening screening){
        screeningManager.addScreening(movie, screening);
    }

    // Books a ticket for a specific seat at a screening
    public void bookTicket(Screening screening, Seat seat){
        BigDecimal price = seat.getPricingStrategy().getPrice();
        Ticket ticket = new Ticket(screening, seat, price);
        screeningManager.addTicket(screening, ticket);
    }

    // Returns all screenings for a specific movie
    public List<Screening> getScreeningForMovie(Movie movie){
        return screeningManager.getScreeningsByMovie(movie);
    }

    // Returns all available seats for a screening
    public List<Seat> getAvailableSeats(Screening screening){
        return screeningManager.getAvailableSeats(screening);
    }

    // Returns the number of tickets sold for a screening
    public int getTicketCount(Screening screening){
        return screeningManager.getTicketsByScreening(screening).size();
    }

    // Returns the list of tickets for a screening
    public List<Ticket> getTicketsForScreening(Screening screening){
        return screeningManager.getTicketsByScreening(screening);
    }

}
