package usecases.service;

import java.util.Scanner;

import domain.model.ProdutoAlimentoModel;
import domain.model.ProdutoLimpezaModel;
import domain.model.ProdutoModel;
import usecases.verifiers.NumberVerifiers;

public class OperationsService {

    ProdutoModel[] estoque = new ProdutoModel[10];
    NumberVerifiers nv = new NumberVerifiers();

    Scanner scanner = new Scanner(System.in);

    int contadorDeProdutosAdicionados = 0;

    public ProdutoAlimentoModel createProdutoAlimento() {

        if (contadorDeProdutosAdicionados < 9) {

            ProdutoAlimentoModel produto = new ProdutoAlimentoModel();

            System.out.println("Vamos criar seu produto do tipo alimento!");

            String id;
            do {
                System.out.println("Digite o ID: ");
                id = scanner.nextLine();
                if (!nv.isNumero(id)) {
                    System.out.println("ID invalido! O ID deve ser um numero.");
                }
            } while (!nv.isNumero(id));
            produto.setId(id);

            System.out.println("Digite a DESCRICAO: ");
            produto.setDescricao(scanner.nextLine());
            System.out.println("Digite a QUANTIDADE: ");
            produto.setQuantidade(scanner.nextInt());
            System.out.println("Digite o VALOR: ");
            produto.setValor(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Digite o TIPO: ");
            produto.setTipo(scanner.nextLine());

            estoque[contadorDeProdutosAdicionados] = produto;
            contadorDeProdutosAdicionados++;

            System.out.println("PRODUTO DE ALIMENTO CRIADO COM SUCESSO!");
            return produto;
        }

        throw new RuntimeException("O estoque ja esta cheio!");
    }

    public ProdutoLimpezaModel createProdutoLimpeza() {

        if (contadorDeProdutosAdicionados < 9) {

            ProdutoLimpezaModel produto = new ProdutoLimpezaModel();

            System.out.println("Vamos criar seu produto do tipo limpeza!");

            String id;
            do {
                System.out.println("Digite o ID: ");
                id = scanner.nextLine();
                if (!nv.isNumero(id)) {
                    System.out.println("ID invalido! O ID deve ser um numero.");
                }
            } while (!nv.isNumero(id));
            produto.setId(id);

            System.out.println("Digite a DESCRICAO: ");
            produto.setDescricao(scanner.nextLine());
            System.out.println("Digite a QUANTIDADE: ");
            produto.setQuantidade(scanner.nextInt());
            System.out.println("Digite o VALOR: ");
            produto.setValor(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Digite a MARCA: ");
            produto.setMarca(scanner.nextLine());

            estoque[contadorDeProdutosAdicionados] = produto;
            contadorDeProdutosAdicionados++;

            System.out.println("PRODUTO DE LIMPEZA CRIADO COM SUCESSO!");
            return produto;
        }

        throw new RuntimeException("O estoque ja esta cheio!");
    }

    public void findProduto() {

        System.out.println("Qual ID do produto que voce esta buscando?");
        String id = scanner.nextLine();

        if (!nv.isNumero(id)) {
            System.out.println("ID invalido! O ID deve ser um numero.");
            return;
        }

        boolean produtoEncontrado = false;

        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            if (estoque[i].getId().equals(id)) {
                System.out.println("Aqui vai os atributos do produto: " + estoque[i].toString());
                produtoEncontrado = true;
            }
        }

        if (produtoEncontrado == false) {
            System.out.println("Nao achamos um produto com este ID ;( ");
        }

    }

    public void darBaixaProduto() {
        System.out.println("Qual ID do produto que deseja dar baixa?");
        String id = scanner.nextLine();

        if (!nv.isNumero(id)) {
            System.out.println("ID invalido! O ID deve ser um numero.");
            return;
        }

        boolean produtoEncontrado = false;

        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            if (estoque[i].getId().equals(id)) {
                produtoEncontrado = true;

                System.out.println("Quantidade atual: " + estoque[i].getQuantidade());
                System.out.println("Digite a quantidade vendida: ");
                int quantidade = scanner.nextInt();
                scanner.nextLine(); // ← adicionado

                ProdutoService ps = new ProdutoService(estoque[i]);
                ps.darBaixa(quantidade);

                System.out.println("Baixa realizada com sucesso! Nova quantidade: " + estoque[i].getQuantidade());
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Nao achamos um produto com este ID ;(");
        }
    }

    public void reporProdutoById() {
        System.out.println("Qual ID do produto que deseja repor?");
        String id = scanner.nextLine();

        if (!nv.isNumero(id)) {
            System.out.println("ID invalido! O ID deve ser um numero.");
            return;
        }

        boolean produtoEncontrado = false;

        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            if (estoque[i].getId().equals(id)) {
                produtoEncontrado = true;

                System.out.println("Quantidade atual: " + estoque[i].getQuantidade());
                System.out.println("Digite a quantidade a repor: ");
                int quantidadeReposta = scanner.nextInt();
                scanner.nextLine();

                ProdutoService ps = new ProdutoService(estoque[i]); // ← usando o service
                ps.repor(quantidadeReposta); // ← ao invés de fazer a conta na mão

                System.out.println("Produto reposto com sucesso! Nova quantidade: " + estoque[i].getQuantidade());
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Nao achamos um produto com este ID ;(");
        }
    }

    public void mostrarTodosProdutos() {
        if (contadorDeProdutosAdicionados == 0) {
            System.out.println("Nao tem produtos no estoque");
            return;
        }

        System.out.println("Produtos no estoque -> ");
        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            System.out.println("Produto " + (i + 1) + ": " + estoque[i].toString());
        }
    }

    public void valorTotalProduto() {
        System.out.println("Qual ID do produto que deseja ver o valor total?");
        String id = scanner.nextLine();

        if (!nv.isNumero(id)) {
            System.out.println("ID invalido! O ID deve ser um numero.");
            return;
        }

        boolean produtoEncontrado = false;

        for (int i = 0; i < contadorDeProdutosAdicionados; i++) {
            if (estoque[i].getId().equals(id)) {
                produtoEncontrado = true;

                double valorTotal = estoque[i].getQuantidade() * estoque[i].getValor();

                System.out.println("Produto: " + estoque[i].getDescricao());
                System.out.println("Quantidade: " + estoque[i].getQuantidade());
                System.out.println("Valor unitario: " + estoque[i].getValor());
                System.out.println("Valor total: " + valorTotal);
                break;
            }
        }

        if (!produtoEncontrado) {
            System.out.println("Nao achamos um produto com este ID ;(");
        }
    }

}
