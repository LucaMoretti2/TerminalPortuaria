package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import actores.ActorPortuario;
import actores.Shipper;
import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.Viaje;
import ordenes.OrdenDeExportacion;
import serviciosDeContainer.Servicio;
// Esta clase depende de Container, Viaje, Shipper, Camion, Chofer y una lista de Servicios. 
//Se utiliza mocks para aislar completamente esta unidad lógica.
class OrdenDeExportacionTestCase {

    @Mock
    private Container containerMock;

    @Mock
    private Viaje viajeMock;
    
    @Mock
    private Buque buqueMock;

    @Mock
    private Shipper shipperMock;

    @Mock
    private Camion camionMock;

    @Mock
    private Chofer choferMock;

    private OrdenDeExportacion orden;
    private LocalDateTime turno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        turno = LocalDateTime.of(2025, 11, 12, 10, 0);
        orden = new OrdenDeExportacion(containerMock, viajeMock,buqueMock, shipperMock, camionMock, choferMock, turno);
    }

    //Test 1: Constructor y getters básicos
    @Test
    void testConstructorYDatosBasicos() {
        assertEquals(containerMock, orden.getContainer());
        assertEquals(viajeMock, orden.getViaje());
        assertEquals(shipperMock, orden.getShipper());
        assertEquals(camionMock, orden.getCamion());
        assertEquals(choferMock, orden.getChofer());
        assertEquals(turno, orden.getTurnoAsignado());
    }

    //Test 2: Calcular costo total (usa los servicios)
    @Test
    void testCalcularCostoTotal() {
        Servicio servicio1 = mock(Servicio.class);
        Servicio servicio2 = mock(Servicio.class);

    
        when(servicio1.calcularCosto(containerMock)).thenReturn(1000.0);
        when(servicio2.calcularCosto(containerMock)).thenReturn(500.0);

        orden.agregarServicio(servicio1);
        orden.agregarServicio(servicio2);

        double total = orden.calcularCostoTotal();

        assertEquals(1500.0, total); // 1000 + 500
        verify(servicio1).calcularCosto(containerMock);
        verify(servicio2).calcularCosto(containerMock);
    }

    //Test 3: Responsable de pago (usa el shipper)
    @Test
    void testGetResponsablePago() {
        when(shipperMock.getNombre()).thenReturn("Empresa Exportadora S.A.");

        ActorPortuario responsable = orden.getResponsablePago();

        assertEquals(shipperMock, responsable);
        assertEquals("Empresa Exportadora S.A.", responsable.getNombre());
        verify(shipperMock).getNombre();
    }
    //sTest 4: Calcular costo total sin servicios
    @Test
    void testCalcularCostoTotalSinServicios() {
        double total = orden.calcularCostoTotal();
        assertEquals(0.0, total);
    }
}
