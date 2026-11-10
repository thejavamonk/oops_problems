package com.codeprep;

import com.codeprep.cinema.Cinema;
import com.codeprep.cinema.Layout;
import com.codeprep.cinema.Room;
import com.codeprep.cinema.Screening;
import com.codeprep.cinema.Seat;
import com.codeprep.manager.ScreeningManager;
import com.codeprep.movie.Movie;
import com.codeprep.price.Ticket;
import com.codeprep.price.impl.NormalPricing;
import com.codeprep.price.impl.VIPPricing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieBookingSystemTest {

    private MovieBookingSystem system;
    private Movie movie;
    private Room room;
    private Screening screening;
    private Seat normalSeat;
    private Seat vipSeat;

    @BeforeEach
    void setUp() {
        system = new MovieBookingSystem(
                new ArrayList<>(),
                new ArrayList<>(),
                new ScreeningManager(new HashMap<>(), new HashMap<>()));

        // 2x2 layout; the default seats have no pricing, so replace them with priced ones
        Layout layout = new Layout(2, 2);
        normalSeat = new Seat("1-1", new NormalPricing(new BigDecimal("10.00")));
        vipSeat = new Seat("1-2", new VIPPricing(new BigDecimal("25.00")));
        layout.addSeat("1-1", 1, 1, normalSeat);
        layout.addSeat("1-2", 1, 2, vipSeat);
        layout.addSeat("2-1", 2, 1, new Seat("2-1", new NormalPricing(new BigDecimal("10.00"))));
        layout.addSeat("2-2", 2, 2, new Seat("2-2", new NormalPricing(new BigDecimal("10.00"))));

        room = new Room("R1", layout);
        movie = new Movie("Inception", "Sci-Fi", 148);
        LocalDateTime start = LocalDateTime.of(2026, 11, 10, 18, 0);
        screening = new Screening(movie, room, start, start.plusMinutes(148));
        system.addMovie(movie);
        system.addCinema(new Cinema("PVR", "Bangalore", new ArrayList<>(List.of(room))));
        system.addScreening(movie, screening);
    }

    @Test
    void getScreeningForMovie_returnsAddedScreening() {
        List<Screening> screenings = system.getScreeningForMovie(movie);
        assertEquals(1, screenings.size());
        assertSame(screening, screenings.get(0));
    }

    @Test
    void getScreeningForMovie_unknownMovie_returnsEmpty() {
        assertTrue(system.getScreeningForMovie(new Movie("Other", "Drama", 90)).isEmpty());
    }

    @Test
    void getAvailableSeats_beforeBooking_returnsAllSeats() {
        assertEquals(4, system.getAvailableSeats(screening).size());
    }

    @Test
    void bookTicket_createsTicketWithSeatPrice() {
        system.bookTicket(screening, vipSeat);

        List<Ticket> tickets = system.getTicketsForScreening(screening);
        assertEquals(1, tickets.size());
        assertSame(vipSeat, tickets.get(0).getSeat());
        assertSame(screening, tickets.get(0).getScreening());
        assertEquals(new BigDecimal("25.00"), tickets.get(0).getPrice());
    }

    @Test
    void bookTicket_removesSeatFromAvailableSeats() {
        system.bookTicket(screening, normalSeat);

        List<Seat> available = system.getAvailableSeats(screening);
        assertEquals(3, available.size());
        assertFalse(available.contains(normalSeat));
        assertTrue(available.contains(vipSeat));
    }

    @Test
    void getTicketCount_reflectsBookings() {
        assertEquals(0, system.getTicketCount(screening));
        system.bookTicket(screening, normalSeat);
        system.bookTicket(screening, vipSeat);
        assertEquals(2, system.getTicketCount(screening));
    }

    @Test
    void bookTicket_isScopedToScreening() {
        LocalDateTime start = LocalDateTime.of(2026, 11, 10, 21, 0);
        Screening late = new Screening(movie, room, start, start.plusMinutes(148));
        system.addScreening(movie, late);

        system.bookTicket(screening, normalSeat);

        assertEquals(1, system.getTicketCount(screening));
        assertEquals(0, system.getTicketCount(late));
        assertEquals(4, system.getAvailableSeats(late).size());
        assertEquals(2, system.getScreeningForMovie(movie).size());
    }

    @Test
    void bookTicket_sameSeatTwice_isCurrentlyAllowed() {
        // Documents current behaviour: the system does not reject double booking.
        // If validation is added later, flip this test to expect an exception.
        system.bookTicket(screening, normalSeat);
        system.bookTicket(screening, normalSeat);
        assertEquals(2, system.getTicketCount(screening));
    }
}
