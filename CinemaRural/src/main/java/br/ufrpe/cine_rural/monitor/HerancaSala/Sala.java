package br.ufrpe.cine_rural.monitor.HerancaSala;

public abstract class Sala {
    protected final int id;
    protected final int capacidade;
    protected final double preco;

    public Sala(int id, int capacidade, double preco){
        this.id = id;
        this.capacidade = capacidade;
        this.preco = preco;

    }

    public int getId() {
        return id;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public double getPreco() {
        return preco;
    }
}
