package empresaMaritima;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import buscador.BuscadorDeTrayectosStrategy;
import containers.Container;
import reportes.ReporteVisitor;
import serviciosDeContainer.Servicio;

public class TerminalGestionada{
	

	double posicion;
	String nombre;
	BuscadorDeTrayectosStrategy motorDeBusqueda;
	List<Naviera> navieras = new ArrayList<>();
	List<CircuitoMaritimo> circuitos = new ArrayList<>();
	List<ActorPortuario> actores = new ArrayList<>();
	List<EmpresaTransportista> empresasTransportistas = new ArrayList<>();
	List<Camion> camiones = new ArrayList<>();
	List<Chofer> choferes = new ArrayList<>();
	List<Orden> ordenes = new ArrayList<>();
	List<Viaje> viajesProgramados = new ArrayList<>();
		
	public TerminalGestionada(double posicion,String nombre,BuscadorDeTrayectosStrategy motorDeBusqueda) {
		this.posicion = posicion;
		this.nombre = nombre;
		this.motorDeBusqueda = motorDeBusqueda;
	}
		
	public void registrarNaviera(Naviera naviera) {
		navieras.add(naviera);
	}
		
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuito) {
		if (!circuitos.contains(circuito)) {
	        circuitos.add(circuito);
	    }
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
	
	public void registrarOrdenDeExportacion(Shipper shipper, Container container, Viaje viaje, Buque buque,Camion camion, Chofer chofer, LocalDateTime turno) {
		OrdenDeExportacion orden = new OrdenDeExportacion(container, viaje,buque, shipper, camion, chofer, turno);
		ordenes.add(orden);
	}
	
	public void registrarOrdenDeImportacion(Consignee consignee, Container container, Viaje viaje,Buque buque, LocalDateTime llegada) {
		OrdenDeImportacion orden = new OrdenDeImportacion(container, viaje,buque, consignee, llegada);
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

	public void generarFacturas(Buque buque) {
	    for (Orden orden : ordenes) {
	        if (orden.getBuque().equals(buque)) {

	            ActorPortuario responsable;

	            if (orden instanceof OrdenDeExportacion) {
	                OrdenDeExportacion ordenExport = (OrdenDeExportacion) orden;
	                responsable = ordenExport.getShipper();
	            } else if (orden instanceof OrdenDeImportacion) {
	                OrdenDeImportacion ordenImport = (OrdenDeImportacion) orden;
	                responsable = ordenImport.getConsignee();
	            } else {
	            	continue;
	            }

	            List<Servicio> servicios = orden.getServicios();
	            Factura factura = new Factura(LocalDate.now(), responsable, servicios,orden.getContainer());

	            factura.enviarPorMail();

	            if (orden instanceof OrdenDeImportacion) {
	                OrdenDeImportacion ordenImport = (OrdenDeImportacion) orden;
	                double costoViaje = ordenImport.getViaje().calcularCostoViaje();
	                System.out.println("Costo adicional de viaje: $" + costoViaje);
	            }
	        }
	    }
	}
	
	public long tiempoDeRecorrido(Naviera naviera, TerminalGestionada destino) {
	    return naviera.tiempoDeRecorrido(this, destino);
	}
	
	public LocalDateTime proximaFechaDePartida(TerminalGestionada destino) {
	    LocalDateTime proxima = null;
	    

	    for (Viaje viaje : viajesProgramados) {
	        CircuitoMaritimo circuito = viaje.getCircuito();

	        if (circuito.contieneTerminal(destino)) {
	            if (proxima == null || viaje.getPartida().isBefore(proxima)) {
	                proxima = viaje.getPartida();
	            }
	        }
	    }

	    return proxima;
	}
	
	public void inicioTrabajo() {
		System.out.println("Iniciando trabajos de carga/descarga...");
	}
	
	public void ordenDeparting() { 
		System.out.println("Los trabajos de carga/descarga han finalizado.");
	}
}
