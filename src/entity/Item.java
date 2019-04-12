package entity;

public class Item {
	private int id;
	private String quality;
	private String item;
	private String plus_stat;
	
	public Item(int id, String quality, String item, String plus_stat) {
		this.setId(id);
		this.setQuality(quality);
		this.setItem(item);
		this.setPlus_stat(plus_stat);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getPlus_stat() {
		return plus_stat;
	}

	public void setPlus_stat(String plus_stat) {
		this.plus_stat = plus_stat;
	}
}
