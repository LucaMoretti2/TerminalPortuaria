package containers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import reportes.ReporteVisitable;
import reportes.ReporteVisitor;
import serviciosDeContainer.Servicio;
/*
 
Clase abstracta que modela un container dentro de la operatoria de la terminal.
Contiene la información común a todos los tipos de contenedores:
- Dimensiones (alto, ancho, largo)
- Peso total
- Identificador estándar (4 letras + 7 números)
- Fechas de ingreso y retiro de la terminal
- Servicios aplicados al container (Lavado, Almacenamiento, etc.)
 Provee comportamientos generales:
- registrarRetiro(): establece la fecha de retiro
- addServicio(): agrega un servicio aplicado al container
- costoTotalDeServicios(): calcula el costo acumulado de los servicios

Implementa la interfaz ReporteVisitable para integrarse con el patrón Visitor,
permitiendo que distintos tipos de reportes procesen la información del container
sin modificar la clase.

Las subclases deben definir detalles propios (tipo de container).
 */
public abstract class Container implements ReporteVisitable {

	int alto;
	int ancho;
	int largo;
	double pesoTotal;
	String idContainer; //4 letras 7 numeros
	List<Servicio> servicios = new ArrayList<>();
	LocalDateTime fechaDeIngreso; 
	LocalDateTime fechaDeRetiro; 
	String tipo;
	
	
	public Container(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		this.pesoTotal = pesoTotal;
		this.idContainer = idContainer;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeRetiro = null;
		
	}
	
	public String getIDContainer() {
        return idContainer;
    }

    public double getPesoTotal() {
        return pesoTotal;
    }
    
    public double getAlto() {
        return alto;
    }
    public double getAncho() {
        return ancho;
    }
    public double getLargo() {
        return largo;
    }
    
    public void registrarRetiro(LocalDateTime fechaDeRetiro) {
    	this.fechaDeRetiro = fechaDeRetiro;
    }
    
    public LocalDateTime getFechaDeIngreso() {
    	return fechaDeIngreso; 
    }
    
    public LocalDateTime getFechaDeRetiro() { 
    	return fechaDeRetiro; 
    }
    
    public void addServicio(Servicio s) {
    	servicios.add(s);
    }
    
    public double costoTotalDeServicios(){
    	
    	double costoHastaAhora= 0;
    	for (Servicio s: servicios) {
    		costoHastaAhora += s.precioFijo;
    	}
    	return costoHastaAhora;
    }
    
    public String getTipo() {
    	return tipo;
    }
    @Override
    public void accept(ReporteVisitor visitor) {
        visitor.visit(this);
    }

	
	

	
}
