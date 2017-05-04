import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioTransferencias {

	public static RepositorioTransferencias instance = new RepositorioTransferencias();
	private List<TransferenciaProgramada> transferencias = new ArrayList<>();

	public void agregar(TransferenciaProgramada transferenciaProgramada) {
		this.transferencias.add(transferenciaProgramada);
	}

	public List<TransferenciaProgramada> transferenciasPendientes() {
		return transferencias;
	}

	public List<TransferenciaProgramada> pendientesAl(LocalDateTime fecha) {
		return transferencias
				.stream()
				.filter((transferencia) -> transferencia.deberiaEjecutarse(fecha))
				.collect(Collectors.toList());
	}

	public void reset() {
		transferencias = new ArrayList<>();
	}


}
