package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.Consignee;
import empresaMaritima.OrdenDeImportacion;
import empresaMaritima.Viaje;
import serviciosDeContainer.Servicio;

class OrdenDeImportacionTestCase {

    @Mock
    private Container containerMock;

    @Mock
    private Viaje viajeMock;

    @Mock
    private Consignee consigneeMock;

    @Mock
    private Camion camionMock;

    @Mock
    private Chofer choferMock;

    @Mock
    private Buque buqueMock;

    private OrdenDeImportacion orden;

    private LocalDateTime llegada;
    private LocalDateTime retiro20h;
    private LocalDateTime retiro48h;
    private LocalDateTime retiro72h;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        llegada = LocalDateTime.of(2025, 11, 1, 8, 0);
        retiro20h = llegada.plusHours(20);   
        retiro48h = llegada.plusHours(48);   
        retiro72h = llegada.plusHours(72);   

        orden = new OrdenDeImportacion(containerMock, viajeMock, buqueMock, consigneeMock, llegada);
    }


    @Test
    void testConstructor() {
        assertEquals(containerMock, orden.getContainer());
        assertEquals(viajeMock, orden.getViaje());
        assertEquals(consigneeMock, orden.getConsignee());
        assertEquals(buqueMock, orden.getBuque());
        assertEquals(llegada, orden.getFechaLlegadaCarga());
    }

  
    @Test
    void testRegistrarRetiro() {
        orden.registrarRetiro(camionMock, choferMock, retiro20h);

        assertEquals(camionMock, orden.getCamion());
        assertEquals(choferMock, orden.getChofer());
        assertEquals(retiro20h, orden.getFechaRetiro());
    }

   
    @Test
    void testDiasExcedentesCero() {
        orden.registrarRetiro(camionMock, choferMock, retiro20h);
        assertEquals(0, orden.getDiasExcedentes());
    }

    @Test
    void testDiasExcedentesUno() {
        orden.registrarRetiro(camionMock, choferMock, retiro48h);
        assertEquals(1, orden.getDiasExcedentes());
    }

    @Test
    void testDiasExcedentesDos() {
        orden.registrarRetiro(camionMock, choferMock, retiro72h);
        assertEquals(2, orden.getDiasExcedentes());
    }

 
    @Test
    void testCalcularCostoTotal() {
        // 48 horas → 1 día excedente → 1000
        orden.registrarRetiro(camionMock, choferMock, retiro48h);

        Servicio s1 = mock(Servicio.class);
        Servicio s2 = mock(Servicio.class);

        when(s1.calcularCosto(containerMock)).thenReturn(2000.0);
        when(s2.calcularCosto(containerMock)).thenReturn(3000.0);

        orden.agregarServicio(s1);
        orden.agregarServicio(s2);

        when(viajeMock.calcularCostoViaje()).thenReturn(5000.0);

        double total = orden.calcularCostoTotal();

        // servicios: 2000 + 3000 = 5000
        // excedentes: 1 * 1000 = 1000
        // viaje: 5000
        assertEquals(11000.0, total);
    }

    @Test
    void testGetResponsablePago() {
        when(consigneeMock.getNombre()).thenReturn("Importadora S.A.");

        assertEquals("Importadora S.A.", orden.getResposablePago());
        verify(consigneeMock).getNombre();
    }


    @Test
    void testGetConsignee() {
        assertEquals(consigneeMock, orden.getConsignee());
    }
}
