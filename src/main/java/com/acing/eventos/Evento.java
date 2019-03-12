package com.acing.eventos;

import java.util.Collection;
import java.util.Date;

import es.lanyu.commons.tiempo.Datable;

public interface Evento extends Datable {
	
	Collection<Suceso> getSucesos();
	String getResultado();
	
	
//	default void addSuceso(Suceso suceso) { //Comentado por erasure del GestorSucesos
//		getSucesos().add(suceso);
//	}

	public Date getFecha();
	
	public Participante getGanador();
}