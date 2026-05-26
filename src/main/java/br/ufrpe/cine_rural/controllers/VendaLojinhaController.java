package main.java.br.ufrpe.cine_rural.controllers;

import main.java.br.ufrpe.cine_rural.dados.interfaces.iRepositorioVendaLojinha;
import main.java.br.ufrpe.cine_rural.model.beans.loja.Produto;
import main.java.br.ufrpe.cine_rural.model.beans.loja.VendaLojinha;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller responsável pela gestão de vendas da bomboniere
 * (REQ11, REQ13, REQ14, REQ22).
 */
public class VendaLojinhaController {

    private final iRepositorioVendaLojinha repositorioVenda;
    private final ProdutoController produtoController;

    // Mapa para rastrear data de cada venda (índice → data)
    private final Map<Integer, LocalDate> datasVenda = new HashMap<>();

    public VendaLojinhaController(iRepositorioVendaLojinha repositorioVenda,
                                   ProdutoController produtoController) {
        this.repositorioVenda = repositorioVenda;
        this.produtoController = produtoController;
    }

    /**
     * REQ11 - Inicia uma nova venda da lojinha.
     * Retorna o índice da venda criada para referência futura.
     */
    public int iniciarVenda() {
        VendaLojinha venda = new VendaLojinha();
        repositorioVenda.cadastrar(venda);
        ArrayList<VendaLojinha> todas = repositorioVenda.listar();
        int indice = todas.size() - 1;
        datasVenda.put(indice, LocalDate.now());
        return indice;
    }

    /**
     * REQ11 / REQ22 - Adiciona item à venda, validando estoque antes.
     */
    public void adicionarItem(int indiceVenda, int idProduto, int quantidade) {
        VendaLojinha venda = buscarVenda(indiceVenda);
        Produto produto = produtoController.buscarProduto(idProduto);

        // REQ22 - valida estoque antes de confirmar
        produtoController.validarEstoque(produto, quantidade);

        venda.adicionarItem(produto, quantidade);
    }

    /**
     * Remove um item (ou reduz quantidade) de uma venda em andamento.
     */
    public void removerItem(int indiceVenda, int idProduto, int quantidade) {
        VendaLojinha venda = buscarVenda(indiceVenda);
        Produto produto = produtoController.buscarProduto(idProduto);
        venda.removerItem(produto, quantidade);
    }

    /**
     * Finaliza e exibe o resumo de uma venda.
     */
    public void finalizarVenda(int indiceVenda) {
        VendaLojinha venda = buscarVenda(indiceVenda);
        venda.finalizarVenda();
    }

    /**
     * Retorna o total monetário de uma venda.
     */
    public double calcularTotalVenda(int indiceVenda) {
        return buscarVenda(indiceVenda).calcularTotal();
    }

    /**
     * Remove uma venda pelo índice.
     */
    public void removerVenda(int indiceVenda) {
        buscarVenda(indiceVenda); // garante existência
        repositorioVenda.remover(indiceVenda);
    }

    /**
     * Lista todas as vendas.
     */
    public ArrayList<VendaLojinha> listarVendas() {
        return repositorioVenda.listar();
    }

    /**
     * REQ13 - Gera relatório de vendas da bomboniere por período.
     * Retorna mapa de índice da venda → total (R$) para vendas dentro do intervalo.
     */
    public Map<Integer, Double> gerarRelatorioVendasPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null || inicio.isAfter(fim)) {
            throw new IllegalArgumentException("Período inválido para o relatório.");
        }

        Map<Integer, Double> relatorio = new HashMap<>();
        ArrayList<VendaLojinha> todas = repositorioVenda.listar();

        for (int i = 0; i < todas.size(); i++) {
            LocalDate dataVenda = datasVenda.getOrDefault(i, LocalDate.now());
            if (!dataVenda.isBefore(inicio) && !dataVenda.isAfter(fim)) {
                relatorio.put(i, todas.get(i).calcularTotal());
            }
        }

        return relatorio;
    }

    /**
     * REQ14 - Exporta faturamento diário em formato CSV.
     * Retorna uma String com linhas no padrão: indice,data,total
     */
    public String exportarFaturamentoDiarioCSV(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data para exportação não pode ser nula.");
        }

        StringBuilder csv = new StringBuilder("indice,data,total\n");
        ArrayList<VendaLojinha> todas = repositorioVenda.listar();

        for (int i = 0; i < todas.size(); i++) {
            LocalDate dataVenda = datasVenda.getOrDefault(i, LocalDate.now());
            if (dataVenda.equals(data)) {
                csv.append(i)
                   .append(",")
                   .append(dataVenda)
                   .append(",")
                   .append(String.format("%.2f", todas.get(i).calcularTotal()))
                   .append("\n");
            }
        }

        return csv.toString();
    }

    // -------------------------------------------------------------------------
    // Helper
    // -------------------------------------------------------------------------

    private VendaLojinha buscarVenda(int indice) {
        VendaLojinha venda = repositorioVenda.buscar(indice);
        if (venda == null) {
            throw new IllegalArgumentException("Venda não encontrada no índice: " + indice);
        }
        return venda;
    }
}
