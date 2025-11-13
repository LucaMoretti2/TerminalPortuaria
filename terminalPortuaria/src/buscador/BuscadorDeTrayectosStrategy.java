package buscador;

import java.time.LocalDateTime;
import java.util.List;

import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

public interface BuscadorDeTrayectosStrategy {
	List<CircuitoMaritimo> buscar(TerminalGestionada destino, LocalDateTime fechaLimite); //metodo que va a implementar cada estrategia para buscar los trayectos
}
