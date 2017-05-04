import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UsuarioTest {
	
	private Usuario destinatario;
	private Usuario usuario;

	@Before
	public void initContext() {
		this.destinatario = new Usuario();
		this.usuario = new Usuario();
		this.usuario.depositar(5000);
	}
	
	@Test
	public void hacerUnDepositoAcreditaElSaldo() {
		this.usuario.depositar(100);
		Assert.assertEquals(5100, this.usuario.saldo(), 0);
	}

	@Test
	public void transferirAOtroUsuarioSeAcreditaCorrectamente() {
		this.usuario.transferir(4000, destinatario);
		Assert.assertEquals(1000, this.usuario.saldo(), 0);
		Assert.assertEquals(4000, this.destinatario.saldo(), 0);
	}
	
	@Test
	public void transferenciaProgramadaSeDeberiaEjecutarAlPasarElDia() {
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		this.usuario.programarTransferencia(4000, destinatario, tomorrow);
		Assert.assertEquals(1, RepositorioTransferencias.instance.pendientesAl(tomorrow).size());
	}
	
	@Test
	public void transferenciaProgramadaNoQuedaPendienteHastaManiana() {
		LocalDateTime tomorrow = LocalDateTime.now().plusDays(1);
		this.usuario.programarTransferencia(4000, destinatario, tomorrow);
		Assert.assertEquals(0, RepositorioTransferencias.instance.pendientesAl(LocalDateTime.now()).size());
	}
	
}
