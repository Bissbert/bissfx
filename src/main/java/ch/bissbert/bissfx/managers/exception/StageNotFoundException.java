package ch.bissbert.bissfx.managers.exception;

/**
 * An exception thrown when a stage is not found.
 * @author Bissbert
 */
public class StageNotFoundException extends IllegalArgumentException {
    public StageNotFoundException(String stageName) {
        super("Stage with name " + stageName + " does not exist");
    }
}
