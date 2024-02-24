package project.cinema.classes.output;


import project.cinema.classes.entity.Hall;
import project.cinema.classes.entity.Seat;

import java.util.List;

public class HallOutput {
    public void print(List<Hall> halls) {
        String str;
        for (Hall h : halls) {
            int counter = 0;
            System.out.print(h.getSize() + " " + h.getQuantityOfPlaces() + " Seats");
            for (Seat seat : h.getSeats()) {
                if (h.getQuantityOfPlaces() == 45) {
                    if (counter % 5 == 0) {
                        System.out.println();
                    }
                    if (!seat.isStatus()) {
                        str = "Free";
                    } else {
                        str = "Reserved";
                    }
                    System.out.print("|Row:" + seat.getRow() + " Num:" + seat.getNumber() + " Status:" + str + "|");
                    counter++;
                }
                if (h.getQuantityOfPlaces() == 30) {
                    if (counter % 5 == 0) {
                        System.out.println();
                    }
                    if (!seat.isStatus()) {
                        str = "Free";
                    } else {
                        str = "Reserved";
                    }
                    System.out.print("|Row:" + seat.getRow() + " Num:" + seat.getNumber() + " Status:" + str + "|");
                    counter++;
                }
                if (h.getQuantityOfPlaces() == 15) {
                    if (counter % 5 == 0) {
                        System.out.println();
                    }
                    if (!seat.isStatus()) {
                        str = "Free";
                    } else {
                        str = "Reserved";
                    }
                    System.out.print("|Row:" + seat.getRow() + " Num:" + seat.getNumber() + " Status:" + str + "|");
                    counter++;
                }

            }
            System.out.println();
        }
    }

}

