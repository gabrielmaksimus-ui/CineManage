package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.loja.Produto;

//AlreadyExistingItem
public class ALIException extends RuntimeException {
    private Produto produto;

    public ALIException(String message, Produto produto) {
        super(message);
        this.produto = produto;
    }

    public Produto getProduto() {
        return produto;
    }
}
