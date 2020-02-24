package liu.springboot;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

    @Autowired
    private UserManager manager;

    public User getUserById(int id) {
        return manager.getUserById(id);
    }

    public List<User> listUser() {
        return manager.listUser();
    }
}
