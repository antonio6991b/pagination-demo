package ru.bolgov.spring.pagination.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bolgov.spring.pagination.model.Car;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    List<Car> findAll();

    @Query(value = "select * from car c limit ?1, ?2", nativeQuery = true)
    List<Car> findByPage(int page, int limit);
}
