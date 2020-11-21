package notify.alignments;

public enum HorizontalAlignment {
    LEFT(0), RIGHT(1), CENTER(2);

    private int alignValue;

    HorizontalAlignment(int alignValue) {
        this.alignValue = alignValue;
    }
}
