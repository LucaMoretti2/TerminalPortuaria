package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import actores.*;
import buscador.BuscadorDeTrayectosStrategy;
import containers.Dry;
import empresaMaritima.*;
import ordenes.*;
import serviciosDeContainer.Servicio;

class TerminalGestionadaTestCase {

	@BeforeEach
	void setUp() throws Exception {
	
	}

	@Test
    void testRegistrarCircuitoMaritimoNoDuplica() {
        BuscadorDeTrayectosStrategy buscador = mock(BuscadorDeTrayectosStrategy.class);
        TerminalGestionada terminal = new TerminalGestionada(10.0, "Buenos Aires", buscador);
        CircuitoMaritimo circuito = mock(CircuitoMaritimo.class);

        terminal.registrarCircuitoMaritimo(circuito);
        terminal.registrarCircuitoMaritimo(circuito);

        assertEquals(1, terminal.getCircuitos().size()); 
    }

	@Test
    void testBuscarRutaUsaMotorDeBusqueda() {
        BuscadorDeTrayectosStrategy buscadorMock = mock(BuscadorDeTrayectosStrategy.class);
        TerminalGestionada terminal = new TerminalGestionada(10.0, "BRasil", buscadorMock);
        TerminalGestionada destino = mock(TerminalGestionada.class);
        LocalDateTime fecha = LocalDateTime.now();

        terminal.buscarRuta(destino, fecha);

        verify(buscadorMock).buscar(destino, fecha);
    }
	
	 @Test
	 void testNotificarConsigneesSoloImportacionesDelBuque() {
		 TerminalGestionada terminal = new TerminalGestionada(10.0, "BRasil", mock(BuscadorDeTrayectosStrategy.class));
		 Buque buque = mock(Buque.class);
		 Consignee consignee = mock(Consignee.class);

		 OrdenDeImportacion orden = mock(OrdenDeImportacion.class);
		 when(orden.getBuque()).thenReturn(buque);
		 when(orden.getConsignee()).thenReturn(consignee);

		 terminal.getOrdenes().add(orden);
		 terminal.notificarConsignees("Llego el buque", buque);

		 verify(consignee).notificar("Llego el buque");
	 }
	 
	 @Test
	 void testNotificarShippersSoloExportacionesDelBuque() {
		 TerminalGestionada terminal = new TerminalGestionada(10.0, "BRasil", mock(BuscadorDeTrayectosStrategy.class));
		 Buque buque = mock(Buque.class);
		 Shipper shipper = mock(Shipper.class);

		 OrdenDeExportacion orden = mock(OrdenDeExportacion.class);
		 when(orden.getBuque()).thenReturn(buque);
		 when(orden.getShipper()).thenReturn(shipper);

		 terminal.getOrdenes().add(orden);
		 terminal.notificarShippers("Partio el buque", buque);

		 verify(shipper).notificar("Partio el buque");
	    }
	 
	
	 
	 @Test
	 void testTiempoDeRecorrido() {
		 BuscadorDeTrayectosStrategy buscador = mock(BuscadorDeTrayectosStrategy.class);
		 TerminalGestionada origen = new TerminalGestionada(10.0, "Buenos Aires", buscador);
		 TerminalGestionada destino = new TerminalGestionada(20.0, "Montevideo", buscador);
		 Naviera navieraMock = mock(Naviera.class);

		 when(navieraMock.tiempoDeRecorrido(origen, destino)).thenReturn(20L); 

		 long resultado = origen.tiempoDeRecorrido(navieraMock, destino);

		 assertEquals(20L, resultado);
		 verify(navieraMock).tiempoDeRecorrido(origen, destino);
	 }
	 
	 @Test
	 void testProximaFechaDePartidaDevuelveLaMasCercana() {
		 TerminalGestionada terminal = new TerminalGestionada(10.0, "Mar del Plata", mock(BuscadorDeTrayectosStrategy.class));
		 TerminalGestionada destino = mock(TerminalGestionada.class);
		 CircuitoMaritimo circuitoMock = mock(CircuitoMaritimo.class);

		 when(circuitoMock.contieneTerminal(destino)).thenReturn(true);

		 Viaje viaje1 = mock(Viaje.class);
		 Viaje viaje2 = mock(Viaje.class);

		 when(viaje1.getCircuito()).thenReturn(circuitoMock);
		 when(viaje2.getCircuito()).thenReturn(circuitoMock);

		 when(viaje1.getPartida()).thenReturn(LocalDateTime.of(2025, 1, 10, 8, 0));
		 when(viaje2.getPartida()).thenReturn(LocalDateTime.of(2025, 1, 5, 8, 0));

		 terminal.getViajesProgramados().addAll(List.of(viaje1, viaje2));

		 LocalDateTime proxima = terminal.proximaFechaDePartida(destino);

		 assertEquals(LocalDateTime.of(2025, 1, 5, 8, 0), proxima);
	    }
	// Test 4: Cálculo del costo total de servicios aplicados al contenedor
		@Test
	    void testCostoTotalDeServicios() {
			
		    LocalDateTime ingreso = LocalDateTime.of(2025, 1, 1, 10, 0);
		    LocalDateTime retiro = LocalDateTime.of(2025, 1, 2, 10, 0);
			
	        Dry container = new Dry(2, 3, 6, 5000, "1234", ingreso, retiro);
	        BuscadorDeTrayectosStrategy buscador = mock(BuscadorDeTrayectosStrategy.class);
	        TerminalGestionada terminal = new TerminalGestionada(10.0, "Buenos Aires", buscador);

	        Servicio s1 = mock(Servicio.class);
	        Servicio s2 = mock(Servicio.class);

	        when(s1.getPrecioFijo()).thenReturn(100.0);
	        when(s2.getPrecioFijo()).thenReturn(200.0);

	        container.addServicio(s1);
	        container.addServicio(s2);

	        double total = terminal.costoTotalDeServiciosEnContainer(container);
	        assertEquals(300, total);
	    }
	 
}