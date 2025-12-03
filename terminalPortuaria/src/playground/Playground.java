package playground;

import java.time.LocalDateTime;
import java.util.Date;

import actores.Consignee;
import actores.Shipper;
import buscador.BuscadorDeTrayectosStrategy;
import buscador.BuscadorPorFechaDeLlegada;
import containers.BillOfLading;
import containers.Dry;
import containers.Reefer;
import containers.Tanque;
import containers.TipoCarga;
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
import reportes.ReporteAduanaVisitor;
import reportes.ReporteBuqueVisitor;
import reportes.ReporteMuelleVisitor;
import serviciosDeContainer.Almacenamiento;
import serviciosDeContainer.Electricidad;
import serviciosDeContainer.Lavado;
import serviciosDeContainer.Servicio;

public class Playground {

    public static void main(String[] args) {

        System.out.println("========== PLAYGROUND TERMINAL PORTUARIA ==========");

        // === Terminales ===
        BuscadorDeTrayectosStrategy motorDeBusqueda = new BuscadorPorFechaDeLlegada(null, null);
        TerminalGestionada uruguay = new TerminalGestionada(1000.0, "Uruguay", motorDeBusqueda);
        TerminalGestionada buenosAires = new TerminalGestionada(20.0, "Puerto Buenos Aires", motorDeBusqueda);

        
        // === Tramo y circuito inicial ===
        long dosHorasEnMilis = 2L * 60 * 60 * 1000;
        Date fecha = new Date(dosHorasEnMilis);
        Tramo tramo1 = new Tramo(2000.0, fecha, uruguay, buenosAires);

        CircuitoMaritimo circuito = new CircuitoMaritimo("Rio de la Plata");
        circuito.agregarTramo(tramo1);

        // === Buque ===
        Buque buque = new Buque("Malvinas", 35.8, null);
        System.out.println("Buque creado: " + buque.getNombre());


        // === Contenedores ===
        LocalDateTime ingreso = LocalDateTime.of(2025, 1, 1, 8, 0);
        LocalDateTime retiro = LocalDateTime.of(2025, 1, 3, 10, 0);

        Dry dry = new Dry(2, 2, 6, 4000, "dry123", ingreso, retiro);
        Reefer reefer = new Reefer(2, 2, 6, 5000, "ref123", 12.5, ingreso, retiro);
        Tanque tanque = new Tanque(2, 2, 6, 7000, "tank123", ingreso, retiro, "nafta", 3000);

        // === BLs ===
        Consignee consignee = new Consignee("Luca Moretti", 123);
        Consignee consignee2 = new Consignee("Hospital Algerich", 999);
        Consignee consignee3 = new Consignee("YPF", 555);

        BillOfLading bl1 = new BillOfLading("rtx 5080", 0.5, consignee, TipoCarga.SECA);
        BillOfLading bl2 = new BillOfLading("intel i9", 0.5, consignee, TipoCarga.SECA);
        BillOfLading bl3 = new BillOfLading("nafta", 1000.0, consignee3, TipoCarga.LIQUIDA);
        BillOfLading bl4 = new BillOfLading("vacunas", 100.0, consignee2, TipoCarga.REFRIGERADA);

        dry.addBl(bl1);
        dry.addBl(bl2);
        tanque.setBl(bl3);
        reefer.setBl(bl4);

        System.out.println("=== Contenedores creados ===");
        System.out.println("Dry BLs: " + dry.getBls());
        System.out.println("Reefer BL: " + reefer.getBl());
        System.out.println("Tanque BL: " + tanque.getBl());


        // === Cargar contenedores en el buque ===
        buque.addContainer(dry);
        buque.addContainer(reefer);
        buque.addContainer(tanque);

        System.out.println("Buque Malvinas tiene " + buque.getCargas().size() + " contenedores");


        // === Conexión eléctrica del Reefer ===
        reefer.conectar(LocalDateTime.of(2025, 1, 1, 9, 0));
        reefer.desconectar(LocalDateTime.of(2025, 1, 1, 21, 0));

        System.out.println("Reefer estuvo conectado " + reefer.getHorasConectado() + " horas");


        // === Servicios ===
        Servicio lavado = new Lavado(300, 10, 500, 300);
        Servicio almacenamiento = new Almacenamiento(200);
        Servicio electricidad = new Electricidad(200,reefer.getHorasConectado());

        uruguay.addServicio(lavado);
        uruguay.addServicio(almacenamiento);
        uruguay.addServicio(electricidad);
        
        uruguay.iniciarServicioParaElContainer(tanque, almacenamiento);
        uruguay.iniciarServicioParaElContainer(reefer, electricidad);
        uruguay.iniciarServicioParaElContainer(tanque, lavado);

        System.out.println("Costo servicios Dry: $" + uruguay.costoTotalDeServiciosEnContainer(dry));
        System.out.println("Costo servicios Reefer: $" + uruguay.costoTotalDeServiciosEnContainer(reefer));


        // === Crear viaje ===
        LocalDateTime fecha2 = LocalDateTime.of(2025, 1, 2, 6, 0);
        Naviera colonia = new Naviera("Colonia Express");
        TerminalGestionada rosario = new TerminalGestionada(1000, "Rosario", motorDeBusqueda);

        Viaje viaje1 = new Viaje(123, fecha2, buque, colonia, circuito, uruguay, rosario);
        buque.agregarViaje(viaje1);
        viaje1.calcularFechaArribo(rosario);


        // === Órdenes ===
        Shipper shipper1 = new Shipper("Camila Arena", 111);
        EmpresaTransportista empresa1 = new EmpresaTransportista("Arcos");
        Chofer chofer1 = new Chofer("Jorge");
        Camion camion1 = new Camion("123", chofer1, empresa1);

        OrdenDeExportacion ordenShipper = new OrdenDeExportacion(dry, viaje1, buque, shipper1, camion1, chofer1, LocalDateTime.now());
        OrdenDeImportacion ordenConsignee = new OrdenDeImportacion(reefer, viaje1, buque, consignee, LocalDateTime.now());

        System.out.println("=== Órdenes registradas ===");
        System.out.println(ordenShipper);
        System.out.println(ordenConsignee);


        // === Estados del Buque ===
        buque.setEstado(new Outbound());
        buque.getEstadoBuque().actualizarPosicion(buque, 40.0, buenosAires);

        buque.setEstado(new Inbound());
        buque.getEstadoBuque().actualizarPosicion(buque, 0.0, buenosAires);

        buque.setEstado(new Arrived());
        buque.getEstadoBuque().actualizarPosicion(buque, 0.0, buenosAires);

        buque.setEstado(new Working());
        buque.getEstadoBuque().actualizarPosicion(buque, 0.0, buenosAires);

        buque.setEstado(new Departing());
        buque.getEstadoBuque().actualizarPosicion(buque, 60.0, buenosAires);


        // === Facturas ===
        Factura facturaShipper = ordenShipper.generarFactura();
        Factura facturaConsignee = ordenConsignee.generarFactura();

        facturaShipper.enviarPorMail();
        facturaConsignee.enviarPorMail();


        // === Circuitos adicionales ===
        CircuitoMaritimo circuito2 = new CircuitoMaritimo("Rutas Uruguay");
        circuito2.agregarTramo(new Tramo(2000, new Date(2L * 3600000), uruguay, buenosAires));
        circuito2.agregarTramo(new Tramo(3500, new Date(5L * 3600000), uruguay, rosario));
        circuito2.agregarTramo(new Tramo(4500, new Date(10L * 3600000), uruguay, rosario));

        uruguay.registrarCircuitoMaritimo(circuito2);
        uruguay.setCriterioSeleccion(new MenorTiempo());

        System.out.println("Mejor circuito: " + uruguay.obtenerMejorCircuito(rosario).getNombre());


        // === Reportes ===
        System.out.println("=== Reporte Buque ===");
        buque.accept(new ReporteBuqueVisitor());

        System.out.println("=== Reporte Aduana ===");
        buque.accept(new ReporteAduanaVisitor());

        System.out.println("=== Reporte Muelle ===");
        buque.accept(new ReporteMuelleVisitor());


        System.out.println("\n=== REGISTRO COMPLETO DE VIAJES DEL BUQUE ===");
        for (String registro : buque.getRegistroDeViajes()) {
            System.out.println(registro);
        }

        System.out.println("\n=== TERMINALES VISITADAS POR EL BUQUE ===");
        System.out.println(buque.getTerminalesVisitadas());

        System.out.println("\n=== CARGA TOTAL TRANSPORTADA POR EL BUQUE (BLs) ===");
        System.out.println(buque.getCargaTransportada());

        System.out.println("\n=== DETALLE DE CARGA POR CADA CONTENEDOR ===");
        for (String detalle : buque.getDetallesCargaPorContenedor()) {
            System.out.println(detalle);
        }

        System.out.println("\n========== FIN  ==========");
    }
}