package containers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import reportes.ReporteVisitable;
import reportes.ReporteVisitor;
import serviciosDeContainer.Servicio;

 
//Clase abstracta que modela un container dentro de la operatoria de la terminal.

public abstract class Container implements ReporteVisitable {

//Contiene la información común a todos los tipos de contenedores:
	int alto;
	int ancho;
	int largo;
	double pesoTotal;
	String idContainer; //4 letras 7 numeros
	List<Servicio> servicios = new ArrayList<>();
	LocalDateTime fechaDeIngreso; 
	LocalDateTime fechaDeRetiro; 
	String tipo;
	protected TipoCarga tipoPermitido;
	
	

	public Container(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		this.alto = alto;
		this.ancho = ancho;
		this.largo = largo;
		this.pesoTotal = pesoTotal;
		this.idContainer = idContainer;
		this.fechaDeIngreso = fechaDeIngreso;
		this.fechaDeRetiro = fechaDeRetiro;
		
		
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
    
    public abstract TipoCarga getTipoPermitido();
    
  // establece la fecha de retiro 
    public void registrarRetiro(LocalDateTime fechaDeRetiro) {
    	this.fechaDeRetiro = fechaDeRetiro;
    }
    
    public LocalDateTime getFechaDeIngreso() {
    	return fechaDeIngreso; 
    }
    
    public LocalDateTime getFechaDeRetiro() { 
    	return fechaDeRetiro; 
    }
    
  //agrega un servicio aplicado al container
    public void addServicio(Servicio s) {
    	servicios.add(s);
    }
 //calcula el costo acumulado de los servicios
    
    public double costoTotalDeServicios() {
        double costoHastaAhora = 0;
        for (Servicio s : servicios) {
            costoHastaAhora += s.getPrecioFijo(); 
        }
        return costoHastaAhora;
    }
    
    public String getTipo() {
    	return tipo;
    }
    public List<Servicio> getServicios() {
        return servicios;
    }
 //Implementa la interfaz ReporteVisitable para integrarse con el patrón Visitor,
 // permitiendo que distintos tipos de reportes procesen la información del container
 //   sin modificar la clase.
    @Override
    public void accept(ReporteVisitor visitor) {
        visitor.visit(this);
    }

    public boolean aceptaCarga(BillOfLading bl) {
    	 return bl.getTipo() == this.tipoPermitido;
    }
    
    
	
}
