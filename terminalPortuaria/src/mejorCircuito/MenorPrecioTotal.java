package mejorCircuito;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class MenorPrecioTotal implements BuscadorMejorCircuitoStrategy {

	@Override
	public CircuitoMaritimo seleccionarMejorCircuito(List<CircuitoMaritimo> circuitos, TerminalGestionada destino) {
		// TODO Auto-generated method stub
		CircuitoMaritimo mejor = null;
        double menorPrecio = Double.MAX_VALUE;

        for (CircuitoMaritimo circuito : circuitos) {
            if ( circuito.contieneTerminal(destino)) {
                double precio = circuito.calcularPrecioHasta(	destino);
                if (precio < menorPrecio) {
                    menorPrecio = precio;
                    mejor = circuito;
                }
            }
        }
        return mejor;
	}

}
