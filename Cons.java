package firemaker;

import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Cons {
	
	public static final Area BONFIRE_SPOT = new Area(new Tile[] {
			new Tile(3149, 3474, 0), new Tile(3150, 3474, 0),
			new Tile(3148, 3474, 0), new Tile(3148, 3473, 0),
			new Tile(3149, 3473, 0), new Tile(3150, 3473, 0),
			new Tile(3150, 3474, 0) });
	
	public static final Tile[] BONFIRE_TILE = new Tile[] { new Tile(3149, 3474,0) };
	public static final int FIRE_SPIRIT_ID[] = { 15451 };
	public static final int[] Logs = {1517};
	public static final int Banker[] = { 3293 };
	public static final int BONFIRE_ID[] = { 70758, 70761, 70764 };
	public static final Tile pLocation = Players.getLocal().getLocation();

	public static void RewardCollect() {
		NPC FireSpirit = NPCs.getNearest(FIRE_SPIRIT_ID);
		if (FireSpirit.isOnScreen()) {
			FireSpirit.interact("Collect-reward");
		} else {
			Camera.turnTo(FireSpirit);
			FireSpirit.interact("Collect-reward");
		}
	}

}
