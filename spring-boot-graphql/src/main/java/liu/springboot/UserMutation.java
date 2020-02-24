package liu.springboot;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

    @Autowired
    private UserManager manager;

    public User saveUser(User user) {
        return manager.saveUser(user);
    }

    public User deleteUser(int id) {
        return manager.removeUser(id);
    }

    public User updateUser(User user) {
        return manager.updateUser(user);
    }


}
