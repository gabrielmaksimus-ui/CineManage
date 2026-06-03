package main.java.br.ufrpe.cine_rural.controllers;

import main.java.br.ufrpe.cine_rural.dados.interfaces.iRepositorioProduto;
import main.java.br.ufrpe.cine_rural.model.beans.loja.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {
    private final iRepositorioProduto repositorioProduto;


    // Limiar para alerta de estoque baixo
    private static final int LIMIAR_ESTOQUE_BAIXO = 5;

    public ProdutoController(iRepositorioProduto repositorioProduto ) {
        this.repositorioProduto = repositorioProduto;

    }


    public void cadastrarProduto(int id, String nome, double preco, int qtdEstoque) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do produto não pode ser vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        if (qtdEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa.");
        }
        if (repositorioProduto.buscar(id) != null) {
            throw new IllegalStateException("Já existe um produto com o ID: " + id);
        }

        repositorioProduto.cadastrar(new Produto(id, nome, preco, qtdEstoque));
    }


    public Produto buscarProduto(int id) {
        Produto produto = repositorioProduto.buscar(id);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado com ID: " + id);
        }
        return produto;
    }

   void atualizarProduto(int id, String novoNome, double novoPreco, int novaQtd) {
        Produto produto = buscarProduto(id);
        produto.setNome(novoNome);
        produto.setPreco(novoPreco);
        produto.setQtdEstoque(novaQtd);
        repositorioProduto.atualizar(produto);
    }


    public void removerProduto(int id) {
        buscarProduto(id); // garante existência
        repositorioProduto.remover(id);
    }


     // Lista todos os produtos cadastrados.

    public ArrayList<Produto> listarProdutos() {
        return repositorioProduto.listar();
    }


     // Valida o estoque antes de confirmar venda.

    public void validarEstoque(Produto produto, int quantidadeDesejada) {
        if (produto.getQtdEstoque() < quantidadeDesejada) {
            throw new IllegalStateException(
                    "Estoque insuficiente para '" + produto.getNome()
                            + "'. Disponível: " + produto.getQtdEstoque()
                            + ", solicitado: " + quantidadeDesejada
            );
        }
    }

    /*
    public List<Produto> verificarEstoqueBaixo() {
        List<Produto> alertas = new ArrayList<>();

        for (Produto p : repositorioProduto.listar()) {
            if (p.getQtdEstoque() <= LIMIAR_ESTOQUE_BAIXO) {
                alertas.add(p);
            }
        }

        if (!alertas.isEmpty()) {
            notificacaoController.alertarGerenteEstoqueBaixo(alertas);
        }

        return alertas;
    }
    */

}
