package com.ssafy.vitafriend.repository;


import com.ssafy.vitafriend.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BadgeRepository  extends JpaRepository<Badge, Integer> {
}
