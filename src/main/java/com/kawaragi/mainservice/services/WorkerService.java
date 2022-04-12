package com.kawaragi.mainservice.services;

import com.kawaragi.mainservice.dtos.Position;
import com.kawaragi.mainservice.dtos.Worker;
import com.kawaragi.mainservice.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkerService {
    public final WorkerRepository repo;

    public List<Worker> getAllWorkers() {
        return repo.getAllWorkers();
    }

    public List<Worker> getWorkersWithThisPosition(Position position) {
        return repo.getWorkerWithThisPosition(position.name());
    }

    public Worker getWorkerById(Long id) {
        return repo.getById(id);
    }
    public Worker getWorkerByEmail(String email){
        return repo.findByEmail(email).orElseThrow(() -> new NullPointerException("User not found"));
    }

    public void addWorker(Worker worker) {
        repo.save(worker);
    }

    public void deleteWorker(Worker worker) {
        repo.delete(worker);
    }
}
