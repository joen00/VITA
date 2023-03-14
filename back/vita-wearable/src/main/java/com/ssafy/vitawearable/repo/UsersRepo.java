package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.DailyWearable;
import com.ssafy.vitawearable.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepo extends JpaRepository<Users,String> {
}
