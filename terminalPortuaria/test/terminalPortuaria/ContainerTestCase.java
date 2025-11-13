package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actores.Consignee;
import containers.BillOfLading;
import containers.Dry;
import containers.Reefer;
import containers.Tanque;
import serviciosDeContainer.Servicio;

class ContainerTestCase {

	Servicio servicioMock;
    LocalDateTime ingreso;
    LocalDateTime retiro;
	
	@BeforeEach
	void setUp() {
		servicioMock = mock(Servicio.class);
        ingreso = LocalDateTime.of(2025, 1, 1, 10, 0);
        retiro = LocalDateTime.of(2025, 1, 2, 10, 0);
	}
//Test 1: Agregar BLs y calcular el peso total de los Bill of Lading
	@Test
	void testAgregarBillOfLadingYCalcularPesoTotal() {
	    Dry dry = new Dry(2, 3, 6, 5000, "1234", ingreso, retiro);

	    Consignee consignee = mock(Consignee.class);
	    
	    BillOfLading bl1 = new BillOfLading("ProductoA", 1000, consignee);
	    BillOfLading bl2 = new BillOfLading("ProductoB", 2000, consignee);

	    dry.addBl(bl1);
	    dry.addBl(bl2);

	    assertEquals(2, dry.getBls().size());
	    assertEquals(3000, dry.getPesoTotalDeBLs());
	    assertFalse(dry.esDesconsolidado());
	}
	
//Test 2: Cálculo de horas conectado en reefer	
	@Test
	void testReeferCalculoDeHorasConectado() {
	    Reefer reefer = new Reefer(2, 3, 6, 8000, "1234", 10.5, ingreso, retiro);

	    reefer.conectar(LocalDateTime.of(2025, 1, 1, 8, 0));
	    reefer.desconectar(LocalDateTime.of(2025, 1, 1, 20, 0));

	    assertEquals(12, reefer.getHorasConectado());
	    assertEquals(10.5, reefer.getConsumoPorHora());
	}
// Test 3: Verificación de atributos propios del contenedor Tanque
	@Test
	void testTanqueAtributosYRevision() {
	    Tanque tanque = new Tanque(2, 3, 6, 10000, "1234", ingreso, retiro, "Combustible", 2500);

	    assertEquals("Combustible", tanque.getTipoDeLiquido());
	    assertEquals(2500, tanque.getCapacidadDeLitros());
	}
	
// Test 4: Cálculo del costo total de servicios aplicados al contenedor
	@Test
	void testCostoTotalDeServicios() {
	    Dry container = new Dry(2, 3, 6, 5000, "1234", ingreso, retiro);

	    Servicio s1 = mock(Servicio.class);
	    Servicio s2 = mock(Servicio.class);

	    s1.precioFijo = 100;
	    s2.precioFijo = 200;

	    container.addServicio(s1);
	    container.addServicio(s2);

	    double total = container.costoTotalDeServicios();
	    assertEquals(300, total);
	}
	
}
