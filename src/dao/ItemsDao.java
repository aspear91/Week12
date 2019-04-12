package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Item;

public class ItemsDao {

	
	private Connection connection;

	private final String GET_LOOT_BANK_QUERY = "select * from loot_bank";
	private final String SELECT_RW = "select weapon from loot_table_weapons order by rand() limit 1"; 
	private final String SELECT_RA = "select concat(armor_type, ' ', body_slot) from loot_table_armor order by rand() limit 1";
	private final String CREATE_RANDOM_LOOT = "insert into loot_bank(quality, item, plus_stat) values(?, ?, ?)";
	private final String ASSIGN_ITEM_OWNERSHIP = "update loot_bank set character_id = ? where id = ?";
	private final String UNASSIGN_ITEM_OWNERSHIP = "update loot_bank set character_id = null where id = ?";
	private final String DELETE_ITEM_BY_ID = "delete from loot_bank where id = ?";

	public ItemsDao(){
		connection = DBConnection.getConnection();
	}
	
	public String randomAttribute() {
		double random = Math.random() * 96;
		if ( random <= 16) {
			return "Strength";
		} else if (random <= 32) {
			return "Finesse";
		} else if (random <= 48) {
			return "Intelligence";
		} else if (random <= 64) {
			return "Constitution";
		} else if (random <= 80) {
			return "Wits";
		} else {
			return "Memory";
		}
	}
	public String randomQuality() {
		double random = Math.random() * 100;
		if ( random < .01) {
			return "Legendary";
		} else if (random < 2) {
			return "Epic";
		} else if (random < 17) {
			return "Rare";
		} else if (random < 37) {
			return "Uncommon";
		} else {
			return "Common";
		}
	}
	public List<Item> getLootBank() throws SQLException {
		List<Item> items = new ArrayList<Item>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_LOOT_BANK_QUERY);
		
		while (rs.next()) {
			items.add(new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			System.out.println("id: " + rs.getInt(1) + "  Name: " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7));
		}
		return items;
	}
	/*public void getLootBank() throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_LOOT_BANK_QUERY);
		
		while (rs.next()) {
			System.out.println("id: " + rs.getInt(1) + "  Item: " + rs.getString(5) + " " + rs.getString(6) + " +" + rs.getString(7));
		}
	}*/
	
	public String RW() throws SQLException{
		PreparedStatement ps = connection.prepareStatement(Math.random() > .5 ? SELECT_RW : SELECT_RA);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getString(1);
	}
	
	public void createWeapon(String quality, String item, String plus_stat) throws SQLException {

		PreparedStatement ps = connection.prepareStatement(CREATE_RANDOM_LOOT);
		ps.setString(1, quality);
		ps.setString(2, item);
		ps.setString(3, plus_stat);
		ps.executeUpdate();
	}
	
	public void assignOwnership(int character_id, int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(ASSIGN_ITEM_OWNERSHIP);
		ps.setInt(1, character_id);
		ps.setInt(2, id);
		ps.executeUpdate();
	}
	
	public void removeOwnership(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UNASSIGN_ITEM_OWNERSHIP);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
	
	public void deleteItem(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_ITEM_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}

