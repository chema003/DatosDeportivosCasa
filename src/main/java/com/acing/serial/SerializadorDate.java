package com.acing.serial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.html.HTMLDocument.HTMLReader.FormAction;

import com.acing.eventos.Partido;
import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SerializadorDate implements JsonSerializer<Date> {
//	private String formatoDia;
//	private String formatoHora;
	private SimpleDateFormat sdformatDia = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat sdformatHora;// = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat sdformatDate;// = new SimpleDateFormat("dd/MM/yy HH:mm");
	
    public SerializadorDate() {
    	this("dd/MM/yyyy", "HH:mm");
    }
    
    public SerializadorDate(String formatoDia, String formatoHora) {
//		this.formatoDia = formatoDia;
//		this.formatoHora = formatoHora;
		sdformatDia = new SimpleDateFormat(formatoDia);
	    sdformatHora = new SimpleDateFormat(formatoHora);
	    sdformatDate = new SimpleDateFormat(formatoDia + " " + formatoHora);
	}
    
	@Override
	public Date read(Json json, JsonValue jsonData, Class type) {
		Date fecha;
        String dia = jsonData.getString("dia");
        String hora = jsonData.getString("hora");
        try {
			fecha = sdformatDate.parse(dia + " " + hora);
		} catch (ParseException e) {
			e.printStackTrace();
			fecha = null;
		}
        
		return fecha;
	}

	@Override
	public void write(Json json, Date fecha, Class knownType) {
		json.writeObjectStart();
		SimpleDateFormat sdformatDia = new SimpleDateFormat("dd/MM/yyyy");
        json.writeValue("dia", sdformatDia.format(fecha));
        json.writeValue("hora", sdformatHora.format(fecha));
        json.writeObjectEnd();
	}

}
