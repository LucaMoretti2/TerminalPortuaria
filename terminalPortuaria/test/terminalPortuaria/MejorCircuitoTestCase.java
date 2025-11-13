package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.*;
import java.util.List;


import empresaMaritima.*;
import mejorCircuito.*;

class MejorCircuitoTestCase {

	private TerminalGestionada destino;
    private CircuitoMaritimo circuito1;
    private CircuitoMaritimo circuito2;
    private CircuitoMaritimo circuito3;
    
    
	
	@BeforeEach
	void setUp() {
		destino = mock(TerminalGestionada.class);
        circuito1 = mock(CircuitoMaritimo.class);
        circuito2 = mock(CircuitoMaritimo.class);
        circuito3 = mock(CircuitoMaritimo.class);

        
        when(circuito1.contieneTerminal(destino)).thenReturn(true);
        when(circuito2.contieneTerminal(destino)).thenReturn(true);
        when(circuito3.contieneTerminal(destino)).thenReturn(true);
	}
	

	@Test 
    void testSeleccionaCircuitoConMenorTiempo() {
        when(circuito1.calcularDuracionHasta(destino)).thenReturn(50L);
        when(circuito2.calcularDuracionHasta(destino)).thenReturn(30L);
        when(circuito3.calcularDuracionHasta(destino)).thenReturn(70L);

        BuscadorMejorCircuitoStrategy estrategia = new MenorTiempo();
        CircuitoMaritimo mejor = estrategia.seleccionarMejorCircuito(List.of(circuito1, circuito2, circuito3), destino);

        assertEquals(circuito2, mejor);
    }
	
	@Test
    void testSeleccionaCircuitoConMenorPrecio() {
        when(circuito1.calcularPrecioHasta(destino)).thenReturn(2000.0);
        when(circuito2.calcularPrecioHasta(destino)).thenReturn(1800.0);
        when(circuito3.calcularPrecioHasta(destino)).thenReturn(2500.0);

        BuscadorMejorCircuitoStrategy estrategia = new MenorPrecioTotal();
        CircuitoMaritimo mejor = estrategia.seleccionarMejorCircuito(List.of(circuito1, circuito2, circuito3), destino);

        assertEquals(circuito2, mejor);
    }
	
	@Test
    void testSeleccionaCircuitoConMenorCantidadDeTerminales() {
        when(circuito1.cantidadDeTerminalesIntermedias(destino)).thenReturn(5);
        when(circuito2.cantidadDeTerminalesIntermedias(destino)).thenReturn(2);
        when(circuito3.cantidadDeTerminalesIntermedias(destino)).thenReturn(4);

        BuscadorMejorCircuitoStrategy estrategia = new MenorCantidadDeTerminalesIntermedias();
        CircuitoMaritimo mejor = estrategia.seleccionarMejorCircuito(List.of(circuito1, circuito2, circuito3), destino);

        assertEquals(circuito2, mejor);
    }

}