package com.bookStore.bookStore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.bookStore.entity.MyBookList;
import com.bookStore.bookStore.repository.MyBookRepository;

@Service
public class MyBookListService {

	@Autowired
	private MyBookRepository myBookListRepository;

	public void saveMyBooks(MyBookList myBookList) {
		myBookListRepository.save(myBookList);
	}

	public List<MyBookList> getAllMyBooks() {
		return myBookListRepository.findAll();
	}

	public void deleteById(int id) {
		myBookListRepository.deleteById(id);
	}
}
