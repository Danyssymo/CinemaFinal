package project.cinema.classes.controller.impl;

import project.cinema.classes.controller.Command;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.logic.CinemaLogic;
import project.cinema.classes.logic.LogicException;
import project.cinema.classes.logic.LogicProvider;

public class BuildHallCommand implements Command {

    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final CinemaLogic logic = logicProvider.getCinemaLogic();

    @Override
    public String execute(String request) {
        String response = null;
        String[] params;
        Hall newHall;

        params = request.split("\n");
        newHall = new Hall();
        newHall.setSize(params[1].split("=")[1].toUpperCase());

        try {
            logic.build(newHall);
            response = "The hall would have been created successfully";
        } catch (LogicException e) {
            response = "An error occurred when creating the hall";
        }

        return response;
    }
}
