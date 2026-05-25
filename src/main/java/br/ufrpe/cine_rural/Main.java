package main.java.br.ufrpe.cine_rural;

import main.java.br.ufrpe.cine_rural.controllers.*;
import main.java.br.ufrpe.cine_rural.dados.implemento.*;
import main.java.br.ufrpe.cine_rural.enums.*;
import main.java.br.ufrpe.cine_rural.model.beans.*;
import main.java.br.ufrpe.cine_rural.model.beans.tiposala.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

import main.java.br.ufrpe.cine_rural.model.beans.*;

import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Imax;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {
        RepositorioSessaoImpl repositorio = new RepositorioSessaoImpl();
        SessaoController controller = new SessaoController(repositorio);

        RepositorioClienteImpl repositorioCliente = new RepositorioClienteImpl();
        ClienteController cController = new ClienteController(repositorioCliente);

        Comum sala = new Comum(1, 20);
        Imax salaImax = new Imax(1, 150);

        Filme filme = new Filme(
                "Interestelar",
                "Um grupo de astronautas viaja através de um buraco de minhoca em busca de um novo lar para a humanidade.",
                Genero.FICCAO,
                ClassificacaoIndicativa.DEZESSEIS,
                120
        );

        LocalDateTime horario = LocalDateTime.of(2025, 6, 10, 19, 0);

        System.out.println("=== TESTE 1: Criar sessão ===");
        Sessao sessao = controller.criarSessao(filme, sala, horario, Idioma.DUBLADO);
        System.out.println("Sessão criada com status: " + sessao.getStatus());

        Assento assento = new Assento("1");
        Ingresso ingresso = new Ingresso(sessao, assento, 25.0, CategoriaMeiaEntrada.ESTUDANTE);

        cController.cadastrarCliente("Arthur", "121", 23, "neve.com");
        cController.atualizarCliente(repositorioCliente.buscar("121"), "Paulo", 21, "Paulo.com");
        System.out.println(cController.listarClientes());

        System.out.println("\n=== TESTE 2: Sobreposição de horário ===");
        LocalDateTime horarioConflitante = LocalDateTime.of(2025, 6, 10, 20, 0);
        try {
            controller.criarSessao(filme, sala, horarioConflitante, Idioma.DUBLADO);
        } catch (IllegalStateException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        System.out.println("\n=== TESTE 3: Encerrar sessão ===");
        controller.encerrarSessao(sessao);
        System.out.println("Status final: " + sessao.getStatus());

    }
}