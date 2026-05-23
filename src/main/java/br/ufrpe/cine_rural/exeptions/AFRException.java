package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Assento;
import main.java.br.ufrpe.cine_rural.model.beans.loja.Produto;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Sala;

//AlreadyFullRoom
public class AFRException extends RuntimeException {
  private Sala sala;

    public AFRException(String message, Sala sala) {
        super("Esta sala ja esta cheia");
        this.sala = sala;
    }

  public Sala getSala() {
    return sala;
  }
}
