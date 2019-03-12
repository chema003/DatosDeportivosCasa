package com.acing.serial;

import java.util.Date;

import com.acing.eventos.Partido;
import com.esotericsoftware.jsonbeans.Json;
import com.esotericsoftware.jsonbeans.JsonSerializer;
import com.esotericsoftware.jsonbeans.JsonValue;

public class SerializadorEventos implements JsonSerializer<Partido> {

	@Override
	public Partido read(Json arg0, JsonValue arg1, Class arg2) {
		
		JsonValue jvDate = arg1.get("fecha");
		SerializadorDate sd = (SerializadorDate) arg0.getSerializer(Date.class);
		Date fecha = sd.read(arg0, jvDate, Date.class);
		
		return null;
	}

	@Override
	public void write(Json arg0, Partido arg1, Class arg2) {
		
		arg0.writeValue("fecha", arg1.getFecha());
	}

}
