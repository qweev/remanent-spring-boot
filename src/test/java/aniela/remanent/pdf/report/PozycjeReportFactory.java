package aniela.remanent.pdf.report;

import aniela.remanent.position.abstraction.PositionInterface;
import aniela.remanent.position.netto.PozycjaDoRaportuNetto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class PozycjeReportFactory {

    private static String LONG_UNIT_NAME = "JakiśćTowarŻŹŹćźóęąĄĘÓłŁślóÓjvvńóóęttTWYÓóÓPRWTWAIU";

    private Map<Integer, Integer> amountMapping = new HashMap<>();
    private Map<Integer, String> unitMapping;
    private Map<Integer, String> positionNames;
    int positionNumberShifter = 1;

    public PozycjeReportFactory() {
        IntStream.range(1, 9).forEach(value -> {
            amountMapping.putIfAbsent(value, value);
        });
        unitMapping = fillUnits();
        positionNames = getPositions();
    }

    public List<PositionInterface> generateListOfPozycjaDoRaportuNettoContentSame(int amountToCreate) {
        List<PositionInterface> positions = new ArrayList<>(amountToCreate);
        getNumberOfPositionsAsIntStream(amountToCreate).forEach(value -> {
            positions.add(createPozycjaDoRaportu(value));
        });
        return positions;
    }

    public List<PositionInterface> generateListOfPozycjaDoRaportuNettoContentRandom(int amountToCreate) {
        List<PositionInterface> positions = new ArrayList<>(amountToCreate);
        getNumberOfPositionsAsIntStream(amountToCreate).forEach(value -> {
            positions.add(createPozycjaDoRaportuRandom(value));
        });
        return positions;
    }

    public List<PositionInterface> generateListOfPozycjaDoRaportuNettoContentMixed(int amountToCreate) {
        List<PositionInterface> positions = new ArrayList<>(amountToCreate);
        getNumberOfPositionsAsIntStream(amountToCreate).forEach(value -> {
            if(value%2 == 0)
                positions.add(createPozycjaDoRaportuRandom(value));
            else
                positions.add(createPozycjaDoRaportu(value));
        });
        return positions;
    }

    private PozycjaDoRaportuNetto createPozycjaDoRaportu(int number) {
        PozycjaDoRaportuNetto pozycjaDoRaportuNetto = new PozycjaDoRaportuNetto();
        pozycjaDoRaportuNetto.setPozycjaWRaporcie(number);
        pozycjaDoRaportuNetto.setNazwaTowaru(LONG_UNIT_NAME);
        pozycjaDoRaportuNetto.setIlosc(ThreadLocalRandom.current().nextInt(1,20));
        pozycjaDoRaportuNetto.setCenaNetto(3.11);
        pozycjaDoRaportuNetto.setCenaBrutto(4.11);
        pozycjaDoRaportuNetto.setJednostka("szt");
        pozycjaDoRaportuNetto.setSuma(pozycjaDoRaportuNetto.getCenaNetto(), pozycjaDoRaportuNetto.getIlosc());
        return pozycjaDoRaportuNetto;
    }


    private PozycjaDoRaportuNetto createPozycjaDoRaportuRandom(int number) {
        PozycjaDoRaportuNetto pozycjaDoRaportuNetto = new PozycjaDoRaportuNetto();
        pozycjaDoRaportuNetto.setPozycjaWRaporcie(number);
        pozycjaDoRaportuNetto.setNazwaTowaru(positionNames.getOrDefault(ThreadLocalRandom.current().nextInt(1,4),"Wartosc domyslna dla nazwy"));
        pozycjaDoRaportuNetto.setIlosc(generatePseudoDouble());
        double value = generatePseudoDouble();
        pozycjaDoRaportuNetto.setCenaNetto(value);
        pozycjaDoRaportuNetto.setCenaNetto(value + 1);
        pozycjaDoRaportuNetto.setJednostka(unitMapping.getOrDefault(ThreadLocalRandom.current().nextInt(1,4),"XXX"));
        pozycjaDoRaportuNetto.setSuma(pozycjaDoRaportuNetto.getCenaNetto(), pozycjaDoRaportuNetto.getIlosc());
        return pozycjaDoRaportuNetto;
    }


    private Map<Integer, String> fillUnits() {
        Map<Integer, String> units = new HashMap<>();
        units.put(1, "szt.");
        units.put(2, "kpl.");
        units.put(3, "mb.");
        units.put(3, "op.");
        return units;
    }

    private Map<Integer, Double> fillNetto() {
        Map<Integer, Double> mapping = new HashMap<>();
        IntStream.range(1, 9).forEach(value -> {
            double doubleRandom = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(1, 245)).setScale(2,
                RoundingMode.HALF_UP)
                .doubleValue();
            mapping.putIfAbsent(value, doubleRandom);
        });
        return mapping;
    }

    private Map<Integer, String> getPositions() {
        Map<Integer, String> positions = new HashMap<>();
        positions.put(1, LONG_UNIT_NAME);
        positions.put(2, "cos dluzszego");
        positions.put(3, "Znakow to tu bedzie jakies 29");
        return positions;
    }

    public double generatePseudoDouble(){
        double  result = Math.random() + ThreadLocalRandom.current().nextDouble(1,9);
        return  BigDecimal.valueOf(result).setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    private IntStream getNumberOfPositionsAsIntStream(int amountToCreate) {
        return IntStream.range(positionNumberShifter, amountToCreate + positionNumberShifter);
    }

}
