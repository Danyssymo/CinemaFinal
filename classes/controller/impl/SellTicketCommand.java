package project.cinema.classes.controller.impl;

import project.cinema.classes.controller.Command;
import project.cinema.classes.logic.CinemaLogic;
import project.cinema.classes.logic.LogicException;
import project.cinema.classes.logic.LogicProvider;
import project.cinema.classes.output.TicketOutput;

public class SellTicketCommand implements Command {
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final CinemaLogic logic = logicProvider.getCinemaLogic();

    @Override
    public String execute(String request) {
        String response = null;
        String[] params;

        params = request.split("\n");

        int filmId = Integer.parseInt(params[1].split("=")[1]);
        String hallSize = params[2].split("=")[1];
        int seat_row = Integer.parseInt(params[3].split("=")[1]);
        int seat_num = Integer.parseInt(params[4].split("=")[1]);

        try {
            TicketOutput output = new TicketOutput();
            output.print(logic.createTicket(hallSize,filmId,seat_row,seat_num));
            response = "The ticket was sold successfully";
        } catch (LogicException e) {
            response = "Ticket sale error";
        }

        return response;
    }
}
