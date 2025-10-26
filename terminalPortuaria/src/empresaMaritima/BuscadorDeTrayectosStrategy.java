package empresaMaritima;

import java.util.List;

public interface BuscadorDeTrayectosStrategy {
		List<CircuitoMaritimo> buscar(String destino);
}
