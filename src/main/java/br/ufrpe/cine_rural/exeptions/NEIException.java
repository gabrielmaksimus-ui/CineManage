package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.loja.Produto;

//NonExistentItem
public class NEIException extends RuntimeException {
    private Produto produto;

    public NEIException(String message, Produto produto) {
        super("Item Inexistente no Estoque");
        this.produto = null;
    }

    public Produto getProduto() {
        return produto;
    }
}
