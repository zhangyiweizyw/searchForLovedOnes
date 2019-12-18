package com.example.administrator.searchforlovedones;

import com.example.administrator.searchforlovedones.GsonUtils;
import com.example.administrator.searchforlovedones.HttpUtil;


import java.util.*;

/**
 * 人脸搜索
 */
public class FaceSearch {

    public static String faceSearch() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("image", "027d8308a2ec665acb1bdf63e513bcb9");
            map.put("liveness_control", "NORMAL");
            map.put("group_id_list", "group_repeat,group_233");
            map.put("image_type", "FACE_TOKEN");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "25.ea380d8fcce8cb81ccf25fe77b661926.315360000.1890181976.282335-17850558";

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceSearch.faceSearch();
    }
}
