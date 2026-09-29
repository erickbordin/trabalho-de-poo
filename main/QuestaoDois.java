package main;

import usecases.interfaces.IApplication;
import usecases.service.QuestaoDoisApplicationService;

public class QuestaoDois {

    public static void main(String[] args) {

        IApplication application = new QuestaoDoisApplicationService();

        application.comecarAplicacao();

    }

}
