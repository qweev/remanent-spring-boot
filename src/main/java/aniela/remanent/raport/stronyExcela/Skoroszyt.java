package aniela.remanent.raport.stronyExcela;

import java.util.ArrayList;
import java.util.Calendar;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import aniela.remanent.raport.raportDoDruku.PozycjaDoRaportu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


public class Skoroszyt {
	private XSSFWorkbook skoroszyt;
	private XSSFSheet arkusz;
	private XSSFSheet podsumowanie;
	
	private static final int iloscKolumn = 5; // indeks od zera
	private static final String nazwaArkuszaPozycji = "remanent";
	private static final int ROK;
	private static final float wysokoscWiersza = (float) 12.8;
	
	private CellStyle ramkaLewaPrawa;
	private CellStyle ramkaDolLewaPrawa;
	private CellStyle ramkaGoraLewaPrawa;
	private CellStyle ramkaDookolaWysrodkowanyTekst;
	private CellStyle ramkaLewaPrawaWysrodkowanyTekst;
	private CellStyle ramkaDolLewaPrawaWysrodkowanyTekst;
	private CellStyle ramkaGoraLewaPrawaWysrodkowanyTekst;
	private CellStyle wysrodkowanyTekstBezRamki;
	private CellStyle tekstBezRamkiDoPrawej;
	private CellStyle walutaRamkaGoraLewaPrawaTekstDoPrawej;
	private CellStyle walutaRamkaDolLewaPrawaTekstDoPrawej;
	private CellStyle walutaRamkaLewaPrawaTekstDoPrawej;
	private CellStyle samTekstBezRamki;
	private CellStyle ramkaDookola;
	
	
	
	private static final int kolumnaLP = 1253;
	private static final int kolumnaNazwaTowaru = 11400;
	private static final int kolumnaJM = 1300;
	private static final int kolumnaIlosc = 1800;
	private static final int kolumnaCenaNetto = 2550;
	private static final int kolumnaSumaNetto = 3050;
	
	private static final String nazwaArkuszaPodsumowanie= "podsumowanie";
	
	private ArrayList<String> sumyStron;
	
	static  {
		ROK = (Calendar.getInstance().get(Calendar.YEAR) - 1 );
	}


	public Skoroszyt(){
		this.skoroszyt = new XSSFWorkbook();
//		this.skoroszyt = book;

		this.arkusz = this.skoroszyt.createSheet(nazwaArkuszaPozycji);
		utworzStyle(this.skoroszyt);
	 	ustawSzerokoscKolumnPozycji(this.arkusz);
	 	this.sumyStron = new ArrayList<String>();
	 	this.podsumowanie = this.skoroszyt.createSheet(nazwaArkuszaPodsumowanie);
	}


	public XSSFWorkbook pobierzSkoroszyt() {
		return skoroszyt;
	}


	public void utworzTytul(int numerWiersza) {
		Row row = arkusz.createRow(numerWiersza);
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		
		for( int i=0; i<iloscKolumn ;i++){
			cell = row.createCell(i);
			if (i == 0){
				cell.setCellValue("Spis z natury sporządzony na dzień 31.12."+ROK);
				cell.setCellStyle(wysrodkowanyTekstBezRamki);
			}
		}
		
		arkusz.addMergedRegion(new CellRangeAddress(numerWiersza, numerWiersza, 0, iloscKolumn));
	}



	public void utworzNaglowkiTabeli(int numerWiersza) {
		String[] naglowki = {"LP", "NazwaTowaru", "j.m.","Ilość", "Cena Netto","Wartość Netto"};
		Row row = arkusz.createRow(numerWiersza);	
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		for( int i=0; i<=iloscKolumn ;i++){
			cell = row.createCell(i);
			cell.setCellValue(naglowki[i]);	
			cell.setCellStyle(ramkaDookolaWysrodkowanyTekst);
		}
	}
	
	
	public void utworzPustyWiersz(int numerWiersza) {
		Row row = arkusz.createRow(numerWiersza);
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		for( int i=0; i<=iloscKolumn ;i++){
			cell = row.createCell(i);
			cell.setCellValue("");	
			cell.setCellStyle(tekstBezRamkiDoPrawej);
		}
	}

	

	public void utworzNumerStrony(int numerWiersza, int strona) {
		Row row = arkusz.createRow(numerWiersza);	
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		for( int i=0; i<=iloscKolumn ;i++){
			cell = row.createCell(i);
			if (i == iloscKolumn ){
			cell.setCellValue("str "+strona);	
			cell.setCellStyle(tekstBezRamkiDoPrawej);
			}
		}
		
	}


	public void utworzPierwszyWierszTabeli(int numerWiersza, int numerPozycji, PozycjaDoRaportu pozycja) {
		CellStyle stylDoPrawej = ramkaGoraLewaPrawa;
		CellStyle stylSrodkuj = ramkaGoraLewaPrawaWysrodkowanyTekst;
		CellStyle stylWaluta = walutaRamkaGoraLewaPrawaTekstDoPrawej;
		wpiszPozycjeDoWiersza(numerWiersza, numerPozycji, pozycja, stylDoPrawej, stylSrodkuj, stylWaluta );
	}


	private void wpiszPozycjeDoWiersza(int numerWiersza, int numerPozycji, PozycjaDoRaportu pozycja, CellStyle doPrawej, CellStyle srodkuj, CellStyle waluta ) {
		Row row = arkusz.createRow(numerWiersza);	
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		int LP = 0;
		int NazwaTowaru = 1;
		int jednostka = 2;
		int ilosc = 3;
		int cenaNetto = 4;
		int sumaNetto = 5;
		
		cell = row.createCell(LP);
		cell.setCellValue(numerPozycji+1);
		cell.setCellStyle(srodkuj);
		
		cell = row.createCell(NazwaTowaru);
		cell.setCellValue(pozycja.getNazwaTowaru());
		cell.setCellStyle(doPrawej);
		
		cell = row.createCell(jednostka);
		cell.setCellValue(pozycja.getJednostka());
		cell.setCellStyle(srodkuj);
		
		cell = row.createCell(ilosc);
		cell.setCellValue(pozycja.getIlosc());
		cell.setCellStyle(srodkuj);
		
		cell = row.createCell(cenaNetto);
		cell.setCellValue(pozycja.getCenaNetto());
		cell.setCellStyle(waluta);
		
		cell = row.createCell(sumaNetto);
		cell.setCellValue(pozycja.getSumaNetto());
		cell.setCellStyle(waluta);
	}


	public void utworzWierszTabeli(int numerWiersza, int numerPozycji, PozycjaDoRaportu pozycja) {
		CellStyle stylDoPrawej = ramkaLewaPrawa;
		CellStyle stylSrodkuj = ramkaLewaPrawaWysrodkowanyTekst;
		CellStyle stylWaluta = walutaRamkaLewaPrawaTekstDoPrawej;
		wpiszPozycjeDoWiersza(numerWiersza, numerPozycji, pozycja, stylDoPrawej, stylSrodkuj, stylWaluta);
	}


	public void utworzOstatniWierszTabeli(int numerWiersza, int numerPozycji, PozycjaDoRaportu pozycja) {
		CellStyle stylDoPrawej = ramkaDolLewaPrawa;
		CellStyle stylSrodkuj = ramkaDolLewaPrawaWysrodkowanyTekst;
		CellStyle stylWaluta = walutaRamkaDolLewaPrawaTekstDoPrawej;
		wpiszPozycjeDoWiersza(numerWiersza, numerPozycji, pozycja, stylDoPrawej, stylSrodkuj, stylWaluta);
	}


