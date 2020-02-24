package liu.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserManager {

    private static Map<Integer, User> cacheMap = new HashMap<>();

    static {
        cacheMap.put(1, new User(1, "liu1", "111", 21));
        cacheMap.put(2, new User(2, "liu2", "222", 22));
        cacheMap.put(3, new User(3, "liu3", "333", 23));
        cacheMap.put(4, new User(4, "liu4", "444", 24));
    }

    public User getUserById(int id) {
        return cacheMap.get(id);
    }

    public List<User> listUser() {
        return new ArrayList<>(cacheMap.values());
    }

    public User saveUser(User user) {
        cacheMap.put(user.getId(), user);
        printCache();
        return user;
    }

    public User removeUser(int id) {
        User user = cacheMap.remove(id);
        printCache();
        return user;
    }

    public User updateUser(User user) {
        int id = user.getId();

        User oldUser = cacheMap.get(id);

        if (oldUser == null) {
            cacheMap.put(id, user);
            printCache();
            return user;
        }
        if (!StringUtils.isEmpty(user.getUsername())) {
            oldUser.setUsername(user.getUsername());
        }
        if (!StringUtils.isEmpty(user.getPassword())) {
            oldUser.setPassword(user.getPassword());
        }
        if (user.getAge() > 0) {
            oldUser.setAge(user.getAge());
        }
        printCache();
        return oldUser;
    }

    public void printCache() {
        ObjectMapper mapper = new ObjectMapper();
        System.err.println("当前用户列表：");
        cacheMap.values().forEach((user) -> {
            try {
                System.out.println(mapper.writeValueAsString(user));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }

}
