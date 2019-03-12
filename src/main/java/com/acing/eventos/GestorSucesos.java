package com.acing.eventos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public interface GestorSucesos extends Evento {

	//Método corregido como el de clase.
	@SuppressWarnings("unchecked")
	public default <T extends Suceso>Collection<T>  getSucesosGestionados(Class<T> tipo){
		return (Collection<T>) getSucesos().stream()
				.filter(s -> s.getClass().isAssignableFrom(tipo)).collect(Collectors.toList());
	}
	
	//Éste es como en un principio lo había hechol devolviendo un Array con los Sucesos 
	//en los que un participante estaba involucrado. 
	public default Collection<Suceso> getSucesosParticipanteM(Participante participante) {
		Collection<Suceso> aux = new ArrayList<>();
		for (Suceso s : getSucesos()) {
			if(s.getParticipante().isEquals(participante)) {
				aux.add(s);
			}
		}		
		return aux;
	}
	
	//En el diagrama viene con retorno un int. En clase se ha hecho así:
	public default int getSucesosParticipante(Participante participante){
		return (int) getSucesos().stream()
				.filter(s -> s.getParticipante().equals(participante))
				.count();
	}
	
	
	//Método corregido como el de clase.
	default <T extends Suceso> void addSuceso(T suceso) {
		getSucesos().add(suceso);
	}
	
//	public <T> void addSuceso(T suceso) {  //Ampliado al que figura en el interfaz.   
//		getSucesos().add((Suceso) suceso);//que al final tengo que castear a Suceso.
//	}
	
	//Método corregido como el de clase.
	default <T extends Suceso> void addSucesos(Class<T> tipo, int numero, Participante participante) {
		for(int i = 0; i < numero; i++) {
			T suceso;
			try {
				suceso = tipo.newInstance();
				suceso.setParticipante(participante);
				addSuceso(suceso);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	//El mio:
		public default void addSuceso(Class<Suceso> tipoSuceso, int numero, Participante participante) {
			
			for (int i=0; i<numero; i++) {
				this.addSuceso(tipoSuceso, participante);
			}
		}	
	
	//Éste es el que yo hice:
	
	public default void addSucesoM(Class<Suceso> tipoSuceso, Participante participante) {
		try {
			Suceso suceso = tipoSuceso.newInstance();//Instancia de la Clase que recibe por parámetro
													//y, al coger constructor vacío, setteamos el participante.
			suceso.setParticipante(participante);
			getSucesos().add(suceso);
		} catch (InstantiationException e) {	
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}
	
	//El de clase:
	public default <T extends Suceso> void addSuceso(Class<T> tipo, Participante participante) {
		addSucesos(tipo, 1, participante);
	}
	
	
	
}
