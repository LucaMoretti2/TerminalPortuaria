package terminalPortuaria;

import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
import estadosBuque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class OutboundTestCase{

    private Outbound outbound;
    private TerminalGestionada terminalMock;
    private Buque buquespy;

    @BeforeEach
    void setUp() {
        outbound = new Outbound();
        terminalMock = mock(TerminalGestionada.class);
       
       buquespy = spy(new Buque("Santa Sofia", 30.0, outbound));
    }

    @Test
    void testGpsEnCeroYCambioDeFase() {

        // Actualizo la posicion del buque a Cero
        outbound.actualizarPosicion(buquespy, 0.0, terminalMock);
        // Verifico que se realicen las tareas de fase y que cambie de estado despues de hacer las tareas.
        verify(terminalMock).notificarConsignees("El buque esta pronto al arrivo", eq(buquespy));
        verify(buquespy).setEstado(any(Inbound.class));
    }

    @Test
    void testGpsMayorA50NoCambiaEstado() {
    	// Actualizo la posicion a una localizacion alejada de la terminal 
        outbound.actualizarPosicion(buquespy, 100.0, terminalMock);
        // Verifico que no se lleven a cabo las tareas ya que no se cumple la condicion de cambio de estado. 
        verifyNoInteractions(terminalMock);
        // El comando never() verifica que no se producen llamadas al metodo de la clase correspondiente <nunca se invocó el mock>
        // El any hace que se acepte cualquier valor de un tipo especifico, no requiere argumentos de valores exactos. <cualquier cosa le viene bien>
        verify(buquespy, never()).setEstado(any(Inbound.class));
    }
}

