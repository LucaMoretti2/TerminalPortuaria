package tramo;

import java.util.Date;

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

	 
	    public long getDuracionEnHoras() {
	        return tiempo.getTime() / (1000 * 60 * 60);
	    }

	 
}
