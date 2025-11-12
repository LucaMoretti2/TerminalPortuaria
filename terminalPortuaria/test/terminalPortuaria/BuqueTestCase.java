package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Observer;
import estadosBuque.EstadoDelBuque;

class BuqueTestCase {

    @Mock
    private EstadoDelBuque estadoMock;

    @Mock
    private Container containerMock;

    @Mock
    private Observer observadorMock;

    private Buque buque;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        buque = new Buque("Unqui", 12.5, estadoMock);
        buque.setCargas(new HashSet<>()); 
    }

    //Test 1: constructor y getters
    @Test
    void testConstructorYGetters() {
        assertEquals("Unqui", buque.getNombre());
        assertEquals(12.5, buque.getGps());
        assertEquals(estadoMock, buque.getEstadoBuque());
    }

    //Test 2: agregar container
    @Test
    void testAddContainer() {
        buque.addContainer(containerMock);
        assertTrue(buque.getCargas().contains(containerMock));
    }

    //Test 3: cambiar estado
    @Test
    void testSetEstado() {
        EstadoDelBuque nuevoEstadoMock = mock(EstadoDelBuque.class);

        buque.setEstado(nuevoEstadoMock);

        assertEquals(nuevoEstadoMock, buque.getEstadoBuque());
    }

    // Test 4: delegación al patrón State
    @Test
    void testActualizarPosicionDelegadaAlEstado() {
        buque.actualizarPosicion(99.9);
        verify(estadoMock).actualizarPosicion(buque, 99.9);
    }

    @Test
    void testIniciarTrabajoDelegadoAlEstado() {
        buque.iniciarTrabajo();
        verify(estadoMock).iniciarTrabajo(buque);
    }

    @Test
    void testFinalizarTrabajoDelegadoAlEstado() {
        buque.finalizarTrabajo();
        verify(estadoMock).finalizarTrabajo(buque);
    }

    @Test
    void testNotificarDelegadoAlEstado() {
        buque.notificar();
        verify(estadoMock).notificar();
    }

    @Test
    void testRealizarPagosDelegadoAlEstado() {
        buque.realizarPagoNecesarios();
        verify(estadoMock).realizarPagosNecesarios();
    }

    //Test 5: patrón Observer - agregar y notificar
    @Test
    void testAgregarYNotificarObservadores() {
        buque.agregarObservador(observadorMock);

        buque.notificarEvento();

        verify(observadorMock).actualizarEvento(buque);
    }

    // Test 6: eliminar observador
    @Test
    void testEliminarObservador() {
        buque.agregarObservador(observadorMock);
        buque.eliminarObservador(observadorMock);

        buque.notificarEvento();

        verify(observadorMock, never()).actualizarEvento(buque);
    }
}