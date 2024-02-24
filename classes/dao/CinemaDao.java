package project.cinema.classes.dao;

import project.cinema.classes.dao.DaoException;
import project.cinema.classes.entity.Film;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Seat;

import java.util.List;

public interface CinemaDao {
    void save(Film film) throws DaoException;

    List<Film> allFilms() throws DaoException;
    List<Hall> allHalls() throws DaoException;

    void deleteAll() throws DaoException;
    void update(Film film) throws DaoException;

    void deleteById(int id) throws DaoException;
    void addHall(Hall hall) throws DaoException;
    void changeStatus(Seat s, Hall h) throws DaoException;
    void refundTicket(String size,int row, int num) throws DaoException;
}
