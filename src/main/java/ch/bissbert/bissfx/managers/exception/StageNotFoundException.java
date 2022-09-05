package ch.bissbert.bissfx.managers.exception;

public class StageNotFoundException extends IllegalArgumentException {
    public StageNotFoundException(String stageName) {
        super("Stage with name " + stageName + " does not exist");
    }
}
