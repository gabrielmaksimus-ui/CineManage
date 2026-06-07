
package br.ufrpe.cine_rural.gui;

/*
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource(
                        "/br/ufrpe/cine_rural/gui/Produto.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Teste Produto");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/














/*
import br.ufrpe.cine_rural.dados.implemento.RepositorioFilmeImpl;
import br.ufrpe.cine_rural.dados.implemento.RepositorioSessaoImpl;
import br.ufrpe.cine_rural.enums.*;
import br.ufrpe.cine_rural.gui.controllers_telas.FilmesController;
import br.ufrpe.cine_rural.model.Filme;
import br.ufrpe.cine_rural.model.Sessao;
import br.ufrpe.cine_rural.model.tiposala.Comum;
import br.ufrpe.cine_rural.model.tiposala.Imax;
import br.ufrpe.cine_rural.model.tiposala.Vip;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class Main extends Application {
    public static RepositorioFilmeImpl filmes;
    public static RepositorioSessaoImpl sessoes;
    @Override
    public void start(Stage stage) throws Exception {

        filmes = new RepositorioFilmeImpl();
        sessoes = new RepositorioSessaoImpl();
        // =========================
        // IMAGENS
        // =========================

        Image poster1 = new Image(
                getClass().getResourceAsStream(
                        "/br/ufrpe/cine_rural/gui/Project_Hail_Mary_poster.jpg"
                )
        );

        Image poster2 = new Image(
                getClass().getResourceAsStream(
                        "/br/ufrpe/cine_rural/gui/Odisseia.jpg"
                )
        );

        Image poster3 = new Image(
                getClass().getResourceAsStream(
                        "/br/ufrpe/cine_rural/gui/Zootopia_2.jpg"
                )
        );


        filmes.cadastrar(new Filme(
                "Devoradores de Estrelas",
                "Sinopse...",
                130,
                Genero.FICCAO,
                ClassificacaoIndicativa.QUATORZE,
                poster1
        ));

        filmes.cadastrar(new Filme(
                "A Odisseia",
                "Sinopse...",
                150,
                Genero.DRAMA,
                ClassificacaoIndicativa.DEZESSEIS,
                poster2
        ));

        filmes.cadastrar(new Filme(
                "Zootopia 2",
                "Sinopse...",
                90,
                Genero.COMEDIA,
                ClassificacaoIndicativa.LIVRE,
                poster3
        ));


        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.EM_EXIBICAO,
                LocalDateTime.of(2026, 5, 30, 14, 30),
                filmes.buscar("Devoradores de Estrelas"),
                new Vip(1, 20)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.LEGENDADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 18, 0),
                filmes.buscar("A Odisseia"),
                new Imax(2, 40)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 20, 0),
                filmes.buscar("Zootopia 2"),
                new Comum(3, 20)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 18, 0),
                filmes.buscar("Zootopia 2"),
                new Comum(3, 20)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 20, 0),
                filmes.buscar("Zootopia 2"),
                new Imax(5, 60)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 10, 0),
                filmes.buscar("Zootopia 2"),
                new Vip(6, 10)
        ));

        sessoes.cadastrar(new Sessao(
                Idioma.DUBLADO,
                StatusSessao.ABERTA,
                LocalDateTime.of(2026, 5, 30, 20, 0),
                filmes.buscar("Devoradores de Estrelas"),
                new Vip(7, 20)
        ));

        FXMLLoader loaderFilmes = new FXMLLoader(
                Main.class.getResource(
                        "/br/ufrpe/cine_rural/gui/Filmes.fxml"
                )
        );

        Scene sceneFilmes = new Scene(loaderFilmes.load());

        sceneFilmes.getStylesheets().add(
                Main.class.getResource(
                        "/br/ufrpe/cine_rural/gui/EstiloFilmes.css"
                ).toExternalForm()
        );

        FilmesController controller = loaderFilmes.getController();

        controller.setRepositorios(
                filmes,
                sessoes
        );

        stage.setTitle("Filmes");
        stage.setScene(sceneFilmes);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


 */

import br.ufrpe.cine_rural.dados.implemento.RepositorioFilmeImpl;
import br.ufrpe.cine_rural.dados.implemento.RepositorioSalaImpl;
import br.ufrpe.cine_rural.dados.implemento.RepositorioSessaoImpl;
import br.ufrpe.cine_rural.gui.controllers_telas.GerenciarSessaoController;
import br.ufrpe.cine_rural.model.tiposala.Comum;
import br.ufrpe.cine_rural.model.tiposala.Imax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

    RepositorioFilmeImpl filmes = new RepositorioFilmeImpl();
    RepositorioSalaImpl salas = new RepositorioSalaImpl();

    Comum sala1 = new Comum(1, 20);
    Imax sala2 = new Imax(2, 30);
    salas.cadastrar(sala1);
    salas.cadastrar(sala2);

    RepositorioSessaoImpl sessoes = new RepositorioSessaoImpl(filmes, salas);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/br/ufrpe/cine_rural/gui/Gerenciar sessoes.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        GerenciarSessaoController controller =
                loader.getController();

        controller.setRepositorios(filmes, sessoes, salas);

        stage.setTitle("Cine Rural");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
import br.ufrpe.cine_rural.dados.implemento.RepositorioFilmeImpl;
import br.ufrpe.cine_rural.dados.implemento.RepositorioSalaImpl;
import br.ufrpe.cine_rural.dados.implemento.RepositorioSessaoImpl;
import br.ufrpe.cine_rural.gui.controllers_telas.GerenciarfilmeController;
import br.ufrpe.cine_rural.model.tiposala.Comum;
import br.ufrpe.cine_rural.model.tiposala.Imax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        RepositorioFilmeImpl filmes = new RepositorioFilmeImpl();
        RepositorioSalaImpl salas = new RepositorioSalaImpl();
        RepositorioSessaoImpl sessoes = new RepositorioSessaoImpl(filmes, salas);

        Comum sala1 = new Comum(1, 20);
        Imax sala2 = new Imax(2, 30);

        salas.cadastrar(sala1);
        salas.cadastrar(sala2);

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                        "/br/ufrpe/cine_rural/gui/Gerenciar filmes.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        GerenciarfilmeController controller = loader.getController();

        controller.setRepositorios(filmes);

        stage.setTitle("Cine Rural - Gerenciar Filmes");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
 */