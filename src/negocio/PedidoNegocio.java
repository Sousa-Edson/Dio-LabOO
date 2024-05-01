package negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import basedados.Banco;
import entidade.Cliente;
import entidade.Cupom;
import entidade.Pedido;
import entidade.Produto;

public class PedidoNegocio {
     private Banco bancoDados;

    
    public PedidoNegocio(Banco banco) {
        this.bancoDados = banco;
    }

    private double calcularTotal(List<Produto> produtos, Cupom cupom) {

        double total = 0.0;
        for (Produto produto: produtos) {
            total += produto.calcularFrete();
        }

        if (cupom != null) {
            return  total * (1 - cupom.getDesconto());
        } else {
            return  total;
        }

    }

     
    public void salvar(Pedido novoPedido) {
        salvar(novoPedido, null, null);
    }

    
    public void salvar(Pedido novoPedido,Cliente cliente, Cupom cupom) {

       // Definir padrão código
    String codigoPadrao = "PD"; // Padrão inicial do código
    LocalDate dataAtual = LocalDate.now(); // Obter a data atual
    String dataFormatada = dataAtual.format(DateTimeFormatter.ofPattern("yyyyMMdd")); // Formatar a data
    String codigo = codigoPadrao + dataFormatada; // Combinar padrão e data para formar o código

    // Setar código no pedido
    novoPedido.setCodigo(codigo);
 
    // Setar cliente no pedido (supondo que haja um método para definir o cliente)
    novoPedido.setCliente(cliente);

    // Calcular e setar total
    List<Produto> produtos = novoPedido.getProdutos(); // Obter a lista de produtos do pedido
    double total = calcularTotal(produtos, cupom); // Calcular o total do pedido
    // novoPedido.setTotal(total); // Definir o total no pedido

    // Adicionar no banco (supondo que haja um método para adicionar o pedido ao banco)
    bancoDados.adicionarPedido(novoPedido);

    // Exibir mensagem
    System.out.println("Pedido salvo com sucesso.");
    }

    
    public void excluir(String codigo) {

        int pedidoExclusao = -1;
        for (int i = 0; i < bancoDados.getPedidos().length; i++) {

            Pedido pedido = bancoDados.getPedidos()[i];
            if (pedido.getCodigo().equals(codigo)) {
                pedidoExclusao = i;
                break;
            }
        }

        if (pedidoExclusao != -1) {
            bancoDados.removerPedido(pedidoExclusao);
            System.out.println("Pedido excluído com sucesso.");
        } else {
            System.out.println("Pedido inexistente.");
        }
    }
    public void listarTodos() {
        Pedido[] pedidos = bancoDados.getPedidos();
    
        if (pedidos.length == 0) {
            System.out.println("Não existem pedidos cadastrados.");
        } else {
            System.out.println("Lista de Pedidos:");
            for (Pedido pedido : pedidos) {
                System.out.println(pedido);
            }
        }
    }
}
