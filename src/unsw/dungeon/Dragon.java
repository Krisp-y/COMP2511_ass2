package unsw.dungeon;

public class Dragon extends Enemy {
    private static final int STEP_COUNT = 2;
    private static final int SHOOT_COUNT = 4;
    private int counter;
    
    public Dragon(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        counter = 1;
    }

    //Override moveable to launch fireball each time dragon changes direction
    public void yeetFireBall() {
        //need to do this each time it changes direction
        //create a fireball at the same position as dragon
        //How do we load a fireball mid game
        FireBall fb = new FireBall(dungeon, getX(), getY(), getDirection());
        dungeon.addDynamicEntity(fb);
        
    }
    
    @Override
    public void tick() {
        if (counter % STEP_COUNT == 0) {
            super.tick();
        }
        if (counter % SHOOT_COUNT == 0) {
            yeetFireBall();
        }
        counter++;
    }
}