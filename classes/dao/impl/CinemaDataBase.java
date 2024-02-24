package project.cinema.classes.dao.impl;


import project.cinema.classes.dao.DaoException;
import project.cinema.classes.entity.Film;
import project.cinema.classes.util.CreateNewDate;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CinemaDataBase {

    private List<Film> films = new ArrayList<>();

    private final String PATH = "C:\\Users\\Msi-G6547\\IdeaProjects\\Hello1\\resourse\\cinemaFile.txt";
    private File file = new File(PATH);

    synchronized public void writeToFile(Film film) throws DaoException {
        try (FileWriter fw = new FileWriter(PATH, true)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            films.add(film);
            if (film.getStartOfTheFilm() == null) {
                film.setStartOfTheFilm(CreateNewDate.addDate());
            }
            String str = "Id=" + film.getId() + "&Name=" + film.getFilmName() + "&Duration=" + film.getFilmDuration() + "&Price=" + film.getTicketPrice() + "&Genre=" + film.getFilmGenre() + "&Discription=" + film.getFilmDescription() + "&Start=" + formatter.format(film.getStartOfTheFilm());
            fw.write(str);
            fw.append("\n");
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    synchronized public List<Film> readFromFile() throws DaoException {
        List<Film> films_ = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String[] params;
            String line = reader.readLine();

            while (line != null) {

                params = line.split("&");
                Film newFilm = new Film();
                newFilm.setId(Integer.parseInt(params[0].split("=")[1]));
                newFilm.setFilmName(params[1].split("=")[1]);
                newFilm.setFilmDuration(Integer.parseInt(params[2].split("=")[1]));
                newFilm.setTicketPrice(Integer.parseInt(params[3].split("=")[1]));
                newFilm.setFilmGenre(params[4].split("=")[1]);
                newFilm.setFilmDescription(params[5].split("=")[1]);
                String dateInString = (params[6].split("=")[1]);
                try {
                    Date date = formatter.parse(dateInString);
                    newFilm.setStartOfTheFilm(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                films_.add(newFilm);

                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException(e);
        }
        films = films_;
        return films;
    }


    public int countOfNotes() throws DaoException {
        readFromFile();
        return films.size();
    }

    private int lastId() throws DaoException {
        return countOfNotes();
    }

    public int getId() throws DaoException {
        return lastId() + 1;
    }

    public void clean() throws DaoException {
        films.clear();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(PATH);
        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        }
        writer.print("");
        writer.close();
    }

    public void update(Film film) throws DaoException {
        List<Film> temp = new ArrayList<>(readFromFile());
        for (Film film1 : temp) {
            if (film1.getId() == film.getId()) {
                film1.setFilmName(film.getFilmName());
                film1.setFilmDuration(film.getFilmDuration());
                film1.setTicketPrice(film.getTicketPrice());
                film1.setFilmGenre(film.getFilmGenre());
                film1.setFilmDescription(film.getFilmDescription());
                film1.setStartOfTheFilm(film.getStartOfTheFilm());
                break;
            }
        }
        clean();
        for (Film f : temp) {
            writeToFile(f);
        }
        films = temp;
    }

    public void deleteById(int id) throws DaoException {
        boolean flag = false;
        List<Film> temp = new ArrayList<>(readFromFile());
        List<Film> found = new ArrayList<>();
        clean();
        for (Film film : temp) {
            if (film.getId() != id) {
                if (!flag) {
                    found.add(film);
                } else {
                    film.setId(film.getId() - 1);
                    found.add(film);
                }
            } else {
                flag = true;
            }
        }

        for (Film film : found) {
            writeToFile(film);
        }
    }
}
