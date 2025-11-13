package empresaMaritima;

public class Camion{
//Representa un camión utilizado por una Empresa Transportista para realizar
//operaciones de retiro o entrega de contenedores en la terminal.
	
    String idCamion;
    EmpresaTransportista empresa;
    Chofer chofer;

    public Camion(String idCamion, Chofer chofer,EmpresaTransportista empresa){
        this.idCamion = idCamion;
        this.empresa = empresa;
        this.chofer = chofer;
    }


    public String getIdCamion(){
        return idCamion;
    }

    public EmpresaTransportista getEmpresa(){
        return empresa;
    }
    public Chofer getChofer(){
        return chofer;
    }

//si se hace algo de recibir carga, hay que hacer que cargue y descargue
}