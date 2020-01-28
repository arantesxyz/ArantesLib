package dev.arantes.lib.utils.formats;

public enum Portuguese {
    K("Mil", "Mil"),
    M("Milhão", "Milhões"),
    B("Bilhão", "Bilhões"),
    T("Trilhão", "Trilhões"),
    Q("Quadrilhão", "Quadrilhões"),
    L("Quintilhão", "Quintilhões");

    private String single;
    private String plural;

    Portuguese(String single, String plural) {
        this.single = single;
        this.plural = plural;
    }

    public String toString(String value) {
        if (value.startsWith("1")) {
            return single;
        }
        return plural;
    }
}
