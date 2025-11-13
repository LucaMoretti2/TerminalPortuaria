package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import buscador.BuscadorPorFechaDeLlegada;
import empresaMaritima.Buque;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.TerminalGestionada;
import estadosBuque.Outbound;

class OutboundTestCase {

	    private Buque buqueMock;
	    private CircuitoMaritimo circuito;
	    private TerminalGestionada terminalMock;
	    private Outbound outbound;
	    private LocalDateTime llegada;

	    @BeforeEach
	    void setUp() {
	        buqueMock = new Buque("Santa Sofia",75.6, outbound);
	        circuito = new CircuitoMaritimo("Ruta Sur");
	        List<CircuitoMaritimo> lista = new ArrayList<>();
	        lista.add(circuito);
	        llegada = LocalDateTime.of(2025, 11, 1, 8, 0);
	        terminalMock = new TerminalGestionada(0, "Retiro",new BuscadorPorFechaDeLlegada(lista,llegada));
	        outbound = new Outbound();
	    }

	    @Test
	    void testGpsEnCeroYCambioDeFase() {
	        //Actualizo la posicion del gps 
	        outbound.actualizarPosicion(buqueMock, 30.0, terminalMock);
	        //Verifico el cambio de fase...
	        assertEquals("Inbound",buqueMock.getEstadoBuque());

	    }

	    @Test
	    void testGetNombreEstado() {
	        assertEquals("Outbound",outbound.nombreEstado());
	    }
	}