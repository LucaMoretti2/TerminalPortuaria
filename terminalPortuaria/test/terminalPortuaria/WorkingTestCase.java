package terminalPortuaria;


import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
import estadosBuque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class WorkingTestCase {

    private Working working;
    private TerminalGestionada terminalMock;
    private Buque buqueSpy;

    @BeforeEach
    void setUp() {
        working = new Working();
        terminalMock = mock(TerminalGestionada.class);
        buqueSpy = spy(new Buque("Saint Barbara",0.0,working));
        
    }
    
    @Test
    void actualizarPosicionYCambiarADeparting() {
    	working.actualizarPosicion(buqueSpy, 10.0, terminalMock);
    	
    	verify(terminalMock).ordenDeparting();
    	verify(buqueSpy).setEstado(any (Departing.class));
    }
}
