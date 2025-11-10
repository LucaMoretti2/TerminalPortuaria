package empresaMaritima;

public interface Sujeto {
	void registrarActorPortuario(ActorPortuario actor);
	void eliminarActorPortuario(ActorPortuario actor);
	void notificar(String mensaje);
}
