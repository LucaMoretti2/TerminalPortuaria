package empresaMaritima;

import java.util.List;

public class TerminalGestionada {
	
	//TerminalGestionada instanciaUnica;
	double posicion;
	BuscadorDeTrayectosStrategy motorDeBusqueda;
	List<Naviera> navieras;
	List<CircuitoMaritimo> circuitos;
	List<ActorPortuario> actores;
	List<EmpresasTransportistas> empresasTransportistas;
	List<Camion> camiones;
	List<Chofer> choferes;
		
	public TerminalGestionada(double posicion) {
		this.posicion = posicion;
	}
		
	public void registrarNaviera(Naviera naviera) {
		navieras.add(naviera);
	}
		
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuito) {
		circuitos.add(circuito);
	}
		
	public void registrarActorPortuario(ActorPortuario actor) {
		actores.add(actor);
	}
		
	public void registrarEmpresaTransportista(EmpresaTransportista empresa) {
		empresasTransportistas.add(empresa);
	}
		
	public void registrarCamion(Camion camion) {
		camiones.add(camion);
	}
		
	public void registrarChofer(Chofer chofer) {
		choferes.add(chofer);
	}
		
	public CircuitoMaritimo buscarRuta(TerminalPortuaria terminal) {
		return this.motorDeBusqueda.buscar(terminal);
	}
}
