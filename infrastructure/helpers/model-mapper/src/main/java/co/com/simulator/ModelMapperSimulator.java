package co.com.simulator;

import co.com.simulator.user.User;
import org.modelmapper.ModelMapper;

public class ModelMapperSimulator<T> {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private final Class<T> clazz;

    public ModelMapperSimulator(Class<T> clazz) {
        this.clazz = clazz;
    }

    public User toUser(T object) {
        return MODEL_MAPPER.map(object, User.class);
    }

    public T fromUser(User user) {
        return MODEL_MAPPER.map(user, clazz);
    }
}
