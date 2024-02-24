package project.cinema.classes.logic.impl;


import project.cinema.classes.dao.CinemaDao;
import project.cinema.classes.dao.DaoException;
import project.cinema.classes.dao.DaoProvider;
import project.cinema.classes.entity.Film;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Seat;
import project.cinema.classes.entity.Ticket;
import project.cinema.classes.logic.CinemaLogic;
import project.cinema.classes.logic.LogicException;
import project.cinema.classes.logic.comparator.*;
import project.cinema.classes.output.CinemaOutput;

import java.util.ArrayList;
import java.util.List;

public class CinemaLogicImpl implements CinemaLogic {
    private final DaoProvider provider = DaoProvider.getInstance();
    private final CinemaDao dao = provider.getCinemaDao();

    @Override
    public void add(Film film) throws LogicException {
        try {
            dao.save(film);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public List<Film> find(String text) throws LogicException {
        List<Film> result = new ArrayList<>();

        List<Film> myFilms;
        try {
            myFilms = dao.allFilms();
        } catch (DaoException e) {
            throw new LogicException(e);
        }

        for (Film f : myFilms) {
            if (isTextInNote(f, text)) {
                result.add(f);
            }
        }
        CinemaOutput output = new CinemaOutput();
        output.print("search by: " + text, result);
        return result;
    }

    private boolean isTextInNote(Film f, String text) throws LogicException {
        return f.getFilmName().contains(text) || f.getFilmGenre().contains(text) || f.getFilmDescription().contains(text);
    }

    @Override
    public List<Film> allFilms() throws LogicException {
        try {
            return dao.allFilms();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public List<Hall> allHalls() throws LogicException {
        try {
            return dao.allHalls();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void clean() throws LogicException {
        try {
            dao.deleteAll();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void update(Film film) throws LogicException {
        try {
            dao.update(film);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public List<Film> sort(String text) throws LogicException {
        List<Film> fi;
        try {
            fi = dao.allFilms();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        if (text.equals("NAME")) {
            fi.sort(new FilmNameComparator());
        }
        if (text.equals("DURATION")) {
            fi.sort(new FilmDurationComparator());
        }
        if (text.equals("PRICE")) {
            fi.sort(new FilmPriceComparator());
        }
        if (text.equals("GENRE")) {
            fi.sort(new FilmGenreComparator());
        }
        if (text.equals("DESCRIPTION")) {
            fi.sort(new FilmDescriptionComparator());
        }
        if (text.equals("DATA")) {
            fi.sort(new FilmDataComparator());
        }
        return fi;
    }

    @Override
    public void deleteById(int id) throws LogicException {
        try {
            dao.deleteById(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void build(Hall hall) throws LogicException {
        if (hall.getSize().equals("MINI")) {
            hall.setQuantityOfPlaces(15);
        }
        if (hall.getSize().equals("STANDART")) {
            hall.setQuantityOfPlaces(30);
        }
        if (hall.getSize().equals("BIG")) {
            hall.setQuantityOfPlaces(45);
        }
        try {
            dao.addHall(hall);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void refundTicket(String size, int row, int num) throws LogicException {
        try {
            dao.refundTicket(size, row, num);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Ticket createTicket(String hallSize, int filmId, int seat_row, int seat_num) throws LogicException {
        Ticket ticket = new Ticket();
        boolean flag = false;
        try {
            List<Hall> tempHalls = new ArrayList<>(dao.allHalls());
            for (Hall h : tempHalls) {
                if (h.getSize().equals(hallSize)) {
                    ticket.setHall(h);
                    for (Seat s : h.getSeats()) {
                        if (s.getRow() == seat_row && s.getNumber() == seat_num) {
                            ticket.setSeat(s);
                            dao.changeStatus(s, h);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }
            }
            for (Film f : dao.allFilms()) {
                if (f.getId() == filmId) {
                    ticket.setFilm(f);
                }
            }
            ticket.setPrice(calculateTicketPrice(ticket.getHall(),ticket.getFilm()));

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return ticket;
    }

    private double calculateTicketPrice(Hall hall, Film film) {
        double price = 0;
        if (hall.getSize().equals("mini")) {
            price = film.getTicketPrice() * 1.3;
        } else if (hall.getSize().equals("standart")) {
            price = film.getTicketPrice() * 1.5;
        } else if (hall.getSize().equals("big")) {
            price = film.getTicketPrice() * 1.7;
        }
        return price;
    }
}
