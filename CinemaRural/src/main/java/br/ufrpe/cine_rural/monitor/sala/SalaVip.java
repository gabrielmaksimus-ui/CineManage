package br.ufrpe.cine_rural.monitor.sala;

public class SalaVip extends Sala {
    private boolean servico;

    public SalaVip(int id, int capacidade, double preco, boolean servico) {
        super(id, capacidade, preco);
        this.servico = servico;
    }

    public boolean getServico() {
        return servico;
    }
}