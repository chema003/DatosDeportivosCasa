package com.acing.eventos;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


public class Partido extends EventoImpl implements EventoConGoles, GestorSucesos {
	
	private final static SimpleDateFormat sdfToString= new SimpleDateFormat("dd/MM/yy HH:mm");
	private Participante local;
	private Participante visitante;
//	String resultado;
	
	@Override
	public String getResultado() {
		long golesLocal = 0;// = getSucesos().stream()
//							.filter(s -> s.getParticipante().equals(local))
//							.count();
		//Este método es sólo para goles. Vamos a hacerlo más genérico para GOLES y TARJETAS.
		long golesVisitante = 0;
		for (Suceso s : getSucesosGestionados(Gol.class)) {//Cambiado GetSucesos por GetSucesosGestionados
			if(s.getParticipante().equals(visitante))
				golesVisitante++;
			if(s.getParticipante().equals(local))
				golesLocal++;
		}
		return golesLocal + "-" + golesVisitante;
	}
	
	//GENÉRICO
	
	public String getResultadoTarjetas() {
		long tarjetasAmarillasLocal = getSucesosGestionados(TarjetaAmarilla.class).stream()
							.filter(s -> s.getParticipante().equals(getLocal()))
							.count();
		long tarjetasAmarillasVisitante = getSucesosGestionados(TarjetaAmarilla.class).stream()
				.filter(s -> s.getParticipante().equals(getVisitante()))
				.count();
		
		long tarjetasRojasLocal  = getSucesosGestionados(TarjetaRoja.class).stream()
				.filter(s -> s.getParticipante().equals(getLocal()))
				.count();
		long tarjetasRojasVisitante  = getSucesosGestionados(TarjetaRoja.class).stream()
				.filter(s -> s.getParticipante().equals(getVisitante()))
				.count();
		
		
		//Este método es sólo para goles. Vamos a hacerlo más genérico para GOLES y TARJETAS.
//		long golesVisitante = 0;
//		for (Suceso s : getSucesos()) {
//			if(s.getParticipante().equals(visitante))
//				golesVisitante++;
//			if(s.getParticipante().equals(local))
//				golesLocal++;
//		}
		return "AL: " + tarjetasAmarillasLocal + " RL: " + tarjetasRojasLocal + " AV: " + 
		tarjetasAmarillasVisitante + " RV: " + tarjetasRojasVisitante;
	}
	
	
	
	
	
//	public void setResultado(String resultado) {
////		this.resultado = resultado;
//	}

	public Partido() {}
	
	public Partido(Participante local, Participante visitante, Date fecha) {
		super();
		this.local = local;
		this.visitante = visitante;
		setFecha(fecha);
	}

	@Override
	public String toString() {
		return "(" + sdfToString.format(getFecha()) + ") " + local + " vs " + visitante + " => " 
				+ getResultado() + " tarjetas: " + getResultadoTarjetas();
	}

	//FALTABA POR HACER:
	@Override
	public Participante getGanador() {
		Participante ganador = getLocal();
		if (getGolesParticipante(getLocal())<getGolesParticipante(getVisitante())) {
			ganador = getVisitante();
		}
		return ganador;
	}

	@Override
	public Participante getLocal() {
		return local;
	}

	@Override
	public Participante getVisitante() {
		return visitante;
	}

	@Override
	public Collection<Gol> getGoles() {//No se puede hacer el Casteo (Downcast). 
										//Voy a crear un Array Aux para meter los objetos tipo Gol
		Collection<Gol> aux = new ArrayList<>();
		for (Suceso s : getSucesos()) {
			if(s instanceof Gol) {//Compruebo que el Suceso es del tipo Gol. Si lo es, entra en mi array aux.
				aux.add((Gol) s);
			}
		}
		return aux;		
	}
	
	

	@Override
	public int getGolesParticipante(Participante participante) {//Voy a necesitar comparar los participantes con su ID. 
		int golesParticipante = 0;								//Otra opción podría ser con el comparador de SU libreria.
		for (Suceso s : getSucesos()) {
//			if(s.getParticipante().getIdentificador().equals(participante.getIdentificador())) {//sumo cuando el anotador 
//																//es el participante que busco
//				golesParticipante++;
//			}
			if(s.getParticipante().isEquals(participante)) {//Utilizando su libreria
				golesParticipante++;
			}
		}		
		return golesParticipante;
	}

	@Override
	public void addGoles(int numGolesLocal, int numGolesVisitante) {//Lo que hacemos es crear Objetos tipo Gol
																	//tantos como diga el parámetro de entrada.
		for (int i=0; i<numGolesLocal; i++) {
			Gol golLocal = new Gol(0, getLocal());
			addSuceso(golLocal);
		}
		
		for (int i=0; i<numGolesVisitante; i++) {
			Gol golVisitante = new Gol(0, getVisitante());
			addSuceso(golVisitante);
		}
	}

	public void addTarjetas (int amarillasLocal, int amarillasVisitante, int rojasLocal, int rojasVisitante) {
		
			addSucesos(TarjetaAmarilla.class, amarillasLocal, getLocal());
			addSucesos(TarjetaAmarilla.class, amarillasVisitante, getVisitante());
			addSucesos(TarjetaRoja.class, rojasLocal, getLocal());
			addSucesos(TarjetaRoja.class, rojasVisitante, getVisitante());
	}

//	public void addSuceso(Suceso suceso) {
//		getSucesos().add(suceso);
//	}
	
	
	
}
