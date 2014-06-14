package org.scalatra.computer.data.repository;

import org.scalatra.computer.data.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


public interface CompanyRepository extends JpaRepository<Company, Long>, QueryDslPredicateExecutor<Company> {
}