	@SuppressWarnings("deprecation")
	public void utworzSumaTabeli(int numerWiersza, int wierszPierwszejPozycjiNaStronie, int wierszOstatniejPozycjiNaStronie) {
		String formula = "SUM(F"+wierszPierwszejPozycjiNaStronie+":F"+wierszOstatniejPozycjiNaStronie+")";
		Row row = arkusz.createRow(numerWiersza);
		row.setHeightInPoints(wysokoscWiersza);
		int sumaStrony = 5; // ostatnia komorka
		
		Cell cell = row.createCell(sumaStrony);
		cell.setCellStyle(walutaRamkaDolLewaPrawaTekstDoPrawej);
	    cell.setCellType(XSSFCell.CELL_TYPE_FORMULA);
	    cell.setCellFormula(formula);
//	    System.out.println(formula);
	    sumyStron.add("F"+(numerWiersza+1));
	}
	
	
	public void utworzKoncoweNapisy(int numerPozycji, int numerWiersza) {
		Row row = arkusz.createRow(numerWiersza+3);
		row.setHeightInPoints(wysokoscWiersza);
		Cell cell;
		
		cell = row.createCell(1);
		cell.setCellValue("Spis zakończono na pozycji: "+(numerPozycji+1));
		cell.setCellStyle(samTekstBezRamki);

		row = arkusz.createRow(numerWiersza+4);
		cell = row.createCell(4);
		cell.setCellValue("Sporządził:");
		cell.setCellStyle(wysrodkowanyTekstBezRamki);
	}
	
	
	public void utworzArkuszPodsumowanie() {
		Row row;
		Cell cell;
		int wiersz = 2;
		int i = 1;
		
		row = podsumowanie.createRow(wiersz);
		row.setHeightInPoints(wysokoscWiersza);
		
		cell = row.createCell(1);
		cell.setCellValue("Podsumowanie");
		cell.setCellStyle(wysrodkowanyTekstBezRamki);
		cell = row.createCell(2);
		podsumowanie.addMergedRegion(new CellRangeAddress(2, 2, 1, 2));
		
		row = podsumowanie.createRow(++wiersz);
		cell = row.createCell(1);
		cell.setCellValue("Strona");
		cell.setCellStyle(ramkaDookolaWysrodkowanyTekst);
		cell = row.createCell(2);
		cell.setCellValue("Wartość");
		cell.setCellStyle(ramkaDookolaWysrodkowanyTekst);
		
		for (String suma: sumyStron){
			row = podsumowanie.createRow(++wiersz);
			row.setHeightInPoints(wysokoscWiersza);
			
			cell = row.createCell(1);
			cell.setCellValue("Strona "+(i++));	
			cell.setCellStyle(ramkaDookola);
	
			cell = row.createCell(2);
			cell.setCellFormula("remanent!"+suma);	
			cell.setCellStyle(ramkaDookola);
		}
		
		row = podsumowanie.createRow(++wiersz);
		row.setHeightInPoints(wysokoscWiersza);
		cell = row.createCell(2);
		cell.setCellStyle(ramkaDookola);
		cell.setCellFormula("sum(C5:C"+wiersz+")");
		
	}
	
	private void ustawSzerokoscKolumnPozycji(XSSFSheet arkusz) {
		arkusz.setColumnWidth(0,kolumnaLP); 
	 	arkusz.setColumnWidth(1,kolumnaNazwaTowaru); 
	 	arkusz.setColumnWidth(2,kolumnaJM); 
	 	arkusz.setColumnWidth(3,kolumnaIlosc);
	 	arkusz.setColumnWidth(4,kolumnaCenaNetto);
	 	arkusz.setColumnWidth(5,kolumnaSumaNetto);
	}


	private void utworzStyle(XSSFWorkbook skoroszyt) {
		this.ramkaLewaPrawa = new StyleKomorek(skoroszyt).ramkaLewaPrawa();
		this.ramkaDolLewaPrawa = new StyleKomorek(skoroszyt).ramkaDolLewaPrawa();
		this.ramkaGoraLewaPrawa = new StyleKomorek(skoroszyt).ramkaGoraLewaPrawa();
		this.ramkaDookolaWysrodkowanyTekst = new StyleKomorek(skoroszyt).ramkaDookolaWysrodkowanyTekst();
		this.ramkaLewaPrawaWysrodkowanyTekst = new StyleKomorek(skoroszyt).ramkaLewaPrawaWysrodkowanyTekst();
		this.ramkaDolLewaPrawaWysrodkowanyTekst = new StyleKomorek(skoroszyt).ramkaDolLewaPrawaWysrodkowanyTekst();
		this.ramkaGoraLewaPrawaWysrodkowanyTekst = new StyleKomorek(skoroszyt).ramkaGoraLewaPrawaWysrodkowanTekst();
		this.wysrodkowanyTekstBezRamki  = new StyleKomorek(skoroszyt).wysrodkowanyTekstBezRamki();
		this.tekstBezRamkiDoPrawej = new StyleKomorek(skoroszyt).tekstBezRamkiDoPrawej();
		this.walutaRamkaGoraLewaPrawaTekstDoPrawej = new StyleKomorek(skoroszyt).walutaRamkaGoraLewaPrawaTekstDoPrawej();
		this.walutaRamkaDolLewaPrawaTekstDoPrawej = new StyleKomorek(skoroszyt).walutaRamkaDolLewaPrawaTekstDoPrawej();
		this.walutaRamkaLewaPrawaTekstDoPrawej = new StyleKomorek(skoroszyt).walutaRamkaLewaPrawaTekstDoPrawej();
		this.samTekstBezRamki = new StyleKomorek(skoroszyt).samTekstBezRamki();
		this.ramkaDookola = new StyleKomorek(skoroszyt).ramkaDookola();
		
	}




}
