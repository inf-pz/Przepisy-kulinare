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
	
	public void saveComment(Comment comment){
		commentDao.update(comment);
	}
	public void deleteComment(Comment comment){
		commentDao.delete(comment);
	}
	
	public void deleteComments(User user){
		List<Comment> comments = getComments(user);
		for (Comment comment: comments) {
			deleteComment(comment);
		}
	}
	public void deleteComments(Przepis przepis){
		List<Comment> comments = getComments(przepis);
		for (Comment comment: comments) {
			deleteComment(comment);
		}
	}
	
	public void flagComment(Comment comment){
		commentDao.flagComment(comment, true);
	}
	public void unflagComment(Comment comment){
		commentDao.flagComment(comment, false);
	}
	public List<Comment> getFlaggedComments(){
		return commentDao.getFlaggedComments();
		
	}
}
