package spring.database.exception;

public class InvalidLottoNumberCountException extends Throwable {
    private String exceptionMessage;

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
