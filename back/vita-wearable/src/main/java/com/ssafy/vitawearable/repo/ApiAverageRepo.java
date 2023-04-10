package com.ssafy.vitawearable.repo;

import com.ssafy.vitawearable.entity.ApiAverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiAverageRepo extends JpaRepository<ApiAverage,Long> {
}
