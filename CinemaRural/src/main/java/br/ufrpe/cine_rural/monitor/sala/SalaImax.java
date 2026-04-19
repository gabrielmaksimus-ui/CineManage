package br.ufrpe.cine_rural.monitor.sala;

public class SalaImax extends Sala {
    private boolean somEspecial;

    public SalaImax(int id, int capacidade, double preco, boolean somEspecial) {
        super(id, capacidade, preco);
        this.somEspecial = somEspecial;
    }

    public boolean getSomEspecial() {
        return somEspecial;
    }

    public void setSomEspecial(boolean somEspecial) {
        this.somEspecial = somEspecial;
    }
}
