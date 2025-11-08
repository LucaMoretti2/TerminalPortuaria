package buscador;

import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public class BuscadorPorFechaDeSalida implements BuscadorDeTrayectosStrategy {
	List<CircuitoMaritimo> circuitos;
	
	public BuscadorPorFechaDeSalida(List<CircuitoMaritimo> circuitos) {
        this.circuitos = circuitos;
    }
	
	@Override
	public List<CircuitoMaritimo> buscar(TerminalGestionada destino) {
		// TODO Auto-generated method stub
		return null;
	}
}
