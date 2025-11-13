
package terminalPortuaria;


import empresaMaritima.Buque;
import empresaMaritima.TerminalGestionada;
import estadosBuque.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class ArrivedTestCase {

    private Arrived arrived;
    private TerminalGestionada terminalMock;
    private Buque buqueSpy;
    private Buque buqueSpy2;

    @BeforeEach
    void setUp() {
        arrived = new Arrived();
        terminalMock = mock(TerminalGestionada.class);
        buqueSpy = spy(new Buque("Santa Filomena",10.0,arrived));
        buqueSpy2 = spy(new Buque("El acorazado",50.0,arrived));
    }

    @Test
    void testGpsEnCeroCambiaAEstadoWorking() {
      
        arrived.actualizarPosicion(buqueSpy, 0.0, terminalMock);

        // Debe iniciar trabajo y cambiar el estado a Working
        verify(terminalMock).inicioTrabajo();
        verify(buqueSpy).setEstado(any(Working.class));
    }

    @Test
    void testGpsDistintoDeCeroNoCambiaEstado() {
       
        arrived.actualizarPosicion(buqueSpy, 25.0, terminalMock);

        verifyNoInteractions(terminalMock);
        verify(buqueSpy2, never()).setEstado(any(Working.class));
    }
}