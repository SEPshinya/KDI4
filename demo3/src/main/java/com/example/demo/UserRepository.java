package com.example.demo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//sql
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT * FROM jyusyoroku AS p WHERE p.delete_flg=0", nativeQuery = true)
	public Page<User> findAll(Pageable pageable);

	@Query(value = "SELECT * FROM jyusyoroku AS p WHERE p.address like %:address%", nativeQuery = true)
	public Page<User> findUsers(@Param("address")String address,Pageable pageable);
}
