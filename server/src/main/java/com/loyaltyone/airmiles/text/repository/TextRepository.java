package com.loyaltyone.airmiles.text.repository;

import com.loyaltyone.airmiles.text.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepository extends JpaRepository<Text, Long> {
}
