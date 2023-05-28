package com.example.demo2.dao;

import com.example.demo2.entities.Computer;

import java.util.List;

public interface ComputerDao {
    void insert(Computer computer);

    void update(Computer computer);

    void deleteById(Integer id);

    Computer findById(Integer id);

    List<Computer> findAll();
}
