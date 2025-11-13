package mejorCircuito;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public interface BuscadorMejorCircuitoStrategy {
	
	CircuitoMaritimo seleccionarMejorCircuito(List<CircuitoMaritimo> circuitos, TerminalGestionada destino);
	
}
