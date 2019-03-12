package com.acing.eventos;

public class TarjetaRoja extends Suceso {


		private int minuto;
		
		
		public TarjetaRoja() {
			super(null);
			minuto = 0;
		}
		public TarjetaRoja (Participante participante, int minuto) {
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
