package co.com.simulator;


import co.com.simulator.dbo.Users;
import co.com.simulator.dbo.UserDBORepository;
import co.com.simulator.user.User;
import co.com.simulator.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
public class R2dbcPostgresAdapter implements UserRepository {

    private final UserDBORepository userDBORepository;
    private final ModelMapperSimulator<Users> modelMapperSimulator = new ModelMapperSimulator<>(Users.class);

    @Override
    public Flux<User> listAllUsers() {
        return null;
    }

    @Override
    public Mono<User> createUser(User user) {
        var userDBO = new Users();
        userDBO.setUserId(user.getUserId());
        userDBO.setName(user.getName());
        userDBO.setDocumentType(user.getDocumentType());
        userDBO.setDocumentNumber(user.getDocumentNumber());
        userDBO.setDocumentNumber(user.getDocumentNumber());
        return userDBORepository.save(userDBO)
                .map(modelMapperSimulator::toUser);
    }

    @Override
    public Mono<User> updateUser(User user) {
        return null;
    }

    @Override
    public Mono<Boolean> deleteUser(String userId) {
        return null;
    }
}
