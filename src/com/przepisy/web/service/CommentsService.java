package com.przepisy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.przepisy.web.dao.Comment;
import com.przepisy.web.dao.Przepis;
import com.przepisy.web.dao.User;
import com.przepisy.web.dao.CommentDao;

@Service("commentsService")
public class CommentsService {
	
	private CommentDao commentDao;
	
	@Autowired
	public void setCommentDao(CommentDao commentDao){
		this.commentDao = commentDao;
	}
	


	public void createComment(Comment comment) {
		commentDao.create(comment);
		
	}
	
	public List<Comment> getComments(Przepis przepis){
		return commentDao.getComments(przepis);
	}
	
	public Comment getComment(int id){
		return commentDao.getComment(id);
	}
	
	public List<Comment> getComments(User user){
		return commentDao.getComments(user);
	}
	
	public void savePrzepis(Comment comment){
		commentDao.update(comment);
	}
}
