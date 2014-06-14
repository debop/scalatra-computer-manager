package org.scalatra.computer.data.repository;

import org.scalatra.computer.data.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


public interface ComputerRepository extends JpaRepository<Computer, Long>, QueryDslPredicateExecutor<Computer> {
}
