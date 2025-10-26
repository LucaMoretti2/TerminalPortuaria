package empresaMaritima;

public class Camion{

    String idCamion;
    EmpresaTransportista empresa;
    Chofer chofer;

    public Camion(String idCamion, Chofer chofer){
        this.idCamion = idCamion;
    }


    public String getIdCamion(){
        return idCamion;
    }

    public EmpresaTransportista getEmpresa(){
        return empresa;
    }

//si se hace algo de recibir carga, hay que hacer que cargue y descargue
}