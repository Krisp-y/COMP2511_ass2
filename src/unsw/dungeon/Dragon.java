package unsw.dungeon;

public class Dragon extends Enemy {
    public Dragon(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
    }   

    //Override moveable to launch fireball each time dragon changes direction
    public void yeetFireBall() {
        //need to do this each time it changes direction
        
        //create a fireball at the same position as dragon
        FireBall fb = new FireBall(this.getDungeon(),this.getX(),this.getY());
    }

    private Dungeon getDungeon() {
        return dungeon;
    }
}