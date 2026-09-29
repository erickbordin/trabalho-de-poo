package usecases.service;

import domain.model.ProdutoModel;
import usecases.interfaces.IProduto;
import usecases.verifiers.NumberVerifiers;

public class ProdutoService implements IProduto {

    ProdutoModel produto;
    NumberVerifiers nv = new NumberVerifiers();

    public ProdutoService(ProdutoModel produto) {
        this.produto = produto;
    }

    @Override 
    public void darBaixa(int num){
        if(nv.itsPossibleDarBaixa(num)){
            produto.setQuantidade(produto.getQuantidade() - num);
        }
    }

    @Override 
    public void repor(int num){
        produto.setQuantidade(produto.getQuantidade() + num);
    }

    @Override 
    public double valorTotal(){
        return produto.getQuantidade() * produto.getValor();
    }
}