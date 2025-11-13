package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscador.BuscadorAnd;
import buscador.BuscadorDeTrayectosStrategy;
import buscador.BuscadorOr;
import buscador.BuscadorPorFechaDeLlegada;
import buscador.BuscadorPorFechaDeSalida;
import buscador.BuscadorPorPuertoDestino;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;

class BuscadorDeTrayectosTestCase {

	TerminalGestionada destino;
    CircuitoMaritimo circuito1;
    CircuitoMaritimo circuito2;
    LocalDateTime fechaLimite;
    BuscadorDeTrayectosStrategy criterio1;
    BuscadorDeTrayectosStrategy criterio2;
    CircuitoMaritimo c1, c2;
	
	@BeforeEach
	void setUp(){
		destino = mock(TerminalGestionada.class);

	    circuito1 = mock(CircuitoMaritimo.class);
	    circuito2 = mock(CircuitoMaritimo.class);

	    when(circuito1.contieneTerminal(destino)).thenReturn(true);
	    when(circuito2.contieneTerminal(destino)).thenReturn(false);

	    when(circuito1.getFechaDeInicio()).thenReturn(LocalDateTime.of(2025, 1, 1, 0, 0));
	    when(circuito2.getFechaDeInicio()).thenReturn(LocalDateTime.of(2025, 1, 10, 0, 0));

	    when(circuito1.calcularTiempoTotalHoras()).thenReturn(24L);
	    when(circuito2.calcularTiempoTotalHoras()).thenReturn(72L);

	    fechaLimite = LocalDateTime.of(2025, 1, 5, 0, 0);
	}

	@Test
	void testBuscadorPorPuertoDestinoDevuelveCircuitosQueContienenDestino() {
	    BuscadorPorPuertoDestino buscador = new BuscadorPorPuertoDestino(List.of(circuito1, circuito2));
	    List<CircuitoMaritimo> resultado = buscador.buscar(destino, fechaLimite);

	    assertEquals(1, resultado.size());
	    assertTrue(resultado.contains(circuito1));
	}
	
	//
	@Test
	void testBuscadorPorFechaDeSalidaDevuelveCircuitosConFechaPosterior() {
		when(circuito1.getFechaDeInicio()).thenReturn(LocalDateTime.of(2025, 1, 10, 0, 0));
	    BuscadorPorFechaDeSalida buscador = new BuscadorPorFechaDeSalida(List.of(circuito1));
	    List<CircuitoMaritimo> resultado = buscador.buscar(destino, fechaLimite);

	    assertEquals(1, resultado.size());
	    assertTrue(resultado.contains(circuito1));
	}
	
	@Test
	void testBuscadorPorFechaDeLlegadaDevuelveCircuitosConLlegadaAntesDeFechaLimite() {
	    BuscadorDeTrayectosStrategy buscador = new BuscadorPorFechaDeLlegada(List.of(circuito1), fechaLimite);

	    List<CircuitoMaritimo> resultado = buscador.buscar(destino, fechaLimite);

	    assertEquals(1, resultado.size());
	    assertTrue(resultado.contains(circuito1));
	}	
	
	
	
	@Test
	void testBuscadorAndDevuelveInterseccionDeResultados() {
	    BuscadorDeTrayectosStrategy buscadorAnd = 	new BuscadorAnd(criterio1, criterio2);
	    List<CircuitoMaritimo> resultado = buscadorAnd.buscar(destino, fechaLimite);

	    assertEquals(1, resultado.size());
	    assertTrue(resultado.contains(c2));
	}
	
	@Test
	void testBuscadorOrDevuelveUnionDeResultadosSinDuplicados() {
	    BuscadorDeTrayectosStrategy buscadorOr = new BuscadorOr(criterio1, criterio2);
	    List<CircuitoMaritimo> resultado = buscadorOr.buscar(destino, fechaLimite);

	    assertEquals(2, resultado.size());
	    assertTrue(resultado.contains(c1));
	    assertTrue(resultado.contains(c2));
	}
	
}
