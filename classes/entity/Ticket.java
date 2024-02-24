package project.cinema.classes.entity;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {

    private Film film;
    private Seat seat;
    private Hall hall;
    private double price;

    public Ticket(){

    }
    public Ticket(Film film, Seat seat, Hall hall, double price) {
        this.film = film;
        this.seat = seat;
        this.hall = hall;
        this.price = price;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(price, ticket.price) == 0 && Objects.equals(film, ticket.film) && Objects.equals(seat, ticket.seat) && Objects.equals(hall, ticket.hall);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film, seat, hall, price);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "film=" + film +
                ", seat=" + seat +
                ", hall=" + hall +
                ", price=" + price +
                '}';
    }
}
