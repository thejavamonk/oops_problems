package com.codeprep.cinema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Layout {

    private final int rows;
    private final int columns;

    // Map seats by number to seat objects for direct access
    private final Map<String, Seat> seatsByNumber;

    // Nested map for position based access (row -> column -> seat)
    private final Map<Integer, Map<Integer, Seat>> seatsByPosition;

    public Layout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seatsByNumber = new HashMap<>();
        this.seatsByPosition = new HashMap<>();
        initializeLayout();
    }

    private void initializeLayout() {
        for (int row = 1; row <= rows; row++) {
            for (int col = 1; col <= columns; col++) {
                String seatNo = row + "-" + col;
                addSeat(seatNo, row, col, new Seat(seatNo, null));
            }
        }
    }

    public void addSeat(String seatNo, int row, int column, Seat seat) {

        // store the seat in number based lookup map
        seatsByNumber.put(seatNo, seat);
        // store the seat in position based lookup map
        seatsByPosition.computeIfAbsent(row, r -> new HashMap<>()).put(column, seat);
    }

    public Seat getSeatByNumber(String seatNo) {
        return seatsByNumber.get(seatNo);
    }

    public Seat getSeatByPosition(int row, int column) {
        Map<Integer, Seat> rowSeats = seatsByPosition.get(row);
        if (rowSeats != null) {
            return rowSeats.get(column);
        }
        return null;
    }

    public List<Seat> getAllSeats(){
        return List.copyOf(seatsByNumber.values());
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
