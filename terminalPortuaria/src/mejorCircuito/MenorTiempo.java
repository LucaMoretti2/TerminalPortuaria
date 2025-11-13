package mejorCircuito;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class MenorTiempo implements BuscadorMejorCircuitoStrategy {

	@Override
	public CircuitoMaritimo seleccionarMejorCircuito(List<CircuitoMaritimo> circuitos, TerminalGestionada destino) {
		// TODO Auto-generated method stub
		CircuitoMaritimo mejor = null;
	    long menorTiempo = Long.MAX_VALUE;

	    for (CircuitoMaritimo circuito : circuitos) {
	        if (circuito.contieneTerminal(destino)) {
	            long tiempo = circuito.calcularDuracionHasta(destino);
	            if (tiempo < menorTiempo) {
	                menorTiempo = tiempo;
	                mejor = circuito;
	            }
	        }
	    }
	    return mejor;
	}
				
}
