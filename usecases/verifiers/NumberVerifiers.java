package usecases.verifiers;

import domain.model.ProdutoModel;

public class NumberVerifiers {

    ProdutoModel produto = new ProdutoModel();

    public boolean itsPossibleDarBaixa(int num) {
        if (num > produto.getQuantidade()) {
            throw new Error("isnt possible dar baixa");
        }
        return true;
    }

    public boolean isNumero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
