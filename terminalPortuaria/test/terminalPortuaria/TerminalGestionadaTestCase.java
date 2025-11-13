package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import actores.*;
import buscador.BuscadorDeTrayectosStrategy;
import empresaMaritima.*;
import ordenes.*;

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
	 
	 
}