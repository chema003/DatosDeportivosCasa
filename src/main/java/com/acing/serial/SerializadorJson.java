package com.acing.serial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.acing.eventos.Partido;
import com.acing.eventos.Evento;
import com.acing.eventos.EventoDAO;
import com.acing.eventos.EventoImpl;
import com.esotericsoftware.jsonbeans.Json;

import es.lanyu.commons.tiempo.Datable;

public class SerializadorJson implements EventoDAO {
	private final String ruta;
	Json json;
	
	public SerializadorJson(Json json, String ruta) {
		this.ruta = ruta;
		this.json = json;
	}
	
	public SerializadorJson(String ruta) {
		this(new Json(), ruta);
//		json.setSerializer(Date.class, new SerializadorDate());
	}
	
	@Override
	public Collection<? extends Partido> getEventos() {
		return getEventosFromJson(json, ruta);
	}

	@Override
	public Collection<? extends Evento> getEventos(Date fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Evento> int guardarEventos(Evento... eventos) {
		return guardarColeccionAJsonPorLineas(json, ruta, eventos);
	}

	@Override
	public <T extends Evento> Evento borrarEvento(Evento evento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Evento> boolean actualizarEvento(Evento evento) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public static int guardarColeccionAJsonPorLineas(Json json, String ruta, Evento... eventos) {
		int numeroEventosGuardados = 0;
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(ruta), "UTF-8"))) {
			for (Datable evento : eventos) {
				writer.write(json.toJson(evento));
				numeroEventosGuardados++;
				writer.newLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return numeroEventosGuardados;
	}
	
	private static Collection<Partido> getEventosFromJson(Json json, String rutaArchivo) {
		Collection<Partido> eventosLeidos = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(rutaArchivo),"UTF-8"))){
			
			String linea;
			while((linea = reader.readLine()) != null) {
				eventosLeidos.add(json.fromJson(Partido.class, linea));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventosLeidos;
	}
	
	private static void guardarStringEnFichero(String cadena, String ruta) {
		try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(ruta), "UTF-8"))) {
			writer.write(cadena);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
