package com.example.demo;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * ユーザー情報 Repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Page<User> findAll(Pageable pageable);

	//@Query(value = "SELECT * FROM User AS p WHERE p.address = 1", nativeQuery = true)

	@Query("from user u where u.address like " )
	public Page<User> findUsers(@Param("address")String address, Pageable pageable);
}


