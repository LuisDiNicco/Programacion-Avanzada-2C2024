package raza;

public enum NombreRaza {
	WRIVES {
		@Override
		public Raza crearRaza() {
			return new Wrives();
		}
	},
	RERALOPES {
		@Override
		public Raza crearRaza() {
			return new Reralopes();
		}
	},
	RADAITERAN {
		@Override
		public Raza crearRaza() {
			return new Radaiteran();
		}
	},
	NORTAICHIAN {
		@Override
		public Raza crearRaza() {
			return new Nortaichian();
		}
	};

	public abstract Raza crearRaza();
}
