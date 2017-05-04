import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

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
		mockNowTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0)));

		this.usuario.transferir(4000, destinatario);
		Assert.assertEquals(1000, this.usuario.saldo(), 0);
		Assert.assertEquals(4000, this.destinatario.saldo(), 0);
	}
	
	@Test
	public void transferirFueraDeHorarioGeneraUnaTransferenciaParaMañana() {
		mockNowTo(LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0)));

		this.usuario.transferir(4000, destinatario);
		Assert.assertEquals(5000, usuario.saldo(), 0);
		Assert.assertEquals(0, destinatario.saldo(), 0);
		LocalDateTime manianaPrimeraHora = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 0));
		Assert.assertEquals(1, RepositorioTransferencias.instance.pendientesAl(manianaPrimeraHora).size());
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
	
	private void mockNowTo(LocalDateTime mockNow) {
		Instant instant = mockNow.toInstant(OffsetDateTime.now().getOffset());
		GlobalClock.use(Clock.fixed(instant, ZoneId.systemDefault()));
	}
	
	@After
	public void reset() {
		RepositorioTransferencias.instance.reset();
		GlobalClock.reset();
	}
}
