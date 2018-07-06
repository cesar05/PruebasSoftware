package co.com.ceiba.parqueadero.dominio;

public final class Precio {
	
	private static final double VALOR_HORA_MOTO=500;
	private static final double VALOR_HORA_CARRO=1000;
	private static final double VALOR_DIA_MOTO=4000;
	private static final double VALOR_DIA_CARRO=8000;
	private static final double VALOR_ADICIONAL_MOTO=2000;
	private double valorHora;
	private double valorDia;
	private double valorAdicional;
	
	public Precio() {
		super();
	}
	
	private Precio(double valorHoraMoto, double valorDiaMoto, double valorAdicionalMotos) {
		super();
		this.valorHora = valorHoraMoto;
		this.valorDia = valorDiaMoto;
		this.valorAdicional = valorAdicionalMotos;
	}
	
	
	private Precio(double valorHoraCarro,double valorDiaCarro) {
		super();
		this.valorHora = valorHoraCarro;
		this.valorDia = valorDiaCarro;
		this.valorAdicional=0;
	}
	
	
	public Precio getPrecio(int tipoVehiculo){
		Precio precio = null;
		
		switch(tipoVehiculo) {
			case 0:
				precio = new Precio(VALOR_HORA_MOTO, VALOR_DIA_MOTO, VALOR_ADICIONAL_MOTO);
				break;
			case 1:
				precio = new Precio(VALOR_HORA_CARRO, VALOR_DIA_CARRO);
				break;
			default:
				break;
		}
		//TipoVehiculo.MOTO;
		/*switch(TipoVehiculo.valueOf( String.valueOf(tipoVehiculo) ) ){
		case MOTO:
			precio = new Precio(VALOR_HORA_MOTO, VALOR_DIA_MOTO, VALOR_ADICIONAL_MOTO);
			break;
		case CARRO:
			precio = new Precio(VALOR_HORA_CARRO, VALOR_DIA_CARRO);
			break;
		default:
			break;
		}*/
		
		return precio;
	}
	
	public double getValorHoraMoto() {
		return VALOR_HORA_MOTO;
	}
	
	public double getValorHoraCarro() {
		return VALOR_HORA_CARRO;
	}
	
	public double getValorDiaMoto() {
		return VALOR_DIA_MOTO;
	}
	
	public double getValorDiaCarro() {
		return VALOR_DIA_CARRO;
	}
	
	public double getValorAdicionalMotos() {
		return VALOR_ADICIONAL_MOTO;
	}	

	public double getValorHora() {
		return valorHora;
	}

	public double getValorDia() {
		return valorDia;
	}

	public double getValorAdicional() {
		return valorAdicional;
	}
	
	
	
}
