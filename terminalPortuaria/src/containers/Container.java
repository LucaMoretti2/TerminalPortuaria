package containers;

import java.time.LocalDateTime;
import java.util.List;

import serviciosDeContainer.Servicio;

public abstract class Container {

	int alto;
	int ancho;
	int largo;
	double pesoTotal;
	String idContainer; //4 letras 7 numeros
	List<Servicio> servicios;
	LocalDateTime fechaDeIngreso; //a evaluar 
	LocalDateTime fechaDeRetiro; //a evaluar 
	
	
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
    	
	
	

	
}
