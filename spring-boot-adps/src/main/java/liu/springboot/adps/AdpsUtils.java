package liu.springboot.adps;

import com.alibaba.fastjson.JSON;
import org.apache.ignite.Ignite;

public class AdpsUtils {

    public static void printIgniteInfo(Ignite ignite) {
        System.err.println(JSON.toJSONString(ignite));
    }

}
