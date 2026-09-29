package usecases.service;

import domain.model.ProdutoModel;
import usecases.interfaces.IProduto;

public class ProdutoService implements IProduto {

    ProdutoModel produto;

    public ProdutoService(ProdutoModel produto) {
        this.produto = produto;
    }

    @Override
    public void darBaixa(int num){
        produto.darBaixa(num);
    }

    @Override
    public void repor(int num){
        produto.repor(num);
    }

    @Override
    public double valorTotal(){
        return produto.valorTotal();
    }
}
