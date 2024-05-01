package negocio;

import basedados.Banco;
import entidade.Cliente;
import java.util.Optional;

public class ClienteNegocio {

  private Banco bancoDados;

  public ClienteNegocio(Banco banco) {
    this.bancoDados = banco;
  }

  public Optional<Cliente> consultar(String cpf) {
    if (bancoDados.getCliente().getCpf().equals(cpf)) {
      return Optional.of(bancoDados.getCliente());
    } else {
      return Optional.empty();
    }
  }
}
