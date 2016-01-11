package com.przepisy.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("przepisyDao")
public class PrzepisyDao {

	private NamedParameterJdbcTemplate jdbc;
	
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Przepis> getPrzepisy(){
		
		return jdbc.query("select * from recipes", new RowMapper<Przepis>(){

			@Override
			public Przepis mapRow(ResultSet rs, int rowNum) throws SQLException {
				Przepis przepis = new Przepis();
				
				przepis.setId(rs.getInt("id_recipe"));
				przepis.setName(rs.getString("name"));
				przepis.setText(rs.getString("text"));
				przepis.setStatus(rs.getInt("status"));
				przepis.setMember_id(rs.getInt("member_id"));
				
				return przepis;
			}
			
		});
	}
	
	public Przepis getPrzepis(int id){
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		
		return jdbc.queryForObject("select * from recipes where id_recipe=:id", params, new RowMapper<Przepis>(){

			@Override
			public Przepis mapRow(ResultSet rs, int rowNum) throws SQLException {
				Przepis przepis = new Przepis();
				
				przepis.setId(rs.getInt("id_recipe"));
				przepis.setName(rs.getString("name"));
				przepis.setText(rs.getString("text"));
				przepis.setStatus(rs.getInt("status"));
				przepis.setMember_id(rs.getInt("member_id"));
				
				return przepis;
			}
			
		});
	}
	
	public boolean update(Przepis przepis){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(przepis);
		
		return jdbc.update("update recipes set name=:name, text=:text, status=:status, member_id=:member_id where id_recipe=:id", params) == 1;
		
	}
	
	public boolean create(Przepis przepis){
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(przepis);
		
		return jdbc.update("insert into recipes (name, text, status, member_id) values (:name, :text, :status, :member_id)", params) == 1;
		
	}
	
	public boolean delete(int id){
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from recipes where id_recipe=:id", params) == 1;
	}
}
