package com.xmz.application.client;

import com.xmz.application.common.util.ObjectToMapUtil;
import com.xmz.application.model.common.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author xiaomingzhang
 * @date 2022/9/15
 */
@Slf4j
@Component
public class CommonBaseClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${client.url.dataRecord:http://127.0.0.1:8989}")
    private String dataRecordBaseUrl;


    private <T> T doRequest(String url, HttpMethod method, Object param, Class<T> responseType) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String,Object> paramMap = null;
            if(param instanceof Map){
                paramMap = (Map<String, Object>) param;
            } else {
                paramMap = ObjectToMapUtil.toMap(param);
            }

            ResponseEntity<T> response = null;

            if(HttpMethod.POST.equals(method)) {
                HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
                response = restTemplate.postForEntity(url, httpEntity, responseType);
            } else if(HttpMethod.GET.equals(method)){
                response = restTemplate.getForEntity(url, responseType, paramMap);
            }

            if (HttpStatus.OK.equals(response.getStatusCode())) {
                return response.getBody();
            } else {
                log.error("client请求返回状态码异常,code={},url={},method={},param={}",response.getStatusCode(), url, method, paramMap);
                throw new RuntimeException("请求失败");
            }
        } catch (Exception e) {
            log.error("client请求发生异常,url={},method={},param={}", url, method, param);
            throw new RuntimeException("请求失败", e);
        }
    }

    public RespResult doPost(String url, Object param){
        return doRequest(dataRecordBaseUrl + url, HttpMethod.POST, param, RespResult.class);
    }

    public RespResult doGet(String url, Object param){
        return doRequest(dataRecordBaseUrl + url, HttpMethod.GET, param, RespResult.class);
    }

}
