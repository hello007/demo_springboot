package liu.springboot.adps;

import cn.com.agree.adps.AdpsConfiguration;
import cn.com.agree.adps.AdpsException;
import cn.com.agree.adps.AdpsInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AdpsConfig {

    private Map<String, AdpsInstance> adpsInstanceMap = new HashMap<>();

    @Bean
    public AdpsInstance adpsInstance() throws Exception {
        AdpsInstance adpsInstance = new AdpsInstance(adpsConfiguration("idea", "127.0.0.1:8891"));
        adpsInstanceMap.put(adpsInstance.getAdpsAddress(), adpsInstance);
        adpsInstance.awaitReady();
        return adpsInstance;
    }

    // broker地址列表,通讯地址为这些的adps可以成为broker
    private final static String brokerAddresses = "127.0.0.1:8890,127.0.0.1:8891";

    public AdpsConfiguration adpsConfiguration(String serverName, String adpsAddress) throws AdpsException {
        AdpsConfiguration adpsConfiguration = new AdpsConfiguration();
        // 设置本地adps通讯地址
        adpsConfiguration.setAdpsAddress(adpsAddress);
        // 设置broker地址列表字符串,用","分割
        adpsConfiguration.setBrokerAddresses(brokerAddresses);
        // 设置本地adps服务名称
        adpsConfiguration.setServerName(serverName);
        // 是否把broker作为pageWorker使用,入参Boolean类型,当入参=null时,使用自定义策略; 默认使用自定义策略
        adpsConfiguration.setUseBrokerAsWorker(true);
        // 当useBrokerAsWorker = null时,当前broker数量/adps总数>=brokerRatio就会把broker作为
        // pageWorker使用, 默认0.2,设值生效区间0.1-0.4
        adpsConfiguration.setBrokerRatio(0.3);
        // 检测backups并补充backups定时任务执行间隔,单位为s,默认值5. 设值生效区间1-10
        adpsConfiguration.setCheckBackupsInterval(4);
        // 检测page消亡间隔,单位为s,默认值60. 设值生效区间30-120
        adpsConfiguration.setCheckPageExtinctionInterval(20);
        // adps状态循环间隔,单位为s,默认值5. 设值生效区间1-10
        adpsConfiguration.setCycleRetryInterval(4);
        // adps心跳间隔,单位为s,默认值5. 设值生效区间1-10
        adpsConfiguration.setHeartBeatInterval(6);
        // idBlockSize大小,默认值10000. 设值生效区间>=10000
        adpsConfiguration.setIdBlockSize(100000);
        // ignite节点堆外内存大小,单位为MB, 默认值256,设值生效区间64-1024
        adpsConfiguration.setIgniteMemSize(64);
        // page的备份数量,默认为0,设值生效区间>=0
        adpsConfiguration.setNumBackups(1);
        // page的使用率,默认为0.8,设值生效区间0.7-0.9
        adpsConfiguration.setPageUseAgeRate(0.8);

        return adpsConfiguration;
    }
}
