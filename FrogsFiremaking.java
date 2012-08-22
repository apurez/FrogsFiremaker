package firemaker;

import org.powerbot.concurrent.Task;
import org.powerbot.concurrent.strategy.Strategy;
import org.powerbot.game.api.ActiveScript;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.util.Time;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.Item;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.bot.event.MessageEvent;
import org.powerbot.game.bot.event.listener.*;

@Manifest(authors = "Frogs482", name = "FrogsFiremaking", description = "Burns logs in a bonfire", version = 1.0)
public class FrogsFiremaking extends ActiveScript implements MessageListener {

	@Override
	protected void setup() {
		provide(new Light());
	}

	public class Light extends Strategy implements Task {
		@Override
		public void run() {
			NPC BANKER = NPCs.getNearest(Cons.Banker);

			final SceneObject Bonfire = SceneEntities
					.getNearest(Cons.BONFIRE_ID);
			Item LOG = Inventory.getItem(Cons.Logs);

			if (Bonfire != null && Bonfire.isOnScreen() && Inventory.isFull()) {
				Bonfire.interact("Add-logs");
				Time.sleep(500, 900);

			} else if (!Bonfire.isOnScreen() && !Players.getLocal().isMoving()) {

				Walking.newTilePath(Cons.BONFIRE_TILE);
				if (Cons.BONFIRE_SPOT.contains(Players.getLocal())) {
					LOG.getWidgetChild().interact("Light"); // If there is no
															// bonfire in sight,
															// light the first
															// log
					Time.sleep(200, 300);
					Bonfire.interact("Add-logs");
				}
				if (Inventory.getCount(Cons.Logs) == 0) {
					Time.sleep(200, 3000);
					BANKER.interact("Bank");
					Time.sleep(300, 500);
					Bank.depositInventory();
					if (Bank.getItem(Cons.Logs) != null) {
						Bank.withdraw(Cons.Logs[0], 0);
						Time.sleep(500, 900);
					}
				}

			}
		}

		public boolean validate() {
			return Inventory.getItem(Cons.Logs) != null && Inventory.isFull();
		}
	}
		public void messageReceived(MessageEvent e) {
			String message = e.getMessage().toLowerCase();
			if (message.contains("A fire spirit emerges")) {

				Cons.RewardCollect();
			}

		}
	
}
