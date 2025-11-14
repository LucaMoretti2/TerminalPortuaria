package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import actores.ActorPortuario;
import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Viaje;
import ordenes.Orden;
import serviciosDeContainer.Servicio;

class OrdenTestCase {

    @Mock
    private Container containerMock;

    @Mock
    private Viaje viajeMock;
    
    @Mock
    private Buque buqueMock;


    @Mock
    private Servicio servicio1;

    @Mock
    private Servicio servicio2;

    private Orden orden;
    
    @Mock
    private ActorPortuario responsableMock;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        orden = new Orden(containerMock, viajeMock,buqueMock) {
            @Override
            public double calcularCostoTotal() {
                return calcularCostoServicios();
            }

            @Override
            public ActorPortuario getResponsablePago() {
                return responsableMock;
            }
        };
    }

    // Test 1: agregar servicios y calcular el costo
    @Test
    void testCalcularCostoServicios() {
        when(servicio1.calcularCosto(containerMock)).thenReturn(1000.0);
        when(servicio2.calcularCosto(containerMock)).thenReturn(500.0);

        orden.agregarServicio(servicio1);
        orden.agregarServicio(servicio2);

        double resultado = orden.calcularCostoServicios();

        assertEquals(1500.0, resultado);

        verify(servicio1).calcularCosto(containerMock);
        verify(servicio2).calcularCosto(containerMock);
    }

    //Test 2: comprobar que la orden tiene container y viaje
    @Test
    void testGetContainerYViaje() {
        assertEquals(containerMock, orden.getContainer());
        assertEquals(viajeMock, orden.getViaje());
    }

    //Test 3: verificar que la fecha se inicializa automáticamente
    @Test
    void testFechaDeRegistroInicializada() {
        assertNotNull(orden.getFechaDeRegistro());
        assertTrue(orden.getFechaDeRegistro().isBefore(LocalDateTime.now().plusSeconds(1)));
    }

    // Test 4: calcular costo total (usando implementación simple)
    @Test
    void testCalcularCostoTotal() {
        when(servicio1.calcularCosto(containerMock)).thenReturn(200.0);
        orden.agregarServicio(servicio1);

        double total = orden.calcularCostoTotal();
        assertEquals(200.0, total);
    }

    // Test 5: obtener responsable de pago
    @Test
    void testGetResponsablePago() {
        when(responsableMock.getNombre()).thenReturn("Cliente Cordoba");

        ActorPortuario r = orden.getResponsablePago();

        assertEquals(responsableMock, r);
        assertEquals("Cliente Cordoba", r.getNombre());
    }
}
