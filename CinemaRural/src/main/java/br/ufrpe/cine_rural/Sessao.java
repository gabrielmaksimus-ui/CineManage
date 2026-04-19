import HerancaSala.Sala;

import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Sessao {
    private String idioma;
    private String status;
    private LocalDateTime horario;
    private Filme filme;
    private ArrayList<Ingresso> ingressos;
    private Sala sala;

    public Sessao(String idioma, String status, LocalDateTime horario, Filme filme,Sala sala){
        this.idioma = idioma;
        this.status = status;
        this.horario = horario;
        this.filme = filme;
        this.sala = sala;
        this.ingressos = new ArrayList();
    }

    //Adiciona os ingressos
    public void adicionarIngressos(Ingresso ingresso){
        ingressos.add(ingresso);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
