package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Assento;

public class ATSException extends RuntimeException {
    private Assento assento;

    public ATSException(String message,  Assento assento) {
        super("Assento já ocupado");
        this.assento = assento;
    }

    public Assento getAssento() {
        return assento;
    }
}
