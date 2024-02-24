package project.cinema.classes.main;

import project.cinema.classes.controller.Controller;

public class Main {

    public static void main(String[] args) {

        Controller controller = new Controller();

        String request;
        String response;


//        request = "CLEAR\n";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "ADD\nname=Блондинки\ndutation=200\nprice=22\ngenre=comedy\ndescription=Family\nstart=2024-03-04 12:00:00";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "ADD\nname=Миагадаскар\ndutation=100\nprice=10\ngenre=roman\ndescription=Семейный";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "UPDATE\nid=3\nname=Магадаскар\ndutation=300\nprice=30\ngenre=comedy\ndescription=Семейный";
//        response = controller.doAction(request);
//        System.out.println(response);

        request = "OUTPUT\n";
        response = controller.doAction(request);
        System.out.println(response);
//
//        request = "FIND\niii";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "SORT\ndata";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "DELETE\nid=2";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "OUTPUT\n";
//        response = controller.doAction(request);
//        System.out.println(response);
//
//        request = "BUILD_HALL\nsize=big";
//        response = controller.doAction(request);
//        System.out.println(response);
//
        request = "SHOW_HALLS\n";
        response = controller.doAction(request);
        System.out.println(response);

//        request = "SELL\nfilm=3\nhall=mini\nseat_row=3\nseat_num=2";
//        response = controller.doAction(request);
//        System.out.println(response);

//        request = "CANCEL\nhall=mini\nseat_row=3\nseat_num=2";
//        response = controller.doAction(request);
//        System.out.println(response);
//
//        request = "SHOW_HALLS\n";
//        response = controller.doAction(request);
//        System.out.println(response);
    }
}
