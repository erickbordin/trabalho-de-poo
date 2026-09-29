package domain.model;

import usecases.interfaces.IProduto;

public class ProdutoModel implements IProduto {

    private String id;
    private String descricao;
    private int quantidade;
    private double valor;

    public ProdutoModel(String id, String descricao, int quantidade, double valor){
        this.id = id;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ProdutoModel(){}

    @Override
    public void darBaixa(int numero) {
        if (quantidade < numero) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque. Disponivel: " + quantidade);
        }
        quantidade -= numero;
    }

    @Override
    public void repor(int numero) {
        quantidade += numero;
    }

    @Override
    public double valorTotal() {
        return quantidade * valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "id: " + getId() +
            "\ndescricao: " + getDescricao() +
            "\nquantidade: " + getQuantidade() +
            "\nvalor: " + getValor();
    }

}
