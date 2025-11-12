package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import containers.Container;
import empresaMaritima.Buque;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.Naviera;
import empresaMaritima.TerminalGestionada;
import empresaMaritima.Tramo;
import empresaMaritima.Viaje;

class ViajeTestCase {

    @Mock
    private CircuitoMaritimo circuitoMock;

    @Mock
    private Buque buqueMock;

    @Mock
    private Naviera navieraMock;

    @Mock
    private TerminalGestionada origenMock;

    @Mock
    private TerminalGestionada destinoMock;

    private Viaje viaje;
    private LocalDateTime fechaInicio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        fechaInicio = LocalDateTime.of(2025, 10, 10, 8, 0);
        viaje = new Viaje(1, fechaInicio, buqueMock, navieraMock, circuitoMock, origenMock, destinoMock);
    }

    // Test 1: duración total del viaje
    @Test
    void testGetDuracionTotalHoras() {
        when(circuitoMock.calcularTiempoTotalHoras()).thenReturn(48L);

        long resultado = viaje.getDuracionTotalHoras();

        assertEquals(48L, resultado);
        verify(circuitoMock).calcularTiempoTotalHoras();
    }

    //  Test 2: cálculo de fecha de arribo
    @Test
    void testCalcularFechaArribo() {
        when(circuitoMock.calcularDuracionHasta(destinoMock)).thenReturn(36L);

        LocalDateTime arribo = viaje.calcularFechaArribo(destinoMock);

        assertEquals(fechaInicio.plusHours(36), arribo);
        verify(circuitoMock).calcularDuracionHasta(destinoMock);
    }

    // Test 3: cálculo del costo total
    @Test
    void testCalcularCostoViaje() {
        when(circuitoMock.calcularPrecioTotal()).thenReturn(15000.50);

        double costo = viaje.calcularCostoViaje();

        assertEquals(15000.50, costo);
        verify(circuitoMock).calcularPrecioTotal();
    }

    // Test 4: obtener tramos
    @Test
    void testObtenerTramos() {
        List<Tramo> tramosFicticios = List.of(mock(Tramo.class), mock(Tramo.class));
        when(circuitoMock.getTramos()).thenReturn(tramosFicticios);

        List<Tramo> resultado = viaje.obtenerTramos();

        assertEquals(2, resultado.size());
        verify(circuitoMock).getTramos();
    }

    // Test 5: obtener contenedores del buque
    @Test
    void testGetContenedores() {
        Set<Container> contenedoresMock = Set.of(mock(Container.class));

        
        when(buqueMock.getCargas()).thenReturn(contenedoresMock);

        Set<Container> resultado = viaje.getContenedores();

        assertEquals(1, resultado.size());
    }
}

