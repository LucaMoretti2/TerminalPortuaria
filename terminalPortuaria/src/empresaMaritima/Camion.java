package empresaMaritima;

public class Camion{

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