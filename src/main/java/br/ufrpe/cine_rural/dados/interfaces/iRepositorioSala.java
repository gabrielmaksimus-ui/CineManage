package main.java.br.ufrpe.cine_rural.dados.interfaces;

import java.util.ArrayList;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Sala;


public interface iRepositorioSala {
    void cadastrar(Sala sala);
    Sala buscar(int id);
    void remover(int id);
    ArrayList<Sala> listar();
}
