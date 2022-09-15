package com.xmz.datarecordapplication.client;

import com.xmz.datarecordapplication.common.util.ObjectToMapUtil;
import com.xmz.datarecordapplication.model.common.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(paramMap,headers);
            ResponseEntity<T> response = restTemplate.postForEntity(url, httpEntity, responseType);
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
        return doRequest(url, HttpMethod.POST, param, RespResult.class);
    }

}
