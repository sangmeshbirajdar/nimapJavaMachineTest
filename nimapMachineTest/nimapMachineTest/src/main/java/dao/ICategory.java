package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Category;

public interface ICategory extends JpaRepository<Category, Integer>{

}
