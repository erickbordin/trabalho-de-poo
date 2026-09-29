package domain.model;

public class UsadoModel extends ImovelModel {

    private static final double DESCONTO_PADRAO = 5;

    private double desconto;

    public UsadoModel(int id, String endereco, double valor) {
        super(id, endereco, valor);
        this.desconto = DESCONTO_PADRAO;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    @Override
    public double getValorVenda() {
        return valor - (valor * desconto / 100);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\ndesconto: " + desconto + "%";
    }

}
