package domain.model;

public class NovoModel extends ImovelModel {

    private static final double ADICIONAL_PADRAO = 15;

    private double adicional;

    public NovoModel(int id, String endereco, double valor) {
        super(id, endereco, valor);
        this.adicional = ADICIONAL_PADRAO;
    }

    public double getAdicional() {
        return adicional;
    }

    public void setAdicional(double adicional) {
        this.adicional = adicional;
    }

    @Override
    public double getValorVenda() {
        return valor + (valor * adicional / 100);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nadicional: " + adicional + "%";
    }

}
