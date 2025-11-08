package buscador;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorPorPuertoDestino implements BuscadorDeTrayectosStrategy {

	List<CircuitoMaritimo> circuitos;
	
	public BuscadorPorPuertoDestino(List<CircuitoMaritimo> circuitos) {
        this.circuitos = circuitos;
    }
	
	@Override
	public List<CircuitoMaritimo> buscar(TerminalGestionada destino) {
		// TODO Auto-generated method stub
		return null;
	}
}
