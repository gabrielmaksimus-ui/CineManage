package main.java.br.ufrpe.cine_rural.controllers;

import main.java.br.ufrpe.cine_rural.dados.implemento.RepositorioSessaoImpl;
import main.java.br.ufrpe.cine_rural.enums.Idioma;
import main.java.br.ufrpe.cine_rural.model.beans.Filme;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Sala;
import main.java.br.ufrpe.cine_rural.model.beans.Ingresso;
import main.java.br.ufrpe.cine_rural.model.beans.Sessao;
import main.java.br.ufrpe.cine_rural.enums.StatusSessao;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class SessaoController {

    private final RepositorioSessaoImpl repositorio;

    public SessaoController(RepositorioSessaoImpl repositorio) {
        this.repositorio = repositorio;
    }


    public Sessao criarSessao(Filme filme, Sala sala,
                               LocalDateTime horario, Idioma idioma) {

        validarParametros(filme, sala, horario, idioma);
        verificarSobreposicao(sala, horario, filme); // REQ20

        Sessao sessao = new Sessao(idioma, StatusSessao.ABERTA, horario, filme, sala);

        repositorio.cadastrar(sessao);
        System.out.println("[SessaoController] Sessão criada: "
                + filme.getTitulo() + " | Sala #" + sala.getId() + " | " + horario);
        return sessao;
    }

    public void iniciarExibicao(Sessao sessao) {
        if (!StatusSessao.ABERTA.equals(sessao.getStatus()))
            throw new IllegalStateException("Somente sessões ABERTAS podem iniciar exibição.");
        sessao.setStatus(StatusSessao.EM_EXIBICAO);
        repositorio.atualizar(sessao);
        System.out.println("[SessaoController] Sessão em exibição.");
    }

    public void encerrarSessao(Sessao sessao) {
        if (StatusSessao.ENCERRADA.equals(sessao.getStatus()))
            throw new IllegalStateException("Sessão já está encerrada.");
        sessao.setStatus(StatusSessao.ENCERRADA);
        repositorio.atualizar(sessao);
        System.out.println("[SessaoController] Sessão encerrada.");
    }

    public void adicionarIngresso(Sessao sessao, Ingresso ingresso) {
        if (StatusSessao.ENCERRADA.equals(sessao.getStatus()))
            throw new IllegalStateException("Sessão encerrada; não é possível adicionar ingressos.");

        boolean ocupado = false;
        if (sessao.getIngressos() != null) {
            for (Ingresso i : sessao.getIngressos()) {
                if (i.getAssento().getCodigo().equalsIgnoreCase(ingresso.getAssento().getCodigo())) {
                    ocupado = true;
                }
            }
        }
        if (ocupado)
            throw new IllegalStateException(
                    "Assento '" + ingresso.getAssento() + "' já está ocupado. (REQ21)");
        sessao.adicionarIngressos(ingresso);
        repositorio.atualizar(sessao);
    }


    public void removerIngresso(Sessao sessao, Ingresso ingresso) {
        if (sessao.getIngressos() != null)
            sessao.getIngressos().remove(ingresso);
        repositorio.atualizar(sessao);
    }


    public void alterarFilmeDaSessao(Sessao sessao, Filme novoFilme) {
        if (sessao.getIngressos() != null && !sessao.getIngressos().isEmpty())
            throw new IllegalStateException(
                    "Não é possível alterar o filme: sessão já possui ingressos vendidos. (REQ24)");
        sessao.setFilme(novoFilme);
        repositorio.atualizar(sessao);
        System.out.println("[SessaoController] Filme alterado para '" + novoFilme.getTitulo() + "'.");
    }


    public ArrayList<Sessao> listarSessoes() {
        return repositorio.listar();
    }

    public Sessao buscarPorHorario(LocalDateTime horario) {
        return repositorio.buscar(horario);
    }

    public void removerSessao(LocalDateTime horario) {
        repositorio.remover(horario);
    }

    private void verificarSobreposicao(Sala sala, LocalDateTime novoInicio, Filme filme) {
        long duracaoNova = filme.getDuracao();
        LocalDateTime novoFim = novoInicio.plusMinutes(duracaoNova);

        for (Sessao s : repositorio.listar()) {
            if (s.getSala().getId() != sala.getId()) continue;
            if (StatusSessao.ENCERRADA.equals(s.getStatus())) continue;

            long duracaoExistente = s.getFilme().getDuracao();
            LocalDateTime existFim = s.getHorario().plusMinutes(duracaoExistente);

            boolean sobrepoe = novoInicio.isBefore(existFim)
                            && novoFim.isAfter(s.getHorario());
            if (sobrepoe)
                throw new IllegalStateException(
                        "Conflito de horário: sala #" + sala.getId()
                        + " ocupada de " + s.getHorario() + " até " + existFim);
        }
    }

    private void validarParametros(Filme filme, Sala sala,
                                   LocalDateTime horario, Idioma idioma) {
        if (filme   == null) throw new IllegalArgumentException("Filme é obrigatório.");
        if (sala    == null) throw new IllegalArgumentException("Sala é obrigatória.");
        if (horario == null) throw new IllegalArgumentException("Horário é obrigatório.");
        if (idioma  == null) throw new IllegalArgumentException("Idioma é obrigatório.");
    }
}
