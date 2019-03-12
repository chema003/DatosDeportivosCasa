package com.acing.eventos;

public class TarjetaAmarilla extends Suceso {

	private int minuto;
	
	
	public TarjetaAmarilla() {
		super(null);
		minuto = 0;
	}
	public TarjetaAmarilla(Participante participante, int minuto) {
		super(participante);
		this.minuto = minuto;
		
	}
	
	public int getMinuto() {
		return minuto;
	}
	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
	
	
}
