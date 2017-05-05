package banking;

import java.time.LocalDateTime;

public class Vigia {

  public static void main(String[] args) {
    System.out.println("Revisando si hay cuentas nuevas...");

    RepositorioTransferencias.instance.pendientesAl(LocalDateTime.now()).forEach(it -> {
      System.out.println("Realizando transferencia desde " + it.getUsuario() + " a " + it.getDestinatario() + " $" + it.getCantidad());
      it.ejecutar();
    });
  }

}
