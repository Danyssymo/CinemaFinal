package project.cinema.classes.dao.impl;


import project.cinema.classes.dao.DaoException;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Seat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class HallDataBase {

    private List<Hall> halls = new ArrayList<>();
    private final String PATH = "C:\\Users\\Msi-G6547\\IdeaProjects\\Hello1\\resourse\\hallFile.txt";

    private File file = new File(PATH);

    public void addHallToTheCinema(Hall hall) throws DaoException {
        try (FileWriter fw = new FileWriter(PATH, true)) {
            halls.add(hall);
            Seat[][] seats;
            if (hall.getQuantityOfPlaces() == 15) {
                int i = 3;
                int j = 5;
                seats = new Seat[i][j];
                for (i = 0; i < 3; i++) {
                    for (j = 0; j < 5; j++) {
                        seats[i][j] = (new Seat());
                        seats[i][j].setRow(i + 1);
                        seats[i][j].setNumber(j + 1);
                        fw.write(String.valueOf(seats[i][j]));
                        fw.append('&');
                    }
                }
                fw.append("\n");
            } else if (hall.getQuantityOfPlaces() == 30) {
                int i = 6;
                int j = 5;
                seats = new Seat[i][j];
                for (i = 0; i < 6; i++) {
                    for (j = 0; j < 5; j++) {
                        seats[i][j] = (new Seat());
                        seats[i][j].setRow(i + 1);
                        seats[i][j].setNumber(j + 1);
                        fw.write(String.valueOf(seats[i][j]));
                        fw.append('&');
                    }
                }
                fw.append("\n");
            } else if (hall.getQuantityOfPlaces() == 45) {
                int i = 9;
                int j = 5;
                seats = new Seat[i][j];
                for (i = 0; i < 9; i++) {
                    for (j = 0; j < 5; j++) {
                        seats[i][j] = (new Seat());
                        seats[i][j].setRow(i + 1);
                        seats[i][j].setNumber(j + 1);
                        fw.write(String.valueOf(seats[i][j]));
                        fw.append('&');
                    }
                }
                fw.append("\n");
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }

    private void deleteCollection() {
        halls.clear();
    }

    public List<Hall> readAllHalls() throws DaoException {
        deleteCollection();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            String[] params;
            while (reader.read() != -1) {
                Hall newHall = new Hall();
                List<Seat> seats = new ArrayList<>();
                String line = reader.readLine();
                params = line.split("&");
                for (int i = 0; i < params.length; i++) {
                    Seat seat = new Seat();
                    seat.setRow(Integer.parseInt(params[i].split(",")[0].split("=")[1]));
                    seat.setNumber(Integer.parseInt(params[i].split(",")[1].split("=")[1]));
                    String st = (params[i].split(",")[2].split("=")[1]);
                    if (st.equals("true]")) {
                        seat.setStatus(true);
                    } else {
                        seat.setStatus(false);
                    }
                    seats.add(seat);
                }
                newHall.setQuantityOfPlaces(seats.size());
                newHall.setSeats(seats);
                if (seats.size() == 15) {
                    newHall.setSize("mini");
                } else if (seats.size() == 30) {
                    newHall.setSize("standart");
                } else if (seats.size() == 45) {
                    newHall.setSize("big");
                }

                halls.add(newHall);
            }
        } catch (FileNotFoundException e) {
            throw new DaoException(e);
        } catch (IOException e) {
            throw new DaoException(e);
        }
        return halls;
    }

    public void changeStatus(Seat s, Hall h) throws DaoException {
        List<Hall> myHalls = new ArrayList<>(readAllHalls());
        List<Hall> tempHalls = new ArrayList<>();
        for (Hall myHall : myHalls) {
            if (myHall.equals(h)) {
                for (Seat seat : myHall.getSeats()) {
                    if (seat.equals(s)) {
                        seat.setStatus(true);
                    }
                }
            }
            tempHalls.add(myHall);
        }
        cleanHalls();
        for (Hall tempHall : tempHalls) {
            updateHallToTheCinema(tempHall);
        }
    }


    private void cleanHalls() {
        halls.clear();
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(PATH);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print("");
        writer.close();
    }

    public void updateHallToTheCinema(Hall hall) throws DaoException {
        try (FileWriter fw = new FileWriter(PATH, true)) {
            List<Seat> seats_ = hall.getSeats();
            for (Seat seat_ : seats_) {
                fw.write(String.valueOf(seat_));
                fw.append('&');
            }
            fw.append("\n");

        } catch (IOException e) {
            throw new DaoException(e);
        }

    }

    public void refundTicket(String size, int row, int num) throws DaoException {
        try (FileWriter fw = new FileWriter(PATH, true)) {
            List<Hall> myHalls = new ArrayList<>(readAllHalls());
            cleanHalls();
            for (Hall myHall : myHalls) {
                if (myHall.getSize().equals(size)) {
                    for (Seat seat : myHall.getSeats()) {
                        if (seat.getRow() == row && seat.getNumber() == num) {
                            seat.setStatus(false);
                        }
                    }
                }
            }
            for (Hall myHall : myHalls) {
                addHallToTheCinema(myHall);
            }
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
