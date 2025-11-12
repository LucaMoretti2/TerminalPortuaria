package terminalPortuaria;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import containers.Container;
import empresaMaritima.Buque;
import estadosBuque.EstadoDelBuque;
import reportes.ReporteVisitor;

class BuqueTestCase {

    private Buque buque;
    private EstadoDelBuque estadoMock;
    private Container containerMock1;
    private Container containerMock2;
    private ReporteVisitor visitorMock;

    @BeforeEach
    void setUp() {
        estadoMock = mock(EstadoDelBuque.class);
        containerMock1 = mock(Container.class);
        containerMock2 = mock(Container.class);
        visitorMock = mock(ReporteVisitor.class);

        buque = new Buque("Titanic", 45.6, estadoMock);
        Set<Container> cargas = new HashSet<>();
        cargas.add(containerMock1);
        cargas.add(containerMock2);
        buque.setCargas(cargas);
    }

    //Test 1: getters básicos
    @Test
    void testGettersBasicos() {
        assertEquals("Titanic", buque.getNombre());
        assertEquals(45.6, buque.getGps());
        assertEquals(estadoMock, buque.getEstadoBuque());
        assertTrue(buque.getCargas().contains(containerMock1));
    }

    // Test 2: setEstado cambia correctamente el estado
    @Test
    void testSetEstadoCambiaElEstado() {
        EstadoDelBuque nuevoEstado = mock(EstadoDelBuque.class);
        buque.setEstado(nuevoEstado);
        assertEquals(nuevoEstado, buque.getEstadoBuque());
    }

    // Test 3: agregar container
    @Test
    void testAddContainer() {
        Container nuevoContainer = mock(Container.class);
        buque.addContainer(nuevoContainer);
        assertTrue(buque.getCargas().contains(nuevoContainer));
    }

    // Test 4: actualizarPosicion delega en el estado- Patron State
    @Test
    void testActualizarPosicionLlamaAlEstado() {
        buque.actualizarPosicion(buque, 99.9);
        verify(estadoMock).actualizarPosicion(eq(buque), eq(99.9), any());
    }

    // Test 5: patrón Visitor — acepta el visitor y propaga a containers
    @Test
    void testAcceptLlamaVisitorEnBuqueYContainers() {
        buque.accept(visitorMock);

        verify(visitorMock).visit(buque);

        verify(containerMock1).accept(visitorMock);
        verify(containerMock2).accept(visitorMock);
    }

    //  Test 6: cambiar y obtener cargas
    @Test
    void testSetYCargas() {
        Set<Container> nuevos = new HashSet<>();
        Container c = mock(Container.class);
        nuevos.add(c);
        buque.setCargas(nuevos);
        assertEquals(1, buque.getCargas().size());
        assertTrue(buque.getCargas().contains(c));
    }
}