package br.ufrpe.cine_rural;

public class Main {
    public static void main(String[] args) {

        // Criando produtos
        Produto p1 = new Produto(1, "Pipoca", 10.00, 50);
        Produto p2 = new Produto(2, "Refrigerante", 7.50, 30);
        Produto p3 = new Produto(3, "Chocolate", 5.00, 0); // sem estoque

        // Criando uma venda
        VendaLojinha venda = new VendaLojinha();

        // Teste 1: Adicionar itens normais
        System.out.println("=== Adicionando itens ===");
        venda.adicionarItem(p1, 2); // 2 pipocas
        venda.adicionarItem(p2, 3); // 3 refrigerantes

        // Teste 2: Tentar adicionar produto sem estoque
        System.out.println("\n=== Testando estoque vazio ===");
        try {
            venda.adicionarItem(p3, 1);
        } catch (RuntimeException e) {
            System.out.println("Erro esperado: " + e.getMessage());
        }

        // Teste 3: Remover quantidade parcial
        System.out.println("\n=== Removendo 1 refrigerante ===");
        venda.removerItem(p2, 1); // remove 1 dos 3

        // Teste 4: Remover item completamente
        System.out.println("\n=== Removendo todas as pipocas ===");
        venda.removerItem(p1, 10); // quantidade maior que o adicionado → remove tudo

        // Teste 5: Tentar remover produto que não está na venda
        System.out.println("\n=== Removendo produto não adicionado ===");
        venda.removerItem(p3, 1);

        // Teste 6: Finalizar venda
        System.out.println("\n=== Finalizando venda ===");
        venda.finalizarVenda();
    }
}