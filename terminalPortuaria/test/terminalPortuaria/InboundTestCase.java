package terminalPortuaria;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
import estadosBuque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class InboundTestCase {

    private Inbound inbound;
    private TerminalGestionada terminalMock;
    private Buque buqueSpy;
    private Buque buqueSpy2;

    @BeforeEach
    void setUp() {
        inbound = new Inbound();
        terminalMock = mock(TerminalGestionada.class);
        buqueSpy = spy(new Buque("Evergreen", 10.0, inbound));
        buqueSpy2 = spy(new Buque("XianXiao",100.0,inbound));
    }

    @Test
    void testGpsEnCeroCambiaAEstadoArrived() {
        
        inbound.actualizarPosicion(buqueSpy, 0.0, terminalMock);
        
        verify(terminalMock).notificarConsignees("El buque llego a destino", buqueSpy);
        verify(buqueSpy).setEstado(any(Arrived.class));
    }

    @Test
    void testGpsDistintoDeCeroNoCambiaEstado() {

        inbound.actualizarPosicion(buqueSpy2, 25.0, terminalMock);

        verifyNoInteractions(terminalMock);
        verify(buqueSpy, never()).setEstado(any(Arrived.class));
    }
}