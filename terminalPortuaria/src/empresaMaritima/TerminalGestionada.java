package empresaMaritima;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import buscador.BuscadorDeTrayectosStrategy;
import containers.Container;
import reportes.ReporteVisitor;

public class TerminalGestionada implements reportes.ReporteVisitable {
	

	double posicion;
	BuscadorDeTrayectosStrategy motorDeBusqueda;
	List<Naviera> navieras = new ArrayList<>();
	List<CircuitoMaritimo> circuitos = new ArrayList<>();
	List<ActorPortuario> actores = new ArrayList<>();
	List<EmpresaTransportista> empresasTransportistas = new ArrayList<>();
	List<Camion> camiones = new ArrayList<>();
	List<Chofer> choferes = new ArrayList<>();
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
		
	public void eliminarActorPortuario(ActorPortuario actor) {
		actores.remove(actor);
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
		
	public List<CircuitoMaritimo> buscarRuta(TerminalGestionada terminal, LocalDateTime fechaLimite) {
        return this.motorDeBusqueda.buscar(terminal, fechaLimite);
    }

    public void setBuscador(BuscadorDeTrayectosStrategy buscador) {
        this.motorDeBusqueda = buscador;
    }
	
	public void registrarOrdenDeExportacion(Shipper shipper, Container container, Viaje viaje, Camion camion, Chofer chofer, LocalDateTime turno) {
		OrdenDeExportacion orden = new OrdenDeExportacion(container, viaje, shipper, camion, chofer, turno);
		ordenes.add(orden);
	}
	
	public void registrarOrdenDeImportacion(Consignee consignee, Container container, Viaje viaje, LocalDateTime llegada) {
		OrdenDeImportacion orden = new OrdenDeImportacion(container, viaje, consignee, llegada);
		ordenes.add(orden);
		
	}
	
	public List<Orden> getOrdenes(){
		return ordenes;
	}
	
	public void notificarConsignees(String mensaje) {
	    for (ActorPortuario actor : actores) {
	        if (actor instanceof Consignee) {
	            actor.notificar(mensaje);
	        }
	    }
	}

	public void notificarShippers(String mensaje) {
	    for (ActorPortuario actor : actores ) {
	        if (actor instanceof Shipper) {
	            actor.notificar(mensaje);
	        }
	    }
	}

	@Override
	public void accept(ReporteVisitor visitor) {
	    visitor.visitarTerminal(this);
	}
	
	public void inicioTrabajo() {
		System.out.println("Iniciando trabajos de carga/descarga...");
	}
	
	public void ordenDeparting() { 
		System.out.println("Los trabajos de carga/descarga han finalizado.");
	}
}
