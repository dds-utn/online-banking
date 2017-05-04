import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Usuario {

	private static final LocalTime ULTIMA_HORA_BANCARIA = LocalTime.of(14, 0);
	private static final LocalTime PRIMERA_HORA_BANCARIA = LocalTime.of(9, 0);
	private double saldo = 0;

	public void depositar(double cantidadADepositar) {
		this.saldo += cantidadADepositar;
	}

	public double saldo() {
		return saldo;
	}

	public void transferir(int cantidadATransferir, Usuario destinatario) {
		if (this.esHorarioBancario()) {
			retirar(cantidadATransferir);
			destinatario.depositar(cantidadATransferir);
		} else {
			programarTransferencia(cantidadATransferir, destinatario, LocalDateTime.of(LocalDate.now().plusDays(1), PRIMERA_HORA_BANCARIA));
		}
	}

	private boolean esHorarioBancario() {
		LocalTime now = LocalTime.now(GlobalClock.clock);
		return now.isAfter(PRIMERA_HORA_BANCARIA) && now.isBefore(ULTIMA_HORA_BANCARIA);
	}

	private double retirar(int cantidadATransferir) {
		return this.saldo -= cantidadATransferir;
	}

	public void programarTransferencia(int cantidad, Usuario destinatario, LocalDateTime localDateTime) {
		RepositorioTransferencias.instance.agregar(new TransferenciaProgramada(this, localDateTime, cantidad, destinatario));
	}

}
