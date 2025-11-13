package terminalPortuaria;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
import estadosBuque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;


class DepartingTestCase {
	
	private Departing departing;
	private TerminalGestionada terminalMock;
	private Buque buqueSpy;
	
	@BeforeEach
	void setUp() {
		departing = new Departing();
		terminalMock = mock(TerminalGestionada.class);
		buqueSpy = spy(new Buque("Libertad",60.0,departing));
	}
	
	
	@Test
	void actualizarPosisionYCambiarEstadoDeBuque() {
		
		departing.actualizarPosicion(buqueSpy, 60.0, terminalMock);
		
		verify(terminalMock).notificarShippers(contains("Libertad"), any());
		verify(buqueSpy).setEstado(any(Outbound.class));
		
	}

}