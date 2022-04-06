package model;

public class ServiceResult<T> {

    private boolean hasError;
    private String error;
    private T result;

    public ServiceResult (T result) {
        this(result, false, "");
    }

    public ServiceResult(T result, boolean hasError, String error) {
        this.hasError = hasError;
        this.error = error;
        this.result = result;
    }

    public boolean hasError() {
        return hasError;
    }

    public String getError() {
        return error;
    }

    public T getResult() {
        return result;
    }
}
