package br.ufrpe.cine_rural.monitor.sala;

public abstract class Sala {
    private int id;
    private int capacidade;
    private double preco;

    public Sala(int id, int capacidade, double preco) {
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