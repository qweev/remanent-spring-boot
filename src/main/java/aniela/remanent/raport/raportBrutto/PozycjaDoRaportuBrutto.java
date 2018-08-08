package aniela.remanent.raport.raportBrutto;


public class PozycjaDoRaportuBrutto {

		private int pozyzjaWRaporcie;
		private String nazwaTowaru;
		private String jednostka;
		private double cenaNetto;
		private double ilosc;
		private double cenaBrutto;
		
		public PozycjaDoRaportuBrutto(){}
		
		@Override
		public String toString() {
            return "{cenaNetto:" + cenaNetto +
                    ", cenaBrutto:" + cenaBrutto + ",nazwaTowaru:" +
					nazwaTowaru + ", jednostka:" + jednostka + 
					", ilosc:" + ilosc+ ", pozyzjaWRaporcie:" + pozyzjaWRaporcie +"}";
		}
		
		public int getPozyzjaWRaporcie() {
			return pozyzjaWRaporcie;
		}

		public void setPozycjaWRaporcie(int pozyzjaWRaporcie) {
			this.pozyzjaWRaporcie = pozyzjaWRaporcie;
		}

		public String getNazwaTowaru() {
			return nazwaTowaru;
		}

		public void setNazwaTowaru(String nazwaTowaru) {
			this.nazwaTowaru = nazwaTowaru;
		}

		public String getJednostka() {
			return jednostka;
		}

		public void setJednostka(String jednostka) {
			this.jednostka = jednostka;
		}

		public double getCenaNetto() {
			return cenaNetto;
		}

		public void setCenaNetto(double cenaNetto) {
			this.cenaNetto = cenaNetto;
		}

		public double getIlosc() {
			return ilosc;
		}

		public void setIlosc(double ilosc) {
			this.ilosc = ilosc;
		}

		public double getCenaBrutto() {
			return cenaBrutto;
		}

		public void setCenaBrutto(double cenaBrutto) {
			this.cenaBrutto = cenaBrutto;
		}

	
}
