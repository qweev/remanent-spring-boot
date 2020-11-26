
DROP DATABASE IF EXISTS sklep;
CREATE DATABASE sklep /*!40100 DEFAULT CHARACTER SET utf8 */;
commit;
use sklep;
commit;

DROP TABLE IF EXISTS sklep.pozycje;
commit;

CREATE TABLE  sklep.pozycje (
  id int(10) unsigned NOT NULL AUTO_INCREMENT,
  nazwa_towaru varchar(60) COLLATE utf8_polish_ci NOT NULL,
  cena_brutto decimal(10,2) NOT NULL,
  cena_netto decimal(10,2) NOT NULL,
  ilosc decimal(10,2) NOT NULL,
  uzytkownik varchar(15) COLLATE utf8_polish_ci NOT NULL,
  jednostka varchar(10) COLLATE utf8_polish_ci NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
commit;
