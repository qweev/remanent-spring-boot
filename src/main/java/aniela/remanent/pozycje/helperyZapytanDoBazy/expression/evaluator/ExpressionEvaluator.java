package aniela.remanent.pozycje.helperyZapytanDoBazy.expression.evaluator;

import aniela.remanent.pozycje.bazaDanych.NazwyPolTabeliPozycje;
import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;
import aniela.remanent.pozycje.helperyZapytanDoBazy.expression.ExpressionStrategy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {

    private final ExpressionStrategy expressionStrategy = new ExpressionStrategy();
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<PozycjaBazy> criteriaQuery;
    private Root<PozycjaBazy> root;
    private List<Predicate> predicates;

    public ExpressionEvaluator(CriteriaBuilder criteriaBuilder) {
        predicates = new ArrayList<>();
        this.criteriaBuilder = criteriaBuilder;
        criteriaQuery = this.criteriaBuilder.createQuery(PozycjaBazy.class);
        root = criteriaQuery.from(PozycjaBazy.class);
        criteriaQuery.select(root);
    }

    public ExpressionEvaluator withTowar(String nazwaTowaru) {
        expressionStrategy.getExpression(nazwaTowaru)
            .apply(criteriaBuilder, root, NazwyPolTabeliPozycje.NAZWA_TOWARU, nazwaTowaru).ifPresent(expression -> {
            predicates.add(expression);
        });
        return this;
    }

    public ExpressionEvaluator withUzytkownik(String uzytkownik) {
        expressionStrategy.getExpression(uzytkownik)
                .apply(criteriaBuilder, root, NazwyPolTabeliPozycje.UZYTKOWNIK, uzytkownik).ifPresent(expression -> {
            predicates.add(expression);
        });
        return this;
    }

    public ExpressionEvaluator withCenaNetto(int radioNetto, double cenaNetto) {
        expressionStrategy.getExpression(radioNetto)
                .apply(criteriaBuilder, root, NazwyPolTabeliPozycje.CENA_NETTO, cenaNetto).ifPresent(expression -> {
            predicates.add(expression);
        });
        return this;
    }

    public ExpressionEvaluator withCenaBrutto(int radioBrutto, double cenaBrutto) {
        expressionStrategy.getExpression(radioBrutto)
                .apply(criteriaBuilder, root, NazwyPolTabeliPozycje.CENA_BRUTTO, cenaBrutto).ifPresent(expression -> {
            predicates.add(expression);
        });
        return this;
    }

    public ExpressionEvaluator withIlosc(int radioIlosc, double ilosc) {
        expressionStrategy.getExpression(radioIlosc)
                .apply(criteriaBuilder, root, NazwyPolTabeliPozycje.ILOSC, ilosc).ifPresent(expression -> {
            predicates.add(expression);
        });
        return this;
    }

    public CriteriaQuery<PozycjaBazy> evaluate() {

        Predicate[] predicatesAsArray = new Predicate[predicates.size()];
        predicatesAsArray = predicates.toArray(predicatesAsArray);
        criteriaQuery.where(criteriaBuilder.and(predicatesAsArray));
        return criteriaQuery;
    }

}
