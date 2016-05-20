package test.batch.jobManager;

import com.lz.batch.request.BatchRequest;
import com.lz.batch.response.BatchResponse;
import com.lz.batch.utils.MyBatchUtil;
import com.lz.qz.model.QrtzCronTriggers;
import com.lzjf.util.JacksonJsonUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.unitils.UnitilsJUnit4TestClassRunner;
import org.unitils.spring.annotation.SpringApplicationContext;
import org.unitils.spring.annotation.SpringBean;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liudh on 2015/10/14.
 */
@RunWith(UnitilsJUnit4TestClassRunner.class)
@SpringApplicationContext({"classpath*:conf/spring.xml"})
public class JobManager {

    @SpringBean("restTemplate")
    private RestTemplate restTemplate;
// private static String url="http://123.56.131.74:8081/batch/run";
    private static String url="http://localhost:8080/batch/run";
  //  private static String url="http://localhost:50001/batch/run";
//    private static String localurl="";
//    private static String url206="http://localhost:50022/batch/run";


    @Test
    public void testJobAdd() throws Exception {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","addQuartzJob");
        hm.put("jobname", "lllsgRollOut_batch");
        hm.put("jobgroup", "lllsgRollOut_batch");
        hm.put("jobid","lllsgRollOut_batch");
        hm.put("cron", "0 30 8 * * ?");
//        String url = "http://localhost:50002/batch/run";
        jobManager(url,hm);
    }

    @Test
    public void testJobDelete() throws Exception {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","removeQuartzJob");
        hm.put("jobname", "writeToHbaseAll");
        hm.put("jobgroup", "writeToHbaseAll");
        hm.put("jobid","writeToHbaseAll");
        hm.put("cron", "0 0 1 * * ?");
//        String url = "http://localhost:50002/batch/run";
        jobManager(url,hm);
    }

    @Test
    public void testJobModify() throws Exception {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","modifyQuartzJob");
        hm.put("jobname", "userHb_batch");
        hm.put("jobgroup", "userHb_batch");
        hm.put("jobid","userHb_batch");
        hm.put("cron", "0 0 5 * * ?");
//        String url = "http://localhost:50002/batch/run";
        jobManager(url,hm);
    }

    @Test
    public void testJobQuery() throws Exception {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","queryQuartzJob");
//        String url = "http://localhost:50002/batch/run";
        BatchResponse batchResponse = jobManager(url, hm);
        if(batchResponse.isSuccess()){
            JsonConfig jcf = new JsonConfig();
            jcf.setExcludes(new String[]{""});
            JSONArray jsonarray = JSONArray.fromObject(batchResponse.getRtn(),jcf);
            List<QrtzCronTriggers> list = JSONArray.toList(jsonarray,new QrtzCronTriggers(),jcf);
            for (QrtzCronTriggers qrtzCronTriggers:list)
                System.out.println(qrtzCronTriggers.getTriggerName()+":"+qrtzCronTriggers.getCronExpression());
        }
    }
    @Test
    public void testJobRun() throws Exception {
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","runBatchJob");
        hm.put("paramMap",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        hm.put("jobid","finance_batch");
//        hm.put("jobEntry","redEntityJob");
        hm.put("currentDate","2015-12-17");
//        String url = "http://localhost:50002/batch/run";
        BatchResponse batchResponse = jobManager(url, hm);
        if(batchResponse.isSuccess()){
        }
    }

    @Test
    public void testJobLoopRun() throws Exception{
        String jobs = "p2pTdRegSrcParamJob_batch,qzJrReportFOtherInvest_batch,qzJrReportFP2pInvest_batch,qzJrReportMInvestAnalysis_batch,qzJreportMOperatingUserdata_batch,qzJrReportMSalesDataStatistical_batch,qzJrReportMTargetInvestStatistics_batch";
        HashMap<String, String> hm = new HashMap<>();
        hm.put("method","runBatchJob");
        hm.put("paramMap",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        hm.put("currentDate","2015-12-06");
        for(String job:jobs.split(",")){
            hm.put("jobid",job);
            BatchResponse batchResponse = jobManager(url, hm);
            if(batchResponse.isSuccess()){
                System.out.println(JacksonJsonUtil.beanToJson(batchResponse));
            }
        }
    }

    @Test
    public void testRemoteCall(){
        String url = "http://localhost:8082/privateProduct/getPrivateProductList.do";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("testpara1", "11111111111111");
        requestMap.put("testpara2", "2222222222");
        requestMap.put("testpara3", "3333333333");

        HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestMap, headers);
        ParameterizedTypeReference<Map<String, Object>> typeRef = new ParameterizedTypeReference<Map<String, Object>>() {
        };
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, typeRef);
        Map<String, Object> myResponse = responseEntity.getBody();
    }

    @Test
    public void testLogin(){
        String url = "http://localhost:8080/a/logout?username=lzjf&password=admin&mobileLogin=true";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String,String> requestMap = new HashMap<>();
        requestMap.put("username", "lzjf");
        requestMap.put("password", "admin");
        requestMap.put("mobileLogin", "true");

        HttpEntity<Map<String,String>> entity = new HttpEntity<>(requestMap, headers);
        ParameterizedTypeReference<String> typeRef = new ParameterizedTypeReference<String>() {
        };
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, typeRef);
        String myResponse = responseEntity.getBody();
        System.out.println(myResponse);
    }
    /**
     * 远程调用
     * @param url
     * @param hm
     * @return
     */
    public BatchResponse jobManager(String url,HashMap<String, String> hm) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Date date = new Date();
        String timestamp = new SimpleDateFormat("yyyyMMddhhmm").format(date);
        hm.put("timestamp", timestamp);
        Map<String, String> maps = new HashMap<String, String>();
        maps.put("appkey","DBB20BAE-200B-607C-E625-42B8B77EAA02");
        maps.put("prefixAppsecret","appsecret");
        maps.put("suffixAppsecret","appsecret");
        maps.put("dateStr", timestamp);
        String sign= MyBatchUtil.getSignature(maps);;
        hm.put("sign",sign);
        BatchRequest batchRequest = new BatchRequest();
        batchRequest.setRequestHm(hm);
        HttpEntity<Object> entity = new HttpEntity<Object>(batchRequest, headers);
        ParameterizedTypeReference<BatchResponse> typeRef = new ParameterizedTypeReference<BatchResponse>() {
        };
        ResponseEntity<BatchResponse> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, typeRef);
        BatchResponse myResponse = responseEntity.getBody();
        return myResponse;
//        System.out.println(JacksonJsonUtil.beanToJson(myResponse));
    }
}
