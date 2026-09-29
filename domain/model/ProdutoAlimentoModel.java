package domain.model;

public class ProdutoAlimentoModel extends ProdutoModel {

    private String tipo;

    public ProdutoAlimentoModel(String id, String descricao, int quantidade, double valor, String tipo) {
        super(id, descricao, quantidade, valor);
        this.tipo = tipo;
    }

    public ProdutoAlimentoModel() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ntipo: " + tipo;
    }

}
