package unsw.dungeon;

import org.json.JSONObject;

public class DungeonTestLoader extends DungeonLoader {
    
    public DungeonTestLoader(JSONObject dungeon_json) {
        super(dungeon_json);
    }
    
    @Override
    public void onLoad(Entity player) {
        return;
    }

    @Override
    public void onLoad(Wall wall) {
        return;
    }
}