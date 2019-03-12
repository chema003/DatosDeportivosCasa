package com.acing.eventos;

import java.util.ArrayList;
import java.util.Collection;

import es.lanyu.commons.tiempo.DatableImpl;

public abstract class EventoImpl extends DatableImpl implements Evento {
	private Collection<Suceso> sucesos;

	@Override
	public Collection<Suceso> getSucesos() {
		if(sucesos == null)
			sucesos = new ArrayList<>();
		return sucesos;
	}

	public EventoImpl() {
		super();
	}
	
	

}