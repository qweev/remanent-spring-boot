package  aniela.remanent.pozycje.helperyZapytanDoBazy;

public enum Conditions {

    EMPTY(0),
    EQUALS(1),
    MORE_THAN(2),
    LESS_THAN(3);

    public final int value;

    Conditions(int value) {
        this.value = value;
    }
}
