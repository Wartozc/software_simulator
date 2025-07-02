package co.com.simulator.dbo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDBORepository extends ReactiveCrudRepository<Users, String> {
}
