package l2k.demo.multiple.chats.game;

import java.util.List;

public class RollCall<T> {
	
	private int itemIdx = 0;
	private List<T> items;

	public RollCall(List<T> items) {
		this.items = items;
	}

	public int getCompletedItemCount() {
		return itemIdx - 1;
	}

	public int getTotalItemCount() {
		return items.size();
	}

	public T getNextItem() {
		int nextItemIdx = itemIdx++;
		
		if(nextItemIdx >= items.size()) {
			return null;
		} else {			
			T item = items.get(nextItemIdx);
			return item;			
		}
	}

	public boolean isFinished() {
		return itemIdx >= items.size();
	}
	
}
