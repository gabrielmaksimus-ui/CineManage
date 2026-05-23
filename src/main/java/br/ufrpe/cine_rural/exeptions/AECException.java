package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Cliente;

//AlreadyExistingClient
public class AECException extends RuntimeException {
    private Cliente cliente;

    public AECException(String message,  Cliente cliente) {
        super(message);
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
