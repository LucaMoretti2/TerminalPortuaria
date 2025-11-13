package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import actores.ActorPortuario;
import containers.Container;
import empresaMaritima.Factura;
import serviciosDeContainer.Servicio;
//se usan mocks para los servicios y el container:
class FacturaTestCase {

	@BeforeEach
	void setUp() throws Exception {
	}

//Factura sume correctamente los costos individuales de cada servicio.
	@Test
	void testCalcularTotalFactura() {
	    
	    ActorPortuario responsable = mock(ActorPortuario.class);
	    Servicio servicio1 = mock(Servicio.class);
	    Servicio servicio2 = mock(Servicio.class);
	    Container container = mock(Container.class);
	    
	    when(servicio1.calcularCosto(container)).thenReturn(100.0);
	    when(servicio2.calcularCosto(container)).thenReturn(200.0);

	    List<Servicio> servicios = List.of(servicio1, servicio2);

	    Factura factura = new Factura(LocalDate.now(), responsable, servicios, container);

	    assertEquals(300.0, factura.getTotal());
	}
	
//si la lista de servicios está vacía, el total calculado sea 0.
	@Test
	void testFacturaSinServiciosTieneTotalCero() {
	    ActorPortuario responsable = mock(ActorPortuario.class);
	    Container container = mock(Container.class);
	    List<Servicio> servicios = new ArrayList<>();

	    Factura factura = new Factura(LocalDate.now(), responsable, servicios, container);

	    assertEquals(0.0, factura.getTotal());
	}

}
