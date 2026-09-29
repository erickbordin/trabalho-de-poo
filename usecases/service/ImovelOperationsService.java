package usecases.service;

import java.util.Scanner;

import domain.model.ImovelModel;
import domain.model.NovoModel;
import domain.model.UsadoModel;
import usecases.verifiers.NumberVerifiers;

public class ImovelOperationsService {

    private static final int CAPACIDADE_LISTA = 10;

    private final ImovelModel[] lista = new ImovelModel[CAPACIDADE_LISTA];
    private final NumberVerifiers numberVerifiers = new NumberVerifiers();
    private final Scanner scanner;

    private int quantidadeImoveis = 0;

    public ImovelOperationsService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void adicionarImovelNovo() {

        if (listaCheia()) {
            System.out.println("A lista de imoveis ja esta cheia!");
            return;
        }

        System.out.println("Vamos cadastrar um imovel NOVO!");

        int id = lerIdDisponivel();
        System.out.println("Digite o ENDERECO: ");
        String endereco = scanner.nextLine();
        double valor = lerDecimal("Digite o VALOR: ");

        lista[quantidadeImoveis] = new NovoModel(id, endereco, valor);
        quantidadeImoveis++;

        System.out.println("IMOVEL NOVO CADASTRADO COM SUCESSO!");
    }

    public void adicionarImovelUsado() {

        if (listaCheia()) {
            System.out.println("A lista de imoveis ja esta cheia!");
            return;
        }

        System.out.println("Vamos cadastrar um imovel USADO!");

        int id = lerIdDisponivel();
        System.out.println("Digite o ENDERECO: ");
        String endereco = scanner.nextLine();
        double valor = lerDecimal("Digite o VALOR: ");

        lista[quantidadeImoveis] = new UsadoModel(id, endereco, valor);
        quantidadeImoveis++;

        System.out.println("IMOVEL USADO CADASTRADO COM SUCESSO!");
    }

    public void modificarAdicional() {

        ImovelModel imovel = buscarImovel("Qual ID do imovel NOVO que deseja modificar o adicional?");

        if (imovel == null) {
            return;
        }

        if (!(imovel instanceof NovoModel)) {
            System.out.println("O imovel com este ID nao e um imovel NOVO.");
            return;
        }

        NovoModel imovelNovo = (NovoModel) imovel;
        System.out.println("Adicional atual: " + imovelNovo.getAdicional() + "%");
        imovelNovo.setAdicional(lerDecimal("Digite o novo ADICIONAL (%): "));

        System.out.println("Adicional modificado com sucesso! Novo adicional: " + imovelNovo.getAdicional() + "%");
    }

    public void modificarDesconto() {

        ImovelModel imovel = buscarImovel("Qual ID do imovel USADO que deseja modificar o desconto?");

        if (imovel == null) {
            return;
        }

        if (!(imovel instanceof UsadoModel)) {
            System.out.println("O imovel com este ID nao e um imovel USADO.");
            return;
        }

        UsadoModel imovelUsado = (UsadoModel) imovel;
        System.out.println("Desconto atual: " + imovelUsado.getDesconto() + "%");
        imovelUsado.setDesconto(lerDecimal("Digite o novo DESCONTO (%): "));

        System.out.println("Desconto modificado com sucesso! Novo desconto: " + imovelUsado.getDesconto() + "%");
    }

    public void mostrarValorVenda() {

        ImovelModel imovel = buscarImovel("Qual ID do imovel que deseja ver o valor de venda?");

        if (imovel != null) {
            System.out.println("Valor de venda: " + imovel.getValorVenda());
        }

    }

    public void mostrarImovel() {

        ImovelModel imovel = buscarImovel("Qual ID do imovel que voce esta buscando?");

        if (imovel != null) {
            System.out.println("Aqui vai os atributos do imovel:\n" + imovel);
        }

    }

    public void mostrarTodosImoveis() {
        if (quantidadeImoveis == 0) {
            System.out.println("Nao tem imoveis na lista");
            return;
        }

        System.out.println("Imoveis na lista -> ");
        for (int i = 0; i < quantidadeImoveis; i++) {
            System.out.println("\nImovel " + (i + 1) + ":\n" + lista[i]);
        }
    }

    private boolean listaCheia() {
        return quantidadeImoveis >= lista.length;
    }

    private ImovelModel buscarImovel(String pergunta) {
        int id = lerInteiro(pergunta);
        ImovelModel imovel = encontrarPorId(id);

        if (imovel == null) {
            System.out.println("Nao achamos um imovel com este ID ;(");
        }

        return imovel;
    }

    private ImovelModel encontrarPorId(int id) {
        for (int i = 0; i < quantidadeImoveis; i++) {
            if (lista[i].getId() == id) {
                return lista[i];
            }
        }
        return null;
    }

    private int lerIdDisponivel() {
        int id = lerInteiro("Digite o ID: ");
        while (encontrarPorId(id) != null) {
            System.out.println("Ja existe um imovel com este ID!");
            id = lerInteiro("Digite o ID: ");
        }
        return id;
    }

    private int lerInteiro(String pergunta) {
        String entrada;
        do {
            System.out.println(pergunta);
            entrada = scanner.nextLine().trim();
            if (!numberVerifiers.isNumero(entrada) || Integer.parseInt(entrada) < 0) {
                System.out.println("Valor invalido! Digite um numero inteiro nao negativo.");
            }
        } while (!numberVerifiers.isNumero(entrada) || Integer.parseInt(entrada) < 0);
        return Integer.parseInt(entrada);
    }

    private double lerDecimal(String pergunta) {
        String entrada;
        do {
            System.out.println(pergunta);
            entrada = scanner.nextLine().trim();
            if (!numberVerifiers.isDecimal(entrada) || converterDecimal(entrada) < 0) {
                System.out.println("Valor invalido! Digite um numero nao negativo (ex: 150000.50).");
            }
        } while (!numberVerifiers.isDecimal(entrada) || converterDecimal(entrada) < 0);
        return converterDecimal(entrada);
    }

    private double converterDecimal(String entrada) {
        return Double.parseDouble(entrada.replace(',', '.'));
    }

}
