package empresaMaritima;

import actores.ActorPortuario;

public interface Sujeto {
	void registrarActorPortuario(ActorPortuario actor);
	void eliminarActorPortuario(ActorPortuario actor);
	void notificar(String mensaje);
}
