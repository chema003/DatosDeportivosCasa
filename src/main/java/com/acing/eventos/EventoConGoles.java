package com.acing.eventos;

import java.util.Collection;

public interface EventoConGoles extends LocalContraVisitante {

//	private static String getFormatoGoles();
	
	public Collection <Gol> getGoles();
	
	public int getGolesParticipante(Participante participante);
	
	public void addGoles(int numGolesLocal, int numGoleVisitante);
	
	public String getResultado();
}
