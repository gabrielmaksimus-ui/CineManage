package main.java.br.ufrpe.cine_rural.exeptions;

import main.java.br.ufrpe.cine_rural.model.beans.Filme;
import main.java.br.ufrpe.cine_rural.model.beans.Sessao;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Sala;

import java.time.LocalDateTime;

public class NESException extends RuntimeException {
    private Sala sala;
    private Sessao sessao;
    private Filme filme;
    private LocalDateTime horario;

    public NESException(String message, Sala sala, Sessao sessao, Filme filme, LocalDateTime horario) {
        super("Na sala não há uma sessão deste filme nesse horário");
        this.sessao = sessao;
        this.sala = sala;
        this.filme = filme;
        this.horario = horario;
    }

    public Sala getSala() {
        return sala;
    }
    public Sessao getSessao() {
        return sessao;
    }
    public Filme getFilme() {
        return filme;
    }
    public LocalDateTime getHorario() {
        return horario;
    }
}
