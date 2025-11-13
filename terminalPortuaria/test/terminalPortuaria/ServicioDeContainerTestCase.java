package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import containers.Container;
import containers.Reefer;
import serviciosDeContainer.Almacenamiento;
import serviciosDeContainer.Electricidad;
import serviciosDeContainer.Lavado;
import serviciosDeContainer.Pesado;
public class ServicioDeContainerTestCase {

    @Test
    void testTodosLosServicios() {
        // --- Mock base de Container
        Container container = mock(Container.class);
        when(container.getAncho()).thenReturn(3.0);
        when(container.getLargo()).thenReturn(3.0);
        when(container.getAlto()).thenReturn(3.0);
        when(container.getFechaDeIngreso()).thenReturn(LocalDateTime.of(2025, 11, 1, 8, 0));
        when(container.getFechaDeRetiro()).thenReturn(LocalDateTime.of(2025, 11, 4, 8, 0));

        // -Almacenamiento
        Almacenamiento almacenamiento = new Almacenamiento(1000.0);
        double costoAlmacenamiento = almacenamiento.calcularCosto(container);
        assertEquals(4000.0, costoAlmacenamiento, "Almacenamiento: 3 días * 1000 + base");

        //-Lavado
        Lavado lavado = new Lavado(500.0, 0, 1500.0, 800.0);
        double costoLavado = lavado.calcularCosto(container);
        assertEquals(1300.0, costoLavado, "Lavado: base 500 + 800 (volumen <70)");

        //-Electricidad
        Reefer reefer = mock(Reefer.class);
        when(reefer.getHorasConectado()).thenReturn(10.0);
        when(reefer.getConsumoPorHora()).thenReturn(2.0);
        Electricidad electricidad = new Electricidad(100.0, 5.0);
        double costoElectricidad = electricidad.calcularCosto(reefer);
        assertEquals(100.0, electricidad.calcularCostoVariable(reefer));
        assertEquals(200.0, costoElectricidad, "Electricidad: base 100 + 200 variable"); //ACA ARREGLAR

        //-Pesado
        Pesado pesado = new Pesado(700.0);
        double costoPesado = pesado.calcularCosto(container);
        assertEquals(700.0, costoPesado, "Pesado: solo base 700");

        //-Total combinado
        double costoTotalServicios = costoAlmacenamiento + costoLavado + costoElectricidad + costoPesado;
        assertEquals(6200.0, costoTotalServicios, 0.001, "Suma total de todos los servicios");
    }
}