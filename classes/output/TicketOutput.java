package project.cinema.classes.output;

import project.cinema.classes.entity.Ticket;

public class TicketOutput {

    public void print(Ticket ticket){
        System.out.println();
        System.out.println("------------------------------------------------------");
        System.out.println("YOUR TICKET");
        System.out.println("------------------------------------------------------");
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name: " + ticket.getFilm().getFilmName() + " | Duration: " + ticket.getFilm().getFilmDuration());
        System.out.println("Genre: " + ticket.getFilm().getFilmGenre() + " | Start: " + ticket.getFilm().getStartOfTheFilm());
        System.out.println("Description: " + ticket.getFilm().getFilmDescription());
        System.out.println("Hall size: " + ticket.getHall().getSize());
        System.out.println("Your seat: " + ticket.getSeat().getRow() + "|" + ticket.getSeat().getNumber());
        System.out.println("Ticket price: " + ticket.getPrice());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
