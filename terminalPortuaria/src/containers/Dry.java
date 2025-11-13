package containers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/*
 Representa un contenedor Dry extendiendo la clase abstracta Container.
 Un container Dry puede transportar uno o varios Bill of Lading, por lo que mantiene
 una lista de BL asociados a la mercadería que contiene.
 Funcionalidades principales:
 - addBl(): agrega un Bill of Lading al contenedor
- getBls(): devuelve una copia inmutable de los BL registrados
- getPesoTotalDeBLs(): calcula el peso total de la carga contenida en los BL
- esDesconsolidado(): indica si el contenedor tiene un único BL (o ninguno),
 */


public class Dry extends Container {

	private List<BillOfLading> bls = new ArrayList<>();
	
	public Dry(int alto, int ancho, int largo, double pesoTotal, String idContainer, LocalDateTime fechaDeIngreso, LocalDateTime fechaDeRetiro) {
		super(alto, ancho, largo, pesoTotal, idContainer, fechaDeIngreso, fechaDeRetiro);
		this.tipo = "Dry";
		// TODO Auto-generated constructor stub
	}

	public void addBl(BillOfLading bl) {
		bls.add(bl);	
	}
	
	public List<BillOfLading> getBls() {
        return List.copyOf(bls);
    }

	public double getPesoTotalDeBLs() {
		double pesoHastaAhora = 0;
		for (BillOfLading bl: bls) {
			pesoHastaAhora += bl.getPeso();
		}
		return pesoHastaAhora;
	}
	
	public boolean esDesconsolidado() {
        return bls.size() <= 1;
    }
}
