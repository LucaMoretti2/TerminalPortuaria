package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.EmpresaTransportista;

class CamionTestCase {

    private Camion camion;
    private Chofer choferMock;
    private EmpresaTransportista empresaMock;

    @BeforeEach
    void setUp() {
        choferMock = new Chofer("Luca Moretti");
        choferMock.asignarCamion(camion);
        empresaMock = new EmpresaTransportista("TransporteQuilmes"); 

        camion = new Camion("CamionBSA", choferMock,empresaMock);
 
    }

    @Test
    void testGetIdCamion() {
        assertEquals("CamionBSA", camion.getIdCamion());
    }

    @Test
    void testGetEmpresa() {
        assertEquals(empresaMock, camion.getEmpresa());
    }

    @Test
    void testChoferAsociado() {
        assertEquals(choferMock, camion.getChofer());
    }
}
