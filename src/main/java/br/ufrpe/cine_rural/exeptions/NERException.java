package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Filme;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Sala;

//NonExistingRoom
public class NERException extends RuntimeException {
    private Sala sala;

    public NERException(String message, Sala sala) {
        super("Sala inexistente");
        this.sala = null;
    }

    public Sala getSala() {
        return sala;
    }
}
