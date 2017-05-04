import java.time.LocalDateTime;

public class TransferenciaProgramada {

	private Usuario usuario;
	private int cantidad;
	private Usuario destinatario;
	private LocalDateTime fechaAEjecutar;

	public TransferenciaProgramada(Usuario usuario, LocalDateTime localDateTime, int cantidad, Usuario destinatario) {
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

}
