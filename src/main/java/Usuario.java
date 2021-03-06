
public class Usuario {

	private double saldo = 0;

	public void depositar(double cantidadADepositar) {
		this.saldo += cantidadADepositar;
	}

	public double saldo() {
		return saldo;
	}

	public void transferir(int cantidadATransferir, Usuario destinatario) {
		retirar(cantidadATransferir);
		destinatario.depositar(cantidadATransferir);
	}

	private double retirar(int cantidadATransferir) {
		return this.saldo -= cantidadATransferir;
	}

}
