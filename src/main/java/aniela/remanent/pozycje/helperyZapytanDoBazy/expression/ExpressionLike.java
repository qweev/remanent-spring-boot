package aniela.remanent.pozycje.helperyZapytanDoBazy.expression;

import aniela.remanent.pozycje.bazaDanych.PozycjaBazy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Optional;

public class ExpressionLike implements ExpressionContract {

    @Override
    public Optional<Predicate> apply(CriteriaBuilder criteriaBuilder, Root<PozycjaBazy> root, String property,
                                     double value) {
        throw new UnsupportedOperationException("ExpressionLike against double value not supported ...");
    }

    @Override
    public Optional<Predicate> apply(CriteriaBuilder criteriaBuilder, Root<PozycjaBazy> root, String property,
                                     String value) {
        return Optional.of(criteriaBuilder.like(root.get(property), "%" + value + "%"));
    }
}
