package project.cinema.classes.controller.impl;

import project.cinema.classes.controller.Command;
import project.cinema.classes.entity.Hall;
import project.cinema.classes.logic.CinemaLogic;
import project.cinema.classes.logic.LogicException;
import project.cinema.classes.logic.LogicProvider;
import project.cinema.classes.output.HallOutput;

import java.util.List;

public class ShowAllHallsCommand implements Command{
    private final LogicProvider logicProvider = LogicProvider.getInstance();
    private final CinemaLogic logic = logicProvider.getCinemaLogic();

    @Override
    public String execute(String request) {
        String response = null;
        List<Hall> myHalls = null;
        try {
            myHalls = logic.allHalls();
        } catch (LogicException e) {

        }

        HallOutput hallOutput = new HallOutput();
        hallOutput.print(myHalls);
        if (myHalls == null || myHalls.size() <=0){
            response = "There are no records to output.";
        } else {
            response = "The output was successful";
        }

        return response;
    }
}
