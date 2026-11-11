package com.codeprep.manager;

import com.codeprep.cinema.Screening;
import com.codeprep.cinema.Seat;
import com.codeprep.movie.Movie;
import com.codeprep.price.Ticket;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScreeningManager {

    // maps movies to their scheduled screenings
    private final Map<Movie, List<Screening>> screeningsByMovie;
    // maps screening to tickets sold for that screening
    private final Map<Screening, List<Ticket>> ticketsByScreening;

    public ScreeningManager(Map<Movie, List<Screening>> screeningsByMovie, Map<Screening, List<Ticket>> ticketsByScreening) {
        this.screeningsByMovie = screeningsByMovie;
        this.ticketsByScreening = ticketsByScreening;
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningsByMovie.computeIfAbsent(movie, k -> new ArrayList<>()).add(screening);
    }

    //Return all screenings by movie
    public List<Screening> getScreeningsByMovie(Movie movie) {
        return screeningsByMovie.getOrDefault(movie, new ArrayList<>());
    }

    public void addTicket(Screening screening, Ticket ticket){
        ticketsByScreening.computeIfAbsent(screening, k -> new ArrayList<>()).add(ticket);
    }

    //Return all tickets by screening
    public List<Ticket> getTicketsByScreening(Screening screening) {
        return ticketsByScreening.getOrDefault(screening, new ArrayList<>());
    }

    //Calculate the seats available for a screening
    public List<Seat> getAvailableSeats(Screening screening) {
        List<Seat> availableSeats = new ArrayList<>(screening.getRoom().getLayout().getAllSeats());
        List<Ticket> soldTickets = getTicketsByScreening(screening);
        for (Ticket ticket : soldTickets) {
            availableSeats.remove(ticket.getSeat());
        }
        return availableSeats;
    }

    //simplified optimistic locking screening manager
    public synchronized Ticket bookSeatOptimistically(Screening screening, Seat seat) {

        // first check if the seat is available (optimistic)
        if (isSeatBooked(screening, seat)) {
            throw new IllegalArgumentException("Seat is already booked");
        }

        // create ticket - at this point we are optimistically assuming the seat is still available
        BigDecimal price = seat.getPricingStrategy().getPrice();
        List<Seat> availableSeats = getAvailableSeats(screening);
        if (availableSeats.contains(seat)) {
            Ticket ticket = new Ticket(screening, seat, price);
            addTicket(screening, ticket);
            return ticket;
        }
        return null;
    }

    private boolean isSeatBooked(Screening screening, Seat seat) {
        List<Ticket> tickets = getTicketsByScreening(screening);
        for (Ticket ticket : tickets) {
            if (ticket.getSeat().equals(seat)) {
                return true;
            }
        }
        return false;
    }
}
