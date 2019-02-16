package com.gistprototype.demo.repository;

import com.gistprototype.demo.model.Gist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface GistRepository extends JpaRepository<Gist, Long> {

	@Query("SELECT t FROM Gist t where t.gistApiType = :gistApiType") 
	Gist findEntityByApiType(@Param("gistApiType") String gistApiType);
	@Transactional
	@Modifying
	@Query("UPDATE Gist SET apiHitCount=:count WHERE gistApiType = :gistApiType") 
	void updateApiCount(@Param("count") Integer count,@Param("gistApiType") String gistApiType);

}
