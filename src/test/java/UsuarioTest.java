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
}
