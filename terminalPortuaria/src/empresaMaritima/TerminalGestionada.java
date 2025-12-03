package empresaMaritima;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import actores.ActorPortuario;
import actores.Consignee;
import actores.Shipper;
import buscador.BuscadorDeTrayectosStrategy;
import containers.Container;
import mejorCircuito.BuscadorMejorCircuitoStrategy;
import ordenes.Orden;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;
import serviciosDeContainer.Servicio;

/**
 * Representa una terminal portuaria gestionada, encargada de coordinar navieras,
 * circuitos marítimos, órdenes de exportación e importación, y otros actores del puerto.
 * 
 * Cada terminal puede buscar rutas hacia otras, registrar actores o camiones,
 * generar facturas, y notificar a los responsables según el buque asociado.
 */

public class TerminalGestionada{

	//Simplificada la posicion para el dominio actual
	double posicion;
	String nombre;
	//Estrategia que define cómo se buscan los trayectos marítimos hacia otras terminales <PUERTOS>
	BuscadorDeTrayectosStrategy motorDeBusqueda; 
	// Estrategia que define el mejor circuito entre varios posibles.
	private BuscadorMejorCircuitoStrategy criterioSeleccion; 
	
	//Lista de entidades registradas en la terminal.
	List<Naviera> navieras = new ArrayList<>();
	List<CircuitoMaritimo> circuitos = new ArrayList<>();
	List<ActorPortuario> actores = new ArrayList<>();
	List<EmpresaTransportista> empresasTransportistas = new ArrayList<>();
	List<Camion> camiones = new ArrayList<>();
	List<Chofer> choferes = new ArrayList<>();
	List<Orden> ordenes = new ArrayList<>();
	List<Viaje> viajesProgramados = new ArrayList<>();
	List<Container> containers = new ArrayList<>();
	Set<Servicio> servicios = new HashSet<>();
	
	
	
	
	public TerminalGestionada(double posicion,String nombre,BuscadorDeTrayectosStrategy motorDeBusqueda) {
		this.posicion = posicion;
		this.nombre = nombre;
		this.motorDeBusqueda = motorDeBusqueda;
	}
		
	
	//Métodos de registro de entidades --------------------------------------
	public void registrarNaviera(Naviera naviera) {
		navieras.add(naviera);
	}
		
	public void registrarCircuitoMaritimo(CircuitoMaritimo circuito) {
		//Evita registrar el circuito más de una vez.
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
	//-----------------------------------------------------------------------
	
	
	/*
	 * Busca una ruta hacia otra terminal utilizando el motor de búsqueda
	 * @param terminal destino a buscar.
	 * @param fechaLimite fecha máxima de llegada.
	 * @param lista de posible circuitos.
	 */
	
	public List<CircuitoMaritimo> buscarRuta(TerminalGestionada terminal, LocalDateTime fechaLimite) {
        return this.motorDeBusqueda.buscar(terminal, fechaLimite);
    }

    public void setBuscador(BuscadorDeTrayectosStrategy buscador) {
        this.motorDeBusqueda = buscador;
    }
	
    //Órdenes ---------------------------------------------------------------------------------------------------------------------------
    
	public void registrarOrdenDeExportacion(Shipper shipper, Container container, Viaje viaje, Buque buque,Camion camion, Chofer chofer, LocalDateTime turno) {
		OrdenDeExportacion orden = new OrdenDeExportacion(container, viaje,buque, shipper, camion, chofer, turno);
		ordenes.add(orden);
	}
	
	public void registrarOrdenDeImportacion(Consignee consignee, Container container, Viaje viaje,Buque buque, LocalDateTime llegada) {
		OrdenDeImportacion orden = new OrdenDeImportacion(container, viaje,buque, consignee, llegada);
		ordenes.add(orden);
		
	}
	
	//Getters útiles----------------------------------------
	
	public List<Orden> getOrdenes(){
		return ordenes;
	}
	
	public List<Viaje> getViajesProgramados(){
		return viajesProgramados;
	}
	
	public List<CircuitoMaritimo> getCircuitos(){
		return circuitos;
	}
	
	//--------------------------------------------------------------------
	
	//Notificaciones de importador/exportador ---------------------------------------
	
	public void notificarConsignees(String mensaje, Buque buque) {
		for (Orden orden : ordenes) {
	        if (orden instanceof OrdenDeImportacion && orden.getBuque().equals(buque)) {
	            OrdenDeImportacion ordenImportacion = (OrdenDeImportacion) orden;
	            Consignee consignee = ordenImportacion.getConsignee();
	            consignee.notificar(mensaje);
	        }
	    }
	}

	public void notificarShippers(String mensaje, Buque buque) {
		for (Orden orden : ordenes) {
	        if (orden instanceof OrdenDeExportacion && orden.getBuque().equals(buque)) {
	            OrdenDeExportacion ordenExportacion = (OrdenDeExportacion) orden;
	            Shipper shipper = ordenExportacion.getShipper();
	            shipper.notificar(mensaje);
	        }
	    }
	}
	/*
	 * Generacion de facturas para TODAS las órdenes asociadas al buque dado.
	 * Imprime también costos adicionales si la orden es de importacion.
	 * */
	
	public void generarFacturas(Buque buque) {
	    for (Orden orden : ordenes) {
	        if (orden.getBuque().equals(buque)) {

	            ActorPortuario responsable;
	            
	            //Determina quien paga: shipper o consignee
	            if (orden instanceof OrdenDeExportacion) {
	                OrdenDeExportacion ordenExport = (OrdenDeExportacion) orden;
	                responsable = ordenExport.getShipper();
	            } else if (orden instanceof OrdenDeImportacion) {
	                OrdenDeImportacion ordenImport = (OrdenDeImportacion) orden;
	                responsable = ordenImport.getConsignee();
	            } else {
	            	continue;
	            }
	            
	            //Se genera la factura con los servicios utilizados
	            List<Servicio> servicios = orden.getServicios();
	            Factura factura = new Factura(LocalDate.now(), responsable, servicios,orden.getContainer());

	            factura.enviarPorMail();
	            
	            //En órdenes de importacion se mmuestra el costo del viaje.
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
	//----------------------------------------------------------------------------
	
	//Tanto inicioTrabajo() como ordenDeparting() simulan los trabajos correspondientes:
	public void inicioTrabajo() {
		System.out.println("Iniciando trabajos de carga/descarga...");
	}
	
	public void ordenDeparting() { 
		System.out.println("Los trabajos de carga/descarga han finalizado.");
	}
	
	public void setCriterioSeleccion(BuscadorMejorCircuitoStrategy criterio) {
	    this.criterioSeleccion = criterio;
	}
	
	public CircuitoMaritimo obtenerMejorCircuito(TerminalGestionada destino) {
	    return getCriterioSeleccion().seleccionarMejorCircuito(circuitos, destino);
	}


	public String getNombre() {
		// TODO Auto-generated method stub
		return nombre;
	}


	public BuscadorDeTrayectosStrategy getMotorDeBusqueda() {
		// TODO Auto-generated method stub
		return motorDeBusqueda;
	}


	public BuscadorMejorCircuitoStrategy getCriterioSeleccion() {
		return criterioSeleccion;
	}
	
	public void iniciarServicioParaElContainer(Container c, Servicio s) {
		c.addServicio(s);
	}
	
	public Set<Servicio> getServicios() {
		return servicios;
	}
	
	public List<Container> getContainers() { 
		return containers;
	}
	
	public void addServicio(Servicio s) {
		servicios.add(s);
	}

	public double costoTotalDeServiciosEnContainer(Container c) {
		
		return c.costoTotalDeServicios();
	}
}
