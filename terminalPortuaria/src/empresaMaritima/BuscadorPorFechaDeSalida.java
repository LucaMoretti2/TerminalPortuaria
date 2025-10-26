package empresaMaritima;

import java.util.List;

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
