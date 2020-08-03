package unsw.dungeon;

public class LandMine extends Entity implements Collider{
    private boolean live;

    public LandMine(int x, int y) {
        super(x, y);
        this.live = false;

    }
    
    public boolean getStatus() {
        return this.live;
    }

    @Override
    public void handleCollision(Moveable m) {
        if(m instanceof Player && this.getStatus() == false) {
            //sort casting of m to player
            Player p = (Player)m;
            p.collectItem(this);
        }

    }
}