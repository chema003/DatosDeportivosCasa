package com.acing.eventos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import es.lanyu.commons.identificable.AbstractNombrable;

public class Participante extends AbstractNombrable {
	
	public static Map<String, Participante> mapaParticipantes = new HashMap<>();
	
	
//	@Override
//	public String getIdentificador() {
//		return id;
//	}
	
	public Participante() {
	}
	
	public Participante(String nombre) {
//		this.nombre = nombre;
		setIdentificador(nombre);
		setNombre(nombre);
		if(!mapaParticipantes.containsKey(id)) {//Al cerrar el objeto, lo incluyo en el Map.
			mapaParticipantes.put(id, this);
		}
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	public Collection<Participante> getParticipantesRegistrados(){
		Collection<Participante> aux = new ArrayList<>();
		mapaParticipantes.forEach((id, participante) -> aux.add(participante));//recorro el Map y cojo todos los Participantes del Mapa.
		return aux;
	}

	
	public Participante getIdentificable(String ID) {
		 Participante pBuscado = null;//Si no existe, me devuelve null. 
		
		if (mapaParticipantes.containsKey(ID)) {
			pBuscado = mapaParticipantes.get(ID);
		}		
		return pBuscado;
	}

	public void registrarParticipante(Participante p) {
		String id;
		id = p.getIdentificador();
			mapaParticipantes.put(id, p);//Si el ID ya existe en el mapa,  reemplaza el campo valor.
	}
	
	public void registrarNombre(Participante p) {//¿?¿?¿?¿?¿?
		
	}
	
	
	
	
	//Con este método lo complicado es localizar el atributo del objeto vinculado a un key. 
	//Recorremos todos los key del mapa, obtenemos todos los participantes del mapa.
	//Comparamos el atributo nombre de cada Participante con el que buscamos.
	public Participante getParticipantePorNombre(String nombre) {
		Participante participanteNombre = null;
		for (String id : mapaParticipantes.keySet()) {
			Participante pAux = mapaParticipantes.get(id);
			if(pAux.getNombre().equals(nombre)) {
				participanteNombre = pAux;
				break;
			}
		}
		return participanteNombre;
	}
	
	
}
