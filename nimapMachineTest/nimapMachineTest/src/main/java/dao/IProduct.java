package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Product;

public interface IProduct extends JpaRepository<Product, Integer>{

}
