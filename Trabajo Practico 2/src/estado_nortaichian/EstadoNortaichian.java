package estado_nortaichian;

import raza.*;

public interface EstadoNortaichian {
    void atacar(Nortaichian origen,Raza objetivo);

    void recibirAtaque(Nortaichian origen,int danio);

    void descansar(Nortaichian origen);
}