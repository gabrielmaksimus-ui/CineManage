package main.java.br.ufrpe.cine_rural.controllers;

import main.java.br.ufrpe.cine_rural.dados.implemento.RepositorioSalaImpl;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.*;

import java.util.ArrayList;
import java.util.List;


 // Controller responsável pelo cadastro e gestão de salas
public class SalaController {

    private final RepositorioSalaImpl repositorioSala;

    public SalaController(RepositorioSalaImpl repositorioSala) {
        this.repositorioSala = repositorioSala;
    }

    /** Cadastra uma sala Comum (multiplicador de preço 1.0×). */
    public Comum cadastrarSalaComum(int id, int capacidade) {
        validarIdUnico(id);
        validarCapacidade(capacidade);
        Comum sala = new Comum(id, capacidade);  
        repositorioSala.cadastrar(sala);
        System.out.println("[SalaController] Sala Comum #" + id + " cadastrada (cap.: " + capacidade + ").");
        return sala;
    }

    /** Cadastra uma sala VIP (multiplicador de preço 3.0×). */
    public Vip cadastrarSalaVip(int id, int capacidade) {
        validarIdUnico(id);
        validarCapacidade(capacidade);
        Vip sala = new Vip(id, capacidade);        
        repositorioSala.cadastrar(sala);
        System.out.println("[SalaController] Sala VIP #" + id + " cadastrada (cap.: " + capacidade + ").");
        return sala;
    }

    /** Cadastra uma sala IMAX (multiplicador de preço 2.0×). */
    public Imax cadastrarSalaImax(int id, int capacidade) {
        validarIdUnico(id);
        validarCapacidade(capacidade);
        Imax sala = new Imax(id, capacidade);      
        repositorioSala.cadastrar(sala);
        System.out.println("[SalaController] Sala IMAX #" + id + " cadastrada (cap.: " + capacidade + ").");
        return sala;
    }

    public void atualizarCapacidade(Sala sala, int novaCapacidade) {
        validarCapacidade(novaCapacidade);
        sala.setCapacidade(novaCapacidade);
        System.out.println("[SalaController] Capacidade da sala #" + sala.getId() + " → " + novaCapacidade);
    }


    public Sala buscarPorId(int id) {
        return repositorioSala.buscar(id);
    }

    // Filtra salas por subtipo (Comum.class, Vip.class ou Imax.class)
    public List<Sala> listarPorTipo(Class<? extends Sala> tipo) {
        List<Sala> resultado = new ArrayList<>();
        for (Sala sala : repositorioSala.listar()) {
            if (tipo.isInstance(sala)) {
                resultado.add(sala);
            }
        }
        return resultado;
    }

    public void removerSala(int id) {
        repositorioSala.remover(id);
        System.out.println("[SalaController] Sala #" + id + " removida.");
    }

    private void validarIdUnico(int id) {
        if (buscarPorId(id) != null)
            throw new IllegalArgumentException("Já existe uma sala com ID " + id + ".");
    }

    private void validarCapacidade(int capacidade) {
        if (capacidade <= 0)
            throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
    }
}
