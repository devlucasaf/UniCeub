package reativo;

public class Alert {
    private final String    message;
    private final Severity  severity;

    public Alert(String message, Severity severity) {
        this.message = message;
        this.severity = severity;
    }

    @Override
    public String toString() {
        return "Alert{message='" + message + "', severity=" + severity + "}";
    }
}