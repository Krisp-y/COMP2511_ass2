package unsw.dungeon;

public class Door extends Moveable implements Collider {
    private int ID;
    private boolean doorUnlocked;
    public Door(Dungeon dungeon, int x, int y, int ID) {
        super(dungeon, x, y);
        this.ID = ID;
        this.doorUnlocked = false;
    }

    @Override
    public void handleCollision(Moveable m) {
        if (m instanceof Player) {
            doorCollision((Player) m);
        }
    } 
    
    private void doorCollision(Player p) {
        Direction playerMoveDirection = p.getDirection();
        switch (playerMoveDirection) {
            case UP:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveUp();
                    p.useKey();
                    changeImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) { // If the door has been unlocked go through.
                    p.moveUp();
                }
                break;
            case DOWN:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveDown();
                    p.useKey();
                    changeImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) {
                    p.moveDown();
                }
                break;
            case LEFT:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveLeft();
                    p.useKey();
                    changeImage();
                    doorUnlocked = true;
                } else if (doorUnlocked) {
                    p.moveLeft();
                }
                break;
            case RIGHT:
                if(p.hasKey() && p.getKeyID() == this.getDoorID()) {
                    p.moveRight();
                    p.useKey();
                    changeImage();
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
        return this.ID;
    }
    
    public void changeImage() {
        if (ev != null) 
            ev.changeDoorImage();
    }
}