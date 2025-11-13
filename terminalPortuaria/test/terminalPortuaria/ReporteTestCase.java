package terminalPortuaria;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import containers.Container;
import empresaMaritima.Buque;
import estadosBuque.EstadoDelBuque;
import reportes.ReporteAduanaVisitor;
import reportes.ReporteBuqueVisitor;
import reportes.ReporteMuelleVisitor;
//Como los reportes imprimen su salida directamente por consola, el test redirige temporalmente System.out 
//hacia un ByteArrayOutputStream para capturar y analizar el texto producido por cada visitor.
class ReporteTestCase {

    @Test
    void testTodosLosReportes() {

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));

  
        Container c1 = mock(Container.class);
        Container c2 = mock(Container.class);

        when(c1.getIDContainer()).thenReturn("HOLA123");
        when(c2.getIDContainer()).thenReturn("CHAU789");

        when(c1.getTipo()).thenReturn("Dry");
        when(c2.getTipo()).thenReturn("Reefer");


        EstadoDelBuque estadoMock = mock(EstadoDelBuque.class);


        Buque buque = new Buque("Titanic", 50.0, estadoMock);
        Set<Container> cargas = new HashSet<>();
        cargas.add(c1);
        cargas.add(c2);
        buque.setCargas(cargas);

// genera correctamente la estructura XML
        ReporteBuqueVisitor reporteBuque = new ReporteBuqueVisitor();
        reporteBuque.visit(buque);

        String outBuque = salida.toString();
        assertTrue(outBuque.contains("<report>"));
        assertTrue(outBuque.contains("<import>"));
        assertTrue(outBuque.contains("<item>HOLA123</item>"));
        assertTrue(outBuque.contains("<item>CHAU789</item>"));
        assertTrue(outBuque.contains("</report>"));

        salida.reset(); 

    //produce un documento HTML 
        ReporteAduanaVisitor reporteAduana = new ReporteAduanaVisitor();
        reporteAduana.visit(buque); 

        String outAduana = salida.toString();
        assertTrue(outAduana.contains("<html><body>"));
        assertTrue(outAduana.contains("Buque: Titanic"));
        assertTrue(outAduana.contains("<li>Dry - HOLA123</li>"));
        assertTrue(outAduana.contains("<li>Reefer - CHAU789</li>"));
        assertTrue(outAduana.contains("</body></html>"));

        salida.reset();
//muestra en texto plano
        ReporteMuelleVisitor reporteMuelle = new ReporteMuelleVisitor();
        reporteMuelle.visit(buque);

        String outMuelle = salida.toString();
        assertTrue(outMuelle.contains("=== Reporte Muelle ==="));
        assertTrue(outMuelle.contains("Buque: Titanic"));
        assertTrue(outMuelle.contains("Cantidad de contenedores: 2"));
        assertTrue(outMuelle.contains("======================="));
    }
}