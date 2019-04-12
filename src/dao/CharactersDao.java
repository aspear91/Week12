package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Character;

public class CharactersDao {

	private Connection connection;
	
	private final String GET_CHARACTERS_QUERY = "select * from characters";
	private final String GET_CHARACTER_BY_ID_QUERY = "select * from characters where id = ?";
	private final String GET_CHARACTER_INVENTORY_BY_CHARACTER_ID = "select * from loot_bank where character_id = ?";
	private final String CREATE_CHARACTER = "insert into characters(name, race, class) values(?, ?, ?)";
	private final String DELETE_CHARACTER_BY_ID = "delete from characters where id = ?";
	
	public CharactersDao(){
		connection = DBConnection.getConnection();
	}
	
	public List<Character> getCharacters() throws SQLException {
		List<Character> characters = new ArrayList<Character>();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(GET_CHARACTERS_QUERY);
		
		while (rs.next()) {
			characters.add(new Character(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			System.out.println("id: " + rs.getInt(1) + "  Name: " + rs.getString(2));
		} return characters;
	}
	
	public Character getCharacterByID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CHARACTER_BY_ID_QUERY);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		Character character = new Character(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
		return character;
	}
	
	public void getCharacterInventoryByCharacterID(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_CHARACTER_INVENTORY_BY_CHARACTER_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("Item: " + rs.getString(5) + " " + rs.getString(6) + " +" + rs.getString(7));
		}
	}
	
	public void createCharacter(String name, String race, String caste) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_CHARACTER);
		ps.setString(1, name);
		ps.setString(2, race);
		ps.setString(3, caste);
		ps.executeUpdate();
	}
	
	public void deleteCharacter(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_CHARACTER_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}
}
