package main.java.br.ufrpe.cine_rural;

import main.java.br.ufrpe.cine_rural.controllers.ClienteController;
import main.java.br.ufrpe.cine_rural.dados.implemento.RepositorioClienteImpl;
import main.java.br.ufrpe.cine_rural.enums.*;

import main.java.br.ufrpe.cine_rural.model.beans.*;

import main.java.br.ufrpe.cine_rural.model.beans.tiposala.Imax;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) {

        RepositorioClienteImpl repositorioCliente = new RepositorioClienteImpl();
        ClienteController cController = new ClienteController(repositorioCliente);
        Imax salaImax = new Imax(1, 150);
        Filme filme = new Filme(
                "Interestelar",
                "Um grupo de astronautas viaja através de um buraco de minhoca em busca de um novo lar para a humanidade.",
                Genero.FICCAO,
                ClassificacaoIndicativa.DEZESSEIS,
                LocalTime.of(19, 30)
        );
        Sessao sessao = new Sessao(
                Idioma.DUBLADO,
                StatusSessao.EM_EXIBICAO,
                LocalDateTime.of(2026, 5, 22, 19, 30),
                filme,
                salaImax
        );
        Assento assento = new Assento("1");
        Ingresso ingresso = new Ingresso(
                sessao,
                assento,
                25.0,
                CategoriaMeiaEntrada.ESTUDANTE
        );
        cController.cadastrarCliente("Arthur", "121", 23, "neve.com");
        cController.atualizarCliente(repositorioCliente.buscar("121"), "Paulo", 21, "Paulo.com");
        System.out.println(cController.listarClientes());

    }
}