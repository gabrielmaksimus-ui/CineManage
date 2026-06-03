package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Filme;

//NonExistingFilm
public class NEFException extends RuntimeException {
  private Filme filme;
    public NEFException(String message,  Filme filme) {
        super("Filme Inexistente Em Catalogo");
        this.filme = null;
    }

    public Filme getFilme() {
      return filme;
    }
}
