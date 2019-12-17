//package com.example.administrator.searchforlovedones;
//
//import com.tencentcloudapi.common.Credential;
//import com.tencentcloudapi.common.profile.ClientProfile;
//import com.tencentcloudapi.common.profile.HttpProfile;
//import com.tencentcloudapi.common.exception.TencentCloudSDKException;
//
//import com.tencentcloudapi.iai.v20180301.IaiClient;
//import com.tencentcloudapi.iai.v20180301.models.SearchFacesRequest;
//import com.tencentcloudapi.iai.v20180301.models.SearchFacesResponse;
//
//public class SearchFaces
//{
//    public static void main(String [] args) {
//        try{
//
//            Credential cred = new Credential("AKIDlJcTO2SWMzLXS7LGzSeKo31mIZ4f4LPH", "nLdOuBbKDpBnAG6gy0lFZcBVsJUqwmMg");
//
//            HttpProfile httpProfile = new HttpProfile();
//            httpProfile.setEndpoint("iai.tencentcloudapi.com");
//
//            ClientProfile clientProfile = new ClientProfile();
//            clientProfile.setHttpProfile(httpProfile);
//
//            IaiClient client = new IaiClient(cred, "ap-beijing", clientProfile);
//
//            String params = "{\"GroupIds\":[\"001\"]}";
//            SearchFacesRequest req = SearchFacesRequest.fromJsonString(params, SearchFacesRequest.class);
//
//            SearchFacesResponse resp = client.SearchFaces(req);
//
//            System.out.println(SearchFacesRequest.toJsonString(resp));
//        } catch (TencentCloudSDKException e) {
//            System.out.println(e.toString());
//        }
//
//    }
//
//}
