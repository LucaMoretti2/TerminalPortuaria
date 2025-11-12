package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.EmpresaTransportista;

class ChoferTestCase {

    private Chofer choferMock;
    private Camion camionMock;
    private EmpresaTransportista empresaMock;


    @BeforeEach
    void setUp() {
    	   choferMock  = new Chofer("Luca Moretti");
           choferMock.asignarCamion(camionMock);
           empresaMock = new EmpresaTransportista("TransporteQuilmes"); 

           camionMock = new Camion("CamionBSA", choferMock,empresaMock);
        
    }

    @Test
    void testGetNombre() {
        assertEquals("Luca Moretti", choferMock.getNombre());
    }

    @Test
    void testAsignarCamion() {
        choferMock.asignarCamion(camionMock);
        assertEquals(camionMock, choferMock.getCamionAsignado());
    }

   
}