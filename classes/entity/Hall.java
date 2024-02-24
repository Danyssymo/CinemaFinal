package project.cinema.classes.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Hall implements Serializable {

    private List<Seat> seats;

    private String size;
    private int quantityOfPlaces;

    public Hall(){
    }

    public Hall(List<Seat> seats, String size, int quantityOfPlaces) {
        this.seats = seats;
        this.size = size;
        this.quantityOfPlaces = quantityOfPlaces;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getQuantityOfPlaces() {
        return quantityOfPlaces;
    }

    public void setQuantityOfPlaces(int quantityOfPlaces) {
        this.quantityOfPlaces = quantityOfPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hall hall = (Hall) o;
        return quantityOfPlaces == hall.quantityOfPlaces && Objects.equals(seats, hall.seats) && Objects.equals(size, hall.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seats, size, quantityOfPlaces);
    }

    @Override
    public String toString() {
        return "Hall{" +
                "seats=" + seats +
                ", size='" + size + '\'' +
                ", quantityOfPlaces=" + quantityOfPlaces +
                '}';
    }
}
