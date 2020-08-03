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

    public void setStatus(boolean b) {
        this.live = b;
    }

    @Override
    public void handleCollision(Moveable m) {
        if(m instanceof Player) {
            Player p = (Player)m;
            p.move(p.getDirection());
            if (this.getStatus() == false) {
                //sort casting of m to player
                p.collectItem(this);
            } else {
                p.kill();
            }
        }
        
        if (m instanceof Enemy) {
            if (live) {
                Enemy e = (Enemy) m;
                e.move(e.getDirection());
                e.kill();   
            }
        }

    }
}