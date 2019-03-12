package com.acing.serial;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.acing.eventos.Partido;
import com.acing.eventos.Suceso;
import com.acing.eventos.Evento;
import com.acing.eventos.EventoDAO;
import com.acing.eventos.EventoImpl;
import com.acing.eventos.Participante;

public class SerializadorCSV implements EventoDAO{

	private final String ruta;
	
//	public void setRutaPorDefecto (String ruta) {
//		this.ruta = ruta;
//	}
	
	public SerializadorCSV(String ruta) {
		this.ruta = ruta;
	}
	
	public static Collection<? extends Evento> getEventos(String rutaArchivo){
		Collection<Evento> eventosLeidos = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(
						new FileInputStream(rutaArchivo),
						"UTF-8"))){
			
			String linea = reader.readLine();// Leo la primera linea
			while((linea = reader.readLine()) != null) {
				eventosLeidos.add(deserializarEvento(linea));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return eventosLeidos;
	}
	
	private static Partido deserializarEvento(String linea) throws ParseException {
		String[] campos = linea.split(",");
		String fechaString = campos[1];
		String localString = campos[2];
		String visitanteString = campos[3];
		String golesLocalString = campos[4];
		String golesVisitanteString = campos[5];
		
		//Añado para deserializar TARJETAS
		String tarjetasAmarillasLocal = campos[18];
		String tarjetasAmarillasVisitante = campos[19];
		String tarjetasRojasLocal = campos[20];
		String tarjetasRojasVisitante = campos[21];
		
		SimpleDateFormat sdformat = new SimpleDateFormat("dd/MM/yy");
		
		Date fecha = sdformat.parse(fechaString);
		Participante local = new Participante(localString);
		Participante visitante = new Participante(visitanteString);
		int golesLocal = Integer.parseInt(golesLocalString);
		int golesVisitante = Integer.parseInt(golesVisitanteString);
//		String resultado = golesLocal + "-" + golesVisitante;
		
		//PASO A ENTERO LAS TARJETAS
		int amarillasLocal = Integer.parseInt(tarjetasAmarillasLocal);
		int amarillasVisitante = Integer.parseInt(tarjetasAmarillasVisitante);
		int rojasLocal = Integer.parseInt(tarjetasRojasLocal);
		int rojasVisitante = Integer.parseInt(tarjetasRojasVisitante);
		
		
		
		Partido miEvento = new Partido(local, visitante, fecha);
		miEvento.addGoles(golesLocal, golesVisitante);
		miEvento.addTarjetas(amarillasLocal, amarillasVisitante, rojasLocal, rojasVisitante);
//		miEvento.setResultado(resultado);
//		addGoles(golesLocal, local, miEvento);
//		addGoles(golesVisitante, visitante, miEvento);
		
		return miEvento;
	}
	
//	private static void addGoles(int numeroGoles, Participante equipo, Evento miEvento) {
//		for(int i = 0; i < numeroGoles; i++) {
//			Suceso gol = new Suceso();
//			gol.setParticipante(equipo);
//			miEvento.addSuceso(gol);
//		}
//	}
	//Este método aquí sobraría, no?? En todo caso su lugar es el gestor de sucesos.

	
	@Override
	public Collection<? extends Evento> getEventos() {
		return getEventos(ruta);
	}
	

	@Override
	public Collection<? extends Partido> getEventos(Date fecha) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public <T extends Evento> int guardarEventos(Evento... eventos) {
		// TODO Auto-generated method stub
		return 0;
	}
}
