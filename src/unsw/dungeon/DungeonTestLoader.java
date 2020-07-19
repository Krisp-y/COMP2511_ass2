package unsw.dungeon;

import org.json.JSONObject;

public class DungeonTestLoader extends DungeonLoader {
    
    public DungeonTestLoader(JSONObject dungeon_json) {
        super(dungeon_json);
    }
    
    @Override
    public void onLoad(Player player) {
        return;
    }

    @Override
    public void onLoad(Wall wall) {
        return;
    }
    
    @Override
    public void onLoad(Boulder boulder) {
        return;
    }
    
    @Override
    public void onLoad(Exit exit) {
        return;
    }
}