package project.cinema.classes.logic;

import project.cinema.classes.entity.Film;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Ticket;

import java.util.List;

public interface CinemaLogic {
    void add(Film film) throws LogicException;// update

    List<Film> find(String text) throws LogicException;

    List<Film> allFilms() throws LogicException;

    List<Hall> allHalls() throws LogicException;

    void clean() throws LogicException;

    void update(Film film) throws LogicException;

    List<Film> sort(String text) throws LogicException;

    void deleteById(int id) throws LogicException;

    void build(Hall hall) throws LogicException;

    void refundTicket(String size,int row, int num) throws LogicException;

    Ticket createTicket(String hallSize, int filmId, int seat_row, int seat_num) throws LogicException;
}
