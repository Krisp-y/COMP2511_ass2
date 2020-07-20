package unsw.dungeon;

public interface GoalPublisher {
    public void subscribe(GoalSubscriber gs);
    public void unsubscribe(GoalSubscriber gs);
    public void notifySubscribers();
}