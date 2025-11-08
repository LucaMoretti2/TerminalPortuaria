package empresaMaritima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import buscador.BuscadorDeTrayectosStrategy;
import containers.Container;

public class TerminalGestionada {
	
	//TerminalGestionada instanciaUnica;
	double posicion;
	BuscadorDeTrayectosStrategy motorDeBusqueda;
	List<Naviera> navieras;
	List<CircuitoMaritimo> circuitos;
	List<ActorPortuario> actores;
	List<EmpresaTransportista> empresasTransportistas;
	List<Camion> camiones;
	List<Chofer> choferes;
	List<Orden> ordenes = new ArrayList<>();
		
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
		
	public CircuitoMaritimo buscarRuta(TerminalGestionada terminal) {
		return this.motorDeBusqueda.buscar(terminal);
	}
	
	public void registrarOrdenDeExportacion(Shipper shipper, Container container, Viaje viaje, Camion camion, Chofer chofer, LocalDateTime turno) {
		OrdenDeExportacion orden = new OrdenDeExportacion(container, viaje, shipper, camion, chofer, turno);
		ordenes.add(orden);
	}
	
	public void registrarOrdenDeImportacion(Consignee consignee, Container container, Viaje viaje, LocalDateTime llegada) {
		OrdenDeImportacion orden = new OrdenDeImportacion(container, viaje, consignee, llegada);
		ordenes.add(orden);
		
	}
	
}
