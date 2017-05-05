package banking;
import java.time.LocalDateTime;

public class TransferenciaProgramada {

	private CuentaBancaria usuario;
	private int cantidad;
	private CuentaBancaria destinatario;
	private LocalDateTime fechaAEjecutar;

	public TransferenciaProgramada(CuentaBancaria usuario, LocalDateTime localDateTime, int cantidad, CuentaBancaria destinatario) {
		this.usuario = usuario;
		this.fechaAEjecutar = localDateTime;
		this.cantidad = cantidad;
		this.destinatario = destinatario;
	}

	public boolean deberiaEjecutarse(LocalDateTime fecha) {
		return fechaAEjecutar.isBefore(fecha) || fechaAEjecutar.isEqual(fecha);
	}
	
	public void ejecutar() {
		usuario.transferir(cantidad, destinatario);
	}
	
	public int getCantidad() {
    return cantidad;
  }
	
	public CuentaBancaria getUsuario() {
    return usuario;
  }
	
	public CuentaBancaria getDestinatario() {
    return destinatario;
  }

}
