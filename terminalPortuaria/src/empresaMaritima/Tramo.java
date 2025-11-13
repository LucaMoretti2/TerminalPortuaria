package empresaMaritima;

import java.util.Date;
//Representa un tramo dentro de un circuito maritimo. Cada tramo conecta dos 
//terminales portuarias consecutivas y tiene asociado un precio y una duración.

public class Tramo {
	    private double precio;
	    private Date tiempo;
	    private String terminalOrigen;
	    private String terminalDestino;

	    
	 public Tramo(double precio, Date tiempo, String terminalOrigen, String terminalDestino) {
	        this.precio = precio;
	        this.tiempo = tiempo;
	        this.terminalOrigen = terminalOrigen;
	        this.terminalDestino = terminalDestino;
	    }

	  public double getPrecio() {
	        return precio;
	    }

	    public void setPrecio(double precio) {
	        this.precio = precio;
	    }

	    public Date getTiempo() {
	        return tiempo;
	    }

	    public void setTiempo(Date tiempo) {
	        this.tiempo = tiempo;
	    }

	    public String getTerminalOrigen() {
	        return terminalOrigen;
	    }

	    public String getTerminalDestino() {
	        return terminalDestino;
	    }

//devuelve la duración del tramo convertida a horas
	    public long getDuracionEnHoras() {
	        return tiempo.getTime() / (1000 * 60 * 60);
	    }

	 
}
