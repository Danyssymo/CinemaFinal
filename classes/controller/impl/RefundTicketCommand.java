package project.cinema.classes.controller.impl;

import project.cinema.classes.controller.Command;
import project.cinema.classes.logic.CinemaLogic;
import project.cinema.classes.logic.LogicException;
import project.cinema.classes.logic.LogicProvider;

public class RefundTicketCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final CinemaLogic logic = logicProvider.getCinemaLogic();

    @Override
    public String execute(String request) {
        String response = null;
        String[] params;

        params = request.split("\n");
        String size = (params[1].split("=")[1]);
        int row = Integer.parseInt((params[2].split("=")[1]));
        int num = Integer.parseInt((params[3].split("=")[1]));

        try {
            logic.refundTicket(size,row,num);
            response = "The ticket was returned successfully";
        } catch (LogicException e) {
            response = "Ticket return error";
        }
        return response;
    }
}
