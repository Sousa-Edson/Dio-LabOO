package entidade;

import java.util.List;

public class Pedido {
    private String codigo;
    private Cliente cliente;
    private List<Produto> produtos;
    private double total;

    public Pedido() {
    }

    public Pedido(String codigo, Cliente cliente, List<Produto> produtos) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.produtos = produtos;
        calcularTotal();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
        calcularTotal();
    }

    public double getTotal() {
        return total;
    }

    private void calcularTotal() {
        total = 0;
        for (Produto produto : produtos) {
            total += produto.getPreco();
        }
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "codigo=" + codigo +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                ", total=" + total +
                '}';
    }
}
