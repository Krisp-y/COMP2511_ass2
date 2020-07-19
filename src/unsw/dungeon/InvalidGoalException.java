package unsw.dungeon;

public class InvalidGoalException extends IllegalArgumentException {

    /**
     *
     */
    private static final long serialVersionUID = -1852994682611294154L;
    
    public InvalidGoalException(String message) {
        super(message);
    }
}