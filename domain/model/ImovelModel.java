package domain.model;

import usecases.interfaces.IImovel;

public abstract class ImovelModel implements IImovel {

    protected int id;
    protected String endereco;
    protected double valor;

    public ImovelModel(int id, String endereco, double valor) {
        this.id = id;
        this.endereco = endereco;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "id: " + id +
                "\nendereco: " + endereco +
                "\nvalor: " + valor;
    }

}
