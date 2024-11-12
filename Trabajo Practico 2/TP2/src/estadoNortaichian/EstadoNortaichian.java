package estadoNortaichian;

import raza.*;

public interface EstadoNortaichian {
	int atacar(Nortaichian origen);

	void recibirAtaque(Nortaichian origen, int daño);

	void descansar(Nortaichian origen);
}