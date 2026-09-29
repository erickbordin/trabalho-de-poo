package domain.model;

public class ProdutoLimpezaModel extends ProdutoModel {

    private String marca;

    public ProdutoLimpezaModel(String id, String descricao, int quantidade, double valor, String marca) {
        super(id, descricao, quantidade, valor);
        this.marca = marca;
    }

    public ProdutoLimpezaModel() {
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nmarca: " + marca;
    }

}
