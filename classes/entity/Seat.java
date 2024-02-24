package project.cinema.classes.entity;

import java.io.Serializable;
import java.util.Objects;

public class Seat implements Serializable {

    private int row;
    private int number;
    private boolean status;

    public Seat() {

    }

    public Seat(int row, int number, boolean status) {
        this.row = row;
        this.number = number;
        this.status = status;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row && number == seat.number && status == seat.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number, status);
    }

    @Override
    public String toString() {
        return "[row=" + row +
                ", number=" + number +
                ", status=" + status +
                ']';
    }
}
