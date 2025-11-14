package playground;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import actores.Consignee;
import actores.Shipper;
import buscador.BuscadorDeTrayectosStrategy;
import buscador.BuscadorPorFechaDeLlegada;
import buscador.BuscadorPorFechaDeSalida;
import containers.BillOfLading;
import containers.Dry;
import containers.Reefer;
import containers.Tanque;
import empresaMaritima.Buque;
import empresaMaritima.Camion;
import empresaMaritima.Chofer;
import empresaMaritima.CircuitoMaritimo;
import empresaMaritima.EmpresaTransportista;
import empresaMaritima.Factura;
import empresaMaritima.Naviera;
import empresaMaritima.TerminalGestionada;
import empresaMaritima.Tramo;
import empresaMaritima.Viaje;
import estadosBuque.Arrived;
import estadosBuque.Departing;
import estadosBuque.Inbound;
import estadosBuque.Outbound;
import estadosBuque.Working;
import mejorCircuito.MenorTiempo;
import ordenes.OrdenDeExportacion;
import ordenes.OrdenDeImportacion;
import serviciosDeContainer.Almacenamiento;
import serviciosDeContainer.Electricidad;
import serviciosDeContainer.Lavado;
import serviciosDeContainer.Servicio;

public class Playground {

    public static void main(String[] args) {

        System.out.println("========== PLAYGROUND TERMINAL PORTUARIA ==========");

        //creacion de la terminal
        BuscadorDeTrayectosStrategy motorDeBusqueda = new BuscadorPorFechaDeLlegada(null,null);
        TerminalGestionada uruguay = new TerminalGestionada(1000.0, "Uruguay", motorDeBusqueda);
        TerminalGestionada buenosAires = new TerminalGestionada(20.0, "Puerto Buenos Aires", motorDeBusqueda);
        
        System.out.println("Terminal creada en posición 1000.0");
       
        //creacion de un tramo
        long dosHorasEnMilis = 2L * 60 * 60 * 1000;
        Date fecha = new Date(dosHorasEnMilis);
        Tramo tramo1 = new Tramo(2000.0,fecha , uruguay, buenosAires);
        
        //creacion de circuito maritimo
        CircuitoMaritimo circuito = new CircuitoMaritimo("Rio de la Plata");
        circuito.agregarTramo(tramo1);

        //creacion de un buque
        Buque buque = new Buque("Malvinas", 35.8, null);
        System.out.println("Buque creado: " + buque.getNombre());

        //Creacion de contenedores
        LocalDateTime ingreso = LocalDateTime.of(2025, 1, 1, 8, 0);
        LocalDateTime retiro = LocalDateTime.of(2025, 1, 3, 10, 0);
       
        Dry dry = new Dry(2, 2, 6, 4000, "dry123", ingreso, retiro);
        Reefer reefer = new Reefer(2, 2, 6, 5000, "ref123", 12.5, ingreso, retiro);
        Tanque tanque = new Tanque(2, 2, 6, 7000, "tank123", ingreso, retiro, "nafta", 3000);
        
        //agregar los bls al Dry
        Consignee consignee = new Consignee("Luca Moretti", 123);
        Consignee consignee2 = new Consignee("Hospital Algerich", 999);
        Consignee consignee3 = new Consignee("YPF", 555);
        BillOfLading bl1= new BillOfLading("rtx 5080",0.5, consignee);
        BillOfLading bl2= new BillOfLading("intel i9",0.5, consignee);
        BillOfLading bl3= new BillOfLading("nafta",1000.0, consignee3);
        BillOfLading bl4= new BillOfLading("vacunas",100.0, consignee2);
        
        dry.addBl(bl1);
        dry.addBl(bl2);
        tanque.setBl(bl3);
        reefer.setBl(bl4);
        System.out.println("=== Contenedores creados ===");
        System.out.println(" - Dry: " + dry.getBls());
        System.out.println(" - Reefer: " + reefer.getBl());
        System.out.println(" - Tanque: " + tanque.getBl() );
        System.out.println("Dry contiene BLs con peso total: " + dry.getPesoTotalDeBLs() + " kg");
        //agregar los  contenedores al buque
        buque.addContainer(dry);
        buque.addContainer(reefer);
        buque.addContainer(tanque);

        System.out.println("El buque Malvinas tiene ahora " + buque.getCargas().size() + " contenedores");
        System.out.println("===========================");
        //conectar un  Reefer
        reefer.conectar(LocalDateTime.of(2025, 1, 1, 9, 0));
        reefer.desconectar(LocalDateTime.of(2025, 1, 1, 21, 0));

        System.out.println("Reefer estuvo conectado por " + reefer.getHorasConectado() + " horas");
        System.out.println("Consumo por hora: " + reefer.getConsumoPorHora() + " kwh");

        //agregar los servicios
        Servicio lavado = new Lavado(300, 10, 500, 300); 
        Servicio almacenamiento = new Almacenamiento(200);

        dry.addServicio(lavado);
        reefer.addServicio(almacenamiento);

        System.out.println("Costo total de servicio  dry: $" + dry.costoTotalDeServicios());
        System.out.println("Costo total de servicio reefer: $" + reefer.costoTotalDeServicios());


        // simulacion de arribo y partida
        System.out.println("=== Simulacion de arribo y partida ===");
        LocalDateTime fecha2 = LocalDateTime.of(2025, 1, 2, 6, 0);
        Naviera colonia = new Naviera("Colonia Express");
        TerminalGestionada rosario = new TerminalGestionada(1000, "Rio de la Plata", motorDeBusqueda);
        Viaje viaje1 = new Viaje(123, fecha2, buque,colonia, circuito, uruguay, rosario );
        viaje1.calcularFechaArribo(rosario);       
        System.out.println("Buque arribo: " + viaje1.getArribo());
        System.out.println("Buque partida: " + viaje1.getPartida());
        System.out.println("======================================");
        
        //hacer una orden de importacion y exportacion
        
        Shipper shipper1 = new Shipper("Camila Arena", 111);
        Shipper shipper2 = new Shipper("Marianela Carbone", 222);
        Shipper shipper3 = new Shipper("Diego Cano", 333);
        EmpresaTransportista empresa1 = new EmpresaTransportista ("Arcos");
        Chofer chofer1 = new Chofer("Jorge");
        Camion camion1 = new Camion("123",chofer1,empresa1);
        OrdenDeExportacion ordenShipper = new OrdenDeExportacion(dry, viaje1, buque,shipper1, camion1, chofer1, LocalDateTime.now());
        OrdenDeImportacion ordenConsignee = new OrdenDeImportacion(reefer,viaje1,buque,consignee, LocalDateTime.now());

        System.out.println("=== Ordenes registradas ===");
        System.out.println("Exportación: " + ordenShipper);
        System.out.println("Importación: " + ordenConsignee);
        System.out.println("===========================");

        // ver  fases de buque
        Outbound outbound = new Outbound();
        Inbound inbound = new Inbound();
        Arrived arrived = new Arrived();
        Working working = new Working();
        Departing departing = new Departing();
        
        System.out.println("=== Simulacion de estados ===");
        buenosAires.registrarActorPortuario(consignee);
        
        buque.setEstado(outbound);

        outbound.actualizarPosicion(buque,40.0,buenosAires);
 
        inbound.actualizarPosicion(buque,0.0,buenosAires);

        arrived.actualizarPosicion(buque, 0.0, buenosAires);

        working.actualizarPosicion(buque, 0.0, buenosAires);

        departing.actualizarPosicion(buque,60.0,buenosAires);
        System.out.println("=============================");
        
       //creacion de factura
     
        Factura facturaShipper = ordenShipper.generarFactura();
        Factura facturaConsignee = ordenConsignee.generarFactura();
        System.out.println("=== Facturas generadas ===");
        System.out.println("Factura Exportación $" + facturaShipper.getTotal());
        System.out.println("Factura Importación  $" + facturaConsignee.getTotal());
    
        System.out.println("===============================");
        //envio por mail 
        facturaShipper.enviarPorMail();
        facturaConsignee.enviarPorMail();
        
        System.out.println("==========================");
        System.out.println("=== Busqueda de trayectos ===");
  

        //busqueda de rutas
        Date duracion2Horas = new Date(2L * 60 * 60 * 1000);
        Date duracion5Horas = new Date(5L * 60 * 60 * 1000);
        Date duracion10Horas = new Date(10L * 60 * 60 * 1000);
        
        Tramo tramo2 = new Tramo(2000.0, duracion2Horas, uruguay, buenosAires);
        Tramo tramo3 = new Tramo(3500.0, duracion5Horas, uruguay, rosario);
        Tramo tramo4 = new Tramo(4500.0, duracion10Horas, uruguay, rosario);
        
        CircuitoMaritimo circuito2 = new CircuitoMaritimo("Rutas Uruguay");
        circuito2.agregarTramo(tramo2);
        circuito2.agregarTramo(tramo3);
        circuito2.agregarTramo(tramo4);
        uruguay.registrarCircuitoMaritimo(circuito2);
        MenorTiempo criterio = new MenorTiempo();
        uruguay.setCriterioSeleccion(criterio);
        CircuitoMaritimo mejorCircuito = uruguay.obtenerMejorCircuito(rosario);
        if (mejorCircuito != null) {
            System.out.println("Mejor circuito encontrado: " + mejorCircuito.getNombre());
        } else {
            System.out.println("No hay circuitos disponibles.");
        }
        System.out.println("Criterio utilizado: " + uruguay.getCriterioSeleccion().getClass().getSimpleName());
        System.out.println("===============================");

        System.out.println("========== FIN  ==========");
    }

}