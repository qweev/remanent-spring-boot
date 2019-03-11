package aniela.remanent;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// TODO
	// nie mam gdzie tego beana wcisnac w inne miejsce ?
	@Bean
	XSSFWorkbook getXSSFWorkbook(){
		return new XSSFWorkbook();
	}

}
