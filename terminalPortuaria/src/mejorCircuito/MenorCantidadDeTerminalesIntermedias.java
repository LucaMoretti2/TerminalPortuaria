package mejorCircuito;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class MenorCantidadDeTerminalesIntermedias implements BuscadorMejorCircuitoStrategy {

	@Override
	public CircuitoMaritimo seleccionarMejorCircuito(List<CircuitoMaritimo> circuitos, TerminalGestionada destino) {
		// TODO Auto-generated method stub
		CircuitoMaritimo mejor = null;
        int menorCantidad = Integer.MAX_VALUE;

        for (CircuitoMaritimo c : circuitos) {
            if (c.contieneTerminal(destino)) {
                int cantidad = c.cantidadDeTerminalesIntermedias(destino);
                if (cantidad < menorCantidad) {
                    menorCantidad = cantidad;
                    mejor = c;
                }
            }
        }
        return mejor;
	}

}
