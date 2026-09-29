package usecases.service;

import java.util.Scanner;

import usecases.interfaces.IApplication;

public class QuestaoDoisApplicationService implements IApplication {

    @Override
    public void comecarAplicacao() {

        Scanner scanner = new Scanner(System.in);

        ImovelOperationsService ios = new ImovelOperationsService(scanner);

        boolean sair = false;

        while (!sair) {
            System.out.println("\n\n Digite a operacao: ");
            System.out.println("1 - Adicionar um imovel Novo");
            System.out.println("2 - Adicionar um imovel Usado");
            System.out.println("3 - Modificar Adicional");
            System.out.println("4 - Modificar Desconto");
            System.out.println("5 - Mostrar o valor de venda de um imovel");
            System.out.println("6 - Mostrar todas as informacoes de um imovel");
            System.out.println("7 - Mostrar todas as informacoes de todos os imoveis");
            System.out.println("0 - Sair");

            if (!scanner.hasNextLine()) {
                break;
            }

            switch (scanner.nextLine().trim()) {
                case "0":
                    System.out.println("Stopping this application...");
                    sair = true;
                    break;
                case "1":
                    ios.adicionarImovelNovo();
                    break;
                case "2":
                    ios.adicionarImovelUsado();
                    break;
                case "3":
                    ios.modificarAdicional();
                    break;
                case "4":
                    ios.modificarDesconto();
                    break;
                case "5":
                    ios.mostrarValorVenda();
                    break;
                case "6":
                    ios.mostrarImovel();
                    break;
                case "7":
                    ios.mostrarTodosImoveis();
                    break;
                default:
                    System.out.println("Opcao indisponivel!");
                    break;
            }

        }

    }

}
