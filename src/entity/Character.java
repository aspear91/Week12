package entity;

public class Character {

	private int id;
	private String name;
	private String race;
	private String caste;//because I couldn't type class.
	
	public Character(int id, String name, String race, String caste){
		this.setId(id);
		this.setName(name);
		this.setRace(race);
		this.setCaste(caste);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
