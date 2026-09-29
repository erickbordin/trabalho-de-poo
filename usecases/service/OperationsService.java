package usecases.service;

import java.util.Scanner;

import domain.model.ProdutoAlimentoModel;
import domain.model.ProdutoLimpezaModel;
import domain.model.ProdutoModel;
import usecases.verifiers.NumberVerifiers;

public class OperationsService {

    ProdutoModel[] estoque = new ProdutoModel[10];
    NumberVerifiers nv = new NumberVerifiers();

    Scanner scanner;

    int contadorDeProdutosAdicionados = 0;

    public OperationsService(Scanner scanner) {
        this.scanner = scanner;
    }

    public void createProdutoAlimento() {

        if (estoqueCheio()) {
            System.out.println("O estoque ja esta cheio!");
            return;
        }

        System.out.println("Vamos criar seu produto do tipo alimento!");

        String id = lerId();
        System.out.println("Digite a DESCRICAO: ");
        String descricao = scanner.nextLine();
        int quantidade = lerInteiro("Digite a QUANTIDADE: ");
        double valor = lerDecimal("Digite o VALOR: ");
        System.out.println("Digite o TIPO: ");
        String tipo = scanner.nextLine();

        estoque[contadorDeProdutosAdicionados] = new ProdutoAlimentoModel(id, descricao, quantidade, valor, tipo);
        contadorDeProdutosAdicionados++;

        System.out.println("PRODUTO DE ALIMENTO CRIADO COM SUCESSO!");
    }

    public void createProdutoLimpeza() {

        if (estoqueCheio()) {
            System.out.println("O estoque ja esta cheio!");
            return;
        }

        System.out.println("Vamos criar seu produto do tipo limpeza!");

        String id = lerId();
        System.out.println("Digite a DESCRICAO: ");
        String descricao = scanner.nextLine();
        int quantidade = lerInteiro("Digite a QUANTIDADE: ");
        double valor = lerDecimal("Digite o VALOR: ");
        System.out.println("Digite a MARCA: ");
        String marca = scanner.nextLine();

        estoque[contadorDeProdutosAdicionados] = new ProdutoLimpezaModel(id, descricao, quantidade, valor, marca);
        contadorDeProdutosAdicionados++;

        System.out.println("PRODUTO DE LIMPEZA CRIADO COM SUCESSO!");
    }

    public void findProduto() {

        ProdutoModel produto = buscarProduto("Qual ID do produto que voce esta buscando?");

        if (produto != null) {
            System.out.println("Aqui vai os atributos do produto:\n" + produto);
        }

    }

    public void darBaixaProduto() {

        ProdutoModel produto = buscarProduto("Qual ID do produto que deseja dar baixa?");

        if (produto == null) {
            return;
        }

        System.out.println("Quantidade atual: " + produto.getQuantidade());
        int quantidade = lerInteiro("Digite a quantidade vendida: ");

        try {
            new ProdutoService(produto).darBaixa(quantidade);
            System.out.println("Baixa realizada com sucesso! Nova quantidade: " + produto.getQuantidade());
        } catch (IllegalArgumentException e) {
            System.out.println("Nao foi possivel dar baixa. " + e.getMessage());
        }
    }

    public void reporProdutoById() {

        ProdutoModel produto = buscarProduto("Qual ID do produto que deseja repor?");

        if (produto == null) {
            return;
        }

        System.out.println("Quantidade atual: " + produto.getQuantidade());
        int quantidadeReposta = lerInteiro("Digite a quantidade a repor: ");

        new ProdutoService(produto).repor(quantidadeReposta);

        System.out.println("Produto reposto com sucesso! Nova quantidade: " + produto.getQuantidade());
    }

    public void mostrarTodosProdutos() {
        if (contadorDeProdutosAdicionados == 0) {
            System.out.println("Nao tem produtos no estoque");
            return;
        }

        System.out.println("Produtos no estoque -> ");
        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            System.out.println("\nProduto " + (i + 1) + ":\n" + estoque[i]);
        }
    }

    public void valorTotalProduto() {

        ProdutoModel produto = buscarProduto("Qual ID do produto que deseja ver o valor total?");

        if (produto == null) {
            return;
        }

        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Quantidade: " + produto.getQuantidade());
        System.out.println("Valor unitario: " + produto.getValor());
        System.out.println("Valor total: " + new ProdutoService(produto).valorTotal());
    }

    private boolean estoqueCheio() {
        return contadorDeProdutosAdicionados >= estoque.length;
    }

    private ProdutoModel buscarProduto(String pergunta) {
        System.out.println(pergunta);
        String id = scanner.nextLine().trim();

        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            if (estoque[i].getId().equals(id)) {
                return estoque[i];
            }
        }

        System.out.println("Nao achamos um produto com este ID ;(");
        return null;
    }

    private String lerId() {
        String id;
        do {
            System.out.println("Digite o ID: ");
            id = scanner.nextLine().trim();
            if (!nv.isNumero(id)) {
                System.out.println("ID invalido! O ID deve ser um numero.");
            }
        } while (!nv.isNumero(id));
        return id;
    }

    private int lerInteiro(String pergunta) {
        String valor;
        do {
            System.out.println(pergunta);
            valor = scanner.nextLine().trim();
            if (!nv.isNumero(valor) || Integer.parseInt(valor) < 0) {
                System.out.println("Valor invalido! Digite um numero inteiro nao negativo.");
            }
        } while (!nv.isNumero(valor) || Integer.parseInt(valor) < 0);
        return Integer.parseInt(valor);
    }

    private double lerDecimal(String pergunta) {
        String valor;
        do {
            System.out.println(pergunta);
            valor = scanner.nextLine().trim();
            if (!nv.isDecimal(valor)) {
                System.out.println("Valor invalido! Digite um numero (ex: 10.50).");
            }
        } while (!nv.isDecimal(valor));
        return Double.parseDouble(valor.replace(',', '.'));
    }

}
