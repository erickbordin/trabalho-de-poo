package main;

import usecases.interfaces.IApplication;
import usecases.service.QuestaoUmApplicationService;

public class QuestaoUm {

    public static void main(String[] args) {

        IApplication application = new QuestaoUmApplicationService();
        
        application.comecarAplicacao();

    }

}
