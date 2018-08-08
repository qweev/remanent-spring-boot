package aniela.remanent.raport.stronyExcela;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class StyleKomorek {

	private static final short wysokoscCzcionki = 10;
	private static final String fontName = "Calibri";
	private XSSFCellStyle style;
	private XSSFFont font;
	private DataFormat df;
	private static final BorderStyle gruboscRamki = BorderStyle.THIN;

	StyleKomorek(XSSFWorkbook skoroszyt){
		this.font = utworzFont(skoroszyt);
		this.style = skoroszyt.createCellStyle();
	    this.style.setFont(font);
	    this.df = skoroszyt.createDataFormat();
	}
	
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle samTekstBezRamki(){
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle wysrodkowanyTekstBezRamki(){
		style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle tekstBezRamkiDoPrawej(){
		style.setAlignment(CellStyle.ALIGN_RIGHT);
		return style;
	}
	
	public XSSFCellStyle ramkaDookola(){
        style.setBorderBottom(gruboscRamki);
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderTop(gruboscRamki);
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle ramkaDookolaWysrodkowanyTekst(){
        style.setBorderBottom(gruboscRamki);
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderTop(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	
	public XSSFCellStyle ramkaLewaPrawa(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle ramkaLewaPrawaWysrodkowanyTekst(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	
	public XSSFCellStyle ramkaDolLewaPrawa(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderBottom(gruboscRamki);
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle ramkaDolLewaPrawaWysrodkowanyTekst(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderBottom(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	
	
	public XSSFCellStyle ramkaGoraLewaPrawa(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderTop(gruboscRamki);
		return style;
	}

	@SuppressWarnings("deprecation")
	public XSSFCellStyle ramkaGoraLewaPrawaWysrodkowanTekst(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderTop(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_CENTER);
		return style;
	}
	

	@SuppressWarnings("deprecation")
	public XSSFCellStyle walutaRamkaGoraLewaPrawaTekstDoPrawej(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setBorderTop(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
//        style.setDataFormat(df.getFormat("#,##0.00 zł;\\-#,##0.00"));
        style.setDataFormat(df.getFormat("#,##0.00 zł"));
		return style;
	
		
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle walutaRamkaDolLewaPrawaTekstDoPrawej(){
        style.setBorderBottom(gruboscRamki);
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setDataFormat(df.getFormat("#,##0.00 zł;\\-#,##0.00"));
		return style;
	}
	
	@SuppressWarnings("deprecation")
	public XSSFCellStyle walutaRamkaLewaPrawaTekstDoPrawej(){
        style.setBorderLeft(gruboscRamki);
        style.setBorderRight(gruboscRamki);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setDataFormat(df.getFormat("#,##0.00 zł;\\-#,##0.00"));
		return style;
	}
	
	
	private XSSFFont utworzFont(XSSFWorkbook skoroszyt){
		XSSFFont font = skoroszyt.createFont();
		font.setFontHeightInPoints(wysokoscCzcionki);
		font.setFontName(fontName);
	    return font;
	}
}
