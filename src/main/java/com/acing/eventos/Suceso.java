package com.acing.eventos;

public abstract class Suceso {

	private Participante participante;
	
	public Suceso(Participante participante) {
		this.participante = participante;
	}
	
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	
	
}
