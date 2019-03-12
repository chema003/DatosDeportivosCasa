package com.acing.eventos;

import java.util.Collection;
import java.util.Date;

public interface EventoDAO {
	Collection<? extends Evento> getEventos();
    Collection<? extends Evento> getEventos(Date fecha);
    <T extends Evento> int guardarEventos(Evento... eventos);
    <T extends Evento> Evento borrarEvento(Evento evento);
    <T extends Evento> boolean actualizarEvento(Evento evento);
}
