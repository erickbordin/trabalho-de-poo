package usecases.service;

import java.util.Scanner;


import usecases.interfaces.IApplication;

public class QuestaoUmApplicationService implements IApplication {

    @Override
    public void comecarAplicacao() {

        Scanner scanner = new Scanner(System.in);
        OperationsService os = new OperationsService(scanner);
        
        boolean sair = false;

        while (sair == false) {
            System.out.println("\n\n Digite a operacao: ");
            System.out.println("1 - Adicionar novo produto de alimento");
            System.out.println("2 - Adicionar novo produto de limpeza");
            System.out.println("3 - Mostrar todas as informacoes de um produto que esta em estoque");
            System.out.println("4 - Dar baixa em um produto");
            System.out.println("5 - Repor um produto ");
            System.out.println("6 - Mostrar todos os produtos em estoque");
            System.out.println("7 - Mostrar valor total de um produto ");
            System.out.println("0 - Sair");

            if (!scanner.hasNextLine()) {
                break;
            }

            switch(scanner.nextLine().trim()){
                case "0":
                    System.out.println("Stopping this application...");
                    sair = true;
                    break;
                case "1":
                    os.createProdutoAlimento();
                    break;
                case "2":
                    os.createProdutoLimpeza();
                    break;
                case "3":
                    os.findProduto();
                    break;
                case "4":
                    os.darBaixaProduto();
                    break;
                case "5":
                    os.reporProdutoById();
                    break;
                case "6":
                    os.mostrarTodosProdutos();
                    break;
                case "7":
                    os.valorTotalProduto();
                    break;
                default:
                    System.out.println("Opcao indisponivel!");
                    break;
            }

        }

    }

}
