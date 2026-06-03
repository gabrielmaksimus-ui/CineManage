package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Filme;

//AlreadyExistingFilm
public class AEFException extends RuntimeException {
    private Filme filme;

    public AEFException(String message,  Filme filme) {
        super(message);
        this.filme = filme;
    }

    public Filme getFilme() {
        return filme;
    }
}
