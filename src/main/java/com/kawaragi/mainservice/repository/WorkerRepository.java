package com.kawaragi.mainservice.repository;

import com.kawaragi.mainservice.dtos.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query(value = "select * from worker where uemail = ?1", nativeQuery = true)
    Optional<Worker> findByEmail(String email);
    @Query(value = "select * from worker", nativeQuery = true)
    List<Worker> getAllWorkers();
    @Query(value = "select * from worker where uposition = ?1", nativeQuery = true)
    List<Worker> getWorkerWithThisPosition(String position);
}
