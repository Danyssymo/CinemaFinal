package project.cinema.classes.dao.impl;

import project.cinema.classes.dao.CinemaDao;
import project.cinema.classes.dao.DaoException;
import project.cinema.classes.entity.Film;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Seat;
import project.cinema.classes.util.CreateNewDate;

import java.util.List;

public class FileCinemaDao implements CinemaDao {
    CinemaDataBase cinemaDB = new CinemaDataBase();
    HallDataBase hallDB = new HallDataBase();

    @Override
    public void save(Film film) throws DaoException {
        int id = cinemaDB.getId();
        film.setId(id);

        if (film.getStartOfTheFilm() == null) {
            film.setStartOfTheFilm(CreateNewDate.addDate());
        }

        cinemaDB.writeToFile(film);
    }

    @Override
    public List<Film> allFilms() throws DaoException {
        return cinemaDB.readFromFile();
    }

    @Override
    public List<Hall> allHalls() throws DaoException {
        return hallDB.readAllHalls();
    }

    @Override
    public void deleteAll() throws DaoException {
        cinemaDB.clean();
    }

    @Override
    public void update(Film film) throws DaoException {
        cinemaDB.update(film);
    }

    @Override
    public void deleteById(int id) throws DaoException {
        cinemaDB.deleteById(id);
    }

    @Override
    public void addHall(Hall hall) throws DaoException {
        hallDB.addHallToTheCinema(hall);
    }

    @Override
    public void changeStatus(Seat s, Hall h) throws DaoException {
        hallDB.changeStatus(s, h);
    }

    @Override
    public void refundTicket(String size, int row, int num) throws DaoException {
        hallDB.refundTicket(size, row, num);
    }
}
