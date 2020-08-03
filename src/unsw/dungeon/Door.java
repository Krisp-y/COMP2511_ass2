package unsw.dungeon;

public class Door extends Moveable implements Collider {
    private int id;
    private boolean doorUnlocked;
    public Door(Dungeon dungeon, int x, int y, int id) {
        super(dungeon, x, y);
        this.id = id;
        this.doorUnlocked = false;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            doorCollision((Player) m);
        }
        if (m instanceof Enemy) {
            if (doorUnlocked) {
                m.move(m.getDirection());
            }
        }
    } 
    
    private void doorCollision(Player p) {
        Direction playerMoveDirection = p.getDirection();
        switch (playerMoveDirection) {
            case UP:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveUp();
                    p.useKey();
                    changeDoorOpenImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) { // If the door has been unlocked go through.
                    p.moveUp();
                }
                break;
            case DOWN:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveDown();
                    p.useKey();
                    changeDoorOpenImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) {
                    p.moveDown();
                }
                break;
            case LEFT:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveLeft();
                    p.useKey();
                    changeDoorOpenImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) {
                    p.moveLeft();
                }
                break;
            case RIGHT:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveRight();
                    p.useKey();
                    changeDoorOpenImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) {
                    p.moveRight();
                }
                break;
            default:
                return;
        }
    }
    
    public int getDoorID() {
        return this.id;
    }
    
    public void changeDoorOpenImage() {
        if (ev != null) 
            ev.changeDoorOpenImage();
    }
}