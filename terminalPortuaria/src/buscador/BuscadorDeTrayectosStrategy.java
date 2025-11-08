package buscador;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public interface BuscadorDeTrayectosStrategy {
		List<CircuitoMaritimo> buscar(TerminalGestionada destino);
}
