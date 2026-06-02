package dao;

import java.util.List; 

public interface RepositoryDAO<T> {
	public Long insert(T о) throws Exception; 
	public void update(T o) throws Exception; 
	public void delete(Long Id) throws Exception; 
	public T findById(Long Id) throws Exception;  
	public List<T> findAll() throws Exception;
}
