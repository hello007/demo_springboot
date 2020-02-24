package liu.springboot.adps;

import cn.com.agree.adps.AdpsException;
import cn.com.agree.adps.AdpsInstance;
import cn.com.agree.adps.PeerFunctionResults;
import org.apache.ignite.Ignite;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdpsFunctions implements IConstants {

    @Autowired
    private AdpsInstance adpsInstance;

    @PostConstruct
    public void init() {
        adpsInstance.addAdpsPeerFunctions(CMD_TEST_INSERT, args -> callTestInsert(args));
    }

    private JSONObject callTestInsert(JSONObject argsJsonObject) throws AdpsException {
        try {
            long pageId = argsJsonObject.getLong("pageId");
            Ignite pageIgnite = adpsInstance.getPageIgniteByPageId(pageId);
            // 打印pageIgnite信息
            AdpsUtils.printIgniteInfo(pageIgnite);
            // 创建表

            return new JSONObject().put("lastRecordId", adpsInstance.getLastRecordIdByPageId(pageId))
                    .put(RET_EXECUTE_KEY, RET_EXECUTE_VALUE);
        } catch (JSONException e) {
            throw PeerFunctionResults.F102_JSON解析错误.createException("testFunction", e.toString());
        }
    }

}
