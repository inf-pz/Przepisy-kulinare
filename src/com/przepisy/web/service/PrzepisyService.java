package com.przepisy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przepisy.web.dao.Comment;
import com.przepisy.web.dao.Przepis;
import com.przepisy.web.dao.PrzepisyDao;

@Service("przepisyService")
public class PrzepisyService {
	
	private PrzepisyDao przepisyDao;
	
	@Autowired
	public void setPrzepisyDao(PrzepisyDao przepisyDao){
		this.przepisyDao = przepisyDao;
	}
	
	
	public List<Przepis> getPrzepisy(){
		return przepisyDao.getPrzepisy();
	}


	public void createPrzepis(Przepis przepis) {
		przepisyDao.create(przepis);
		
	}
	
	public List<Przepis> getPrzepisy(String username){
		return przepisyDao.getPrzepisy(username);
	}
	
	public Przepis getPrzepis(int id){
		return przepisyDao.getPrzepis(id);
	}
	
	public List<Comment> getComments(Przepis przepis){
		return przepisyDao.getComments(przepis);
	}
	
	public void savePrzepis(Przepis przepis){
		przepisyDao.update(przepis);
	}
}
