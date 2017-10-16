package com.bigbigrain.face.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
import org.junit.Test;

import com.baidu.aip.face.AipFace;
import com.bigbigrain.face.utils.FileUtil;

/**
 * @ClassName: AipFaceDemo
 * @Description: 百度人脸识别测试demo
 * 					在所有方法中，现在所有的imagePath使用的本地图片路径，但支持文件二进制数组的形式
 * @author B1gB1gRAin
 * @date 2017年10月16日 下午3:56:18
 *
 */
public class AipFaceDemo {
	
    //设置APPID/AK/SK
	//ps：这三个值需要在百度人脸识别中注册后获得
    public static final String APP_ID = "10244589";
    public static final String API_KEY = "4vhpXtWNw8RfxqGu112BCCRB";
    public static final String SECRET_KEY = "tBzaGLSBKOCyIxMum4Bbwi11fKlzV6lR";

    /**
     * @Title: faceRecognize
     * @Description: 人脸检测方法
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午1:57:07 
     *
     * @param client
     * @param imagePath 参数为本地图片路径
     * @throws IOException
     */
    public void faceDetect(AipFace client, String imagePath, HashMap<String, String> options) throws IOException {
        JSONObject response1 = client.detect(imagePath, options);
        System.out.println(response1.toString());

        // 参数为本地图片文件二进制数组
        byte[] file = FileUtil.readFileByBytes(imagePath);    // readImageFile函数仅为示例
        JSONObject response2 = client.detect(file, options);
        System.out.println(response2.toString());
    }

    /**
     * @Title: faceRecognize
     * @Description: 人脸比对(该请求用于比对多张图片中的人脸相似度并返回两两比对的得分，可用于判断两张脸是否是同一人的可能性大小。)
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午1:54:05 
     *
     * @param client
     * @param imagePath1 参数为本地图片路径1
     * @param imagePath2 参数为本地图片路径2
     */
    public void faceMatch(AipFace client, String imagePath1, String imagePath2) {
        ArrayList<String> pathArray = new ArrayList<String>();
        pathArray.add(imagePath1);
        pathArray.add(imagePath2);
        JSONObject response = client.match(pathArray, new HashMap<String, String>());
        System.out.println(response.toString());
    }
    
    /**
     * @Title: identifyUser
     * @Description:人脸识别（
     * 						用于计算指定组内用户，与上传图像中人脸的相似度。识别前提为您已经创建了一个人脸库。
     * 						人脸识别返回值不直接判断是否是同一人，只返回用户信息及相似度分值
     * 						）
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午2:00:29 
     *
     * @param client
     */
    public void identifyUser(AipFace client, String imagePath, List<String> groupList, HashMap<String, Object> options) {
        JSONObject res = client.identifyUser(groupList, imagePath, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: verifyUser
     * @Description: 人脸认证（
     * 						用于识别上传的图片是否为指定用户，即查找前需要先确定要查找的用户在人脸库中的id。
     * 						人脸认证与人脸识别的差别在于：人脸识别需要指定一个待查找的人脸库中的组；而人脸认证需要指定具体的用户id即可，不需要指定具体的人脸库中的组；
     * 						实际应用中，人脸认证需要用户或系统先输入id，这增加了验证安全度，但也增加了复杂度，具体使用哪个接口需要视您的业务场景判断
     * 						）
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午2:19:22 
     *
     * @param client
     * @param imagePath
     * @param groupList
     * @param options
     */
    public void verifyUser(AipFace client, String imagePath, String userId, List<String> groupList, HashMap<String, Object> options) {
        JSONObject res = client.verifyUser(userId, groupList, imagePath, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: facesetAddUser
     * @Description: 人脸注册 
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午2:51:37 
     *
     * @param client
     * @param imagePath 图片路径
     * @param userId 用户id
     * @param note  用户资料
     * @param groupList 用户组
     * @param options 返回参数
     */
    public void facesetAddUser(AipFace client, String imagePath, String userId, String note, List<String> groupList, HashMap<String, String> options) {
        JSONObject res = client.addUser(userId, note, groupList, imagePath, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: facesetUpdateUser
     * @Description: 人脸更新
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午2:58:18 
     *
     * @param client
     * @param imagePath 图片地址
     * @param userId 用户id
     * @param note 用户资料
     * @param group 组
     * @param options
     */
    public void facesetUpdateUser(AipFace client, String imagePath, String userId, String note, String group, HashMap<String, String> options) {
        JSONObject res = client.updateUser(userId, note, group, imagePath, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: facesetDeleteUser
     * @Description: 人脸删除
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:04:27 
     *
     * @param client
     * @param userId
     * @param group
     */
    public void facesetDeleteUser(AipFace client, String userId, List<String> group) {
        // 只从指定组中删除用户
        JSONObject res1 = client.deleteUser(userId, group);
        System.out.println(res1.toString(2));

        // 从人脸库中彻底删除用户
        JSONObject res2 = client.deleteUser(userId);
        System.out.println(res2.toString(2));
    }
    
    /**
     * @Title: getUser
     * @Description: 根据用户id查询用户在所有组内的信息
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:08:40 
     *
     * @param client
     * @param userId
     */
    public void getUser(AipFace client, String userId) {
        // 查询一个用户在所有组内的信息
        JSONObject res = client.getUser(userId);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: getUser
     * @Description: 查询一个用户在指定组内的信息
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:09:22 
     *
     * @param client
     * @param userId
     * @param group
     */
    public void getUser(AipFace client, String userId, List<String> group) {
    	// 查询一个用户在指定组内的信息
    	JSONObject res = client.getUser(userId, group);
    	System.out.println(res.toString(2));
    }
    
    /**
     * @Title: getGroupList
     * @Description: 用户组列表查询
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:28:06 
     *
     * @param client
     * @param options:options里面可以放如下两个key
     * 					start	number	默认值0，起始序号
						end		number	返回数量，默认值100，最大值1000
     */
    public void getGroupList(AipFace client, HashMap<String, Object> options) {
        JSONObject res = client.getGroupList(options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: getGroupUsers
     * @Description:组内用户查询 
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:32:16 
     *
     * @param client
     * @param group
     * @param options
     */
    public void getGroupUsers(AipFace client, String group, HashMap<String, Object> options) {
        JSONObject res = client.getGroupUsers(group, options);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: addGroupUser
     * @Description: 组间复制用户（用于将已经存在于人脸库中的用户添加到一个新的组）
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:36:43 
     *
     * @param client
     * @param srcGroup 原组
     * @param desGroup 目标组
     * @param userId 要复制的用户ID
     */
    public void addGroupUser(AipFace client, String srcGroup, List<String> desGroup, String userId) {
        JSONObject res = client.addGroupUser(srcGroup, desGroup, userId);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: deleteGroupUser
     * @Description: 组内删除用户(用于将用户从某个组中删除，但不会删除用户在其它组的信息)
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午3:42:00 
     *
     * @param client
     * @param groups
     * @param userId
     */
    public void deleteGroupUser(AipFace client, List<String> groups, String userId) {
        JSONObject res = client.deleteGroupUser(groups, userId);
        System.out.println(res.toString(2));
    }
    
    /**
     * @Title: faceRecognizeTest
     * @Description: 测试方法，这个方法针对百度api逐条测试
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午4:43:41 
     *
     * @throws IOException
     */
    @Test
    public void faceRecognizeTest() throws IOException{
    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //建立连接的超时时间（单位：毫秒
        client.setConnectionTimeoutInMillis(2000);
        //setSocketTimeoutInMillis
        client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        //client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
        //client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

        // 调用接口
        String path = "D:\\test.jpg";
        JSONObject res = client.detect(path, new HashMap<String, String>());
        System.out.println(res.toString(2));
        
        System.out.println("faceRecognize=====================人脸检测测试===========================");
        // 自定义参数定义
        HashMap<String, String> optionsDetect = new HashMap<String, String>();
        optionsDetect.put("max_face_num", "1");
        optionsDetect.put("face_fields", "expression");
        this.faceDetect(client, "D:\\test.jpg", optionsDetect);
        
        System.out.println("faceRecognize=====================人脸比对测试===========================");
        this.faceMatch(client, "D:\\1.jpg", "D:\\2.jpg");
        
        System.out.println("faceRecognize=====================人脸识别测试===========================");
        HashMap<String, Object> optionsIdentifyUser = new HashMap<String, Object>(1);
        optionsIdentifyUser.put("user_top_num", 1);
        this.identifyUser(client, "D:\\test.jpg", Arrays.asList("group1", "group2"), optionsIdentifyUser);
        
        System.out.println("faceRecognize=====================人脸认证测试===========================");
        HashMap<String, Object> optionsVerifyUser = new HashMap<String, Object>(1);
        optionsVerifyUser.put("top_num", 5);
        this.verifyUser(client, "D:\\zhangyu.jpg", "zhangy", Arrays.asList("group1", "group2"), optionsVerifyUser);
        
        System.out.println("faceRecognize=====================人脸注册测试===========================");
        HashMap<String, String> optionsAddUser = new HashMap<String, String>();
        this.facesetAddUser(client, "D:\\zhangyu.jpg", "zhangy", "张雨的个人信息", Arrays.asList("group1", "group2"), optionsAddUser);
        
        System.out.println("faceRecognize=====================人脸更新测试===========================");
        HashMap<String, String> optionsUpdateUser = new HashMap<String, String>();
        this.facesetUpdateUser(client, "D:\\zhangyu02.jpg", "zhangy", "张雨的个人信息", "group1", optionsUpdateUser);
        
        System.out.println("faceRecognize=====================人脸删除测试===========================");
        this.facesetDeleteUser(client, "zhangy1", Arrays.asList("group1"));
        
        System.out.println("faceRecognize=====================人脸用户信息查询测试===========================");
        this.getUser(client, "zhangy");
        this.getUser(client, "zhangy", Arrays.asList("group1"));
        
        System.out.println("faceRecognize=====================人脸用户组列表查询测试===========================");
        HashMap<String, Object> optionsGroupList = new HashMap<String, Object>(2);
        optionsGroupList.put("start", 0);
        optionsGroupList.put("num", 10);
        this.getGroupList(client, optionsGroupList);
        
        System.out.println("faceRecognize=====================组内用户列表查询测试===========================");
        HashMap<String, Object> optionsGroupUsers = new HashMap<String, Object>(2);
        optionsGroupUsers.put("start", 0);
        optionsGroupUsers.put("num", 10);
        this.getGroupUsers(client, "group1", optionsGroupUsers);
        
        System.out.println("faceRecognize=====================组间复制用户测试===========================");
        this.addGroupUser(client, "group1", Arrays.asList("group3"), "zhangy");
        
        System.out.println("faceRecognize=====================组内删除用户测试===========================");
        this.deleteGroupUser(client, Arrays.asList("group3"), "zhangy1");
    }
    
    /**
     * @Title: businessTest
     * @Description: 业务测试，实现了从注册用户到人脸识别的过程
     * @author B1gB1gRAin
     * @date 2017年10月16日 下午4:44:34 
     *
     */
    @Test
    public void businessTest(){
    	AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //建立连接的超时时间（单位：毫秒
        client.setConnectionTimeoutInMillis(2000);
        //setSocketTimeoutInMillis
        client.setSocketTimeoutInMillis(60000);
        
        //查询当前应用下有多少个组
        HashMap<String, Object> optionsGroupList = new HashMap<String, Object>(2);
        System.out.println("当前appid下面的组为：");
        this.getGroupList(client, optionsGroupList);
        
        //在中科恒运（zkhy）组下创建用户zhangy
        System.out.println("在中科恒运(zkhy)组下面创建zhangy用户：");
        HashMap<String, String> optionsAddUser = new HashMap<String, String>();
        this.facesetAddUser(client, "D:\\1.jpg", "zhangy", "这个是张雨的个人资料", Arrays.asList("zkhy"), optionsAddUser);
        
        //查询中科恒运（zkhy）组下的用户
        System.out.println("查询中科恒运(zkhy)组下面zhangy用户：");
        this.getGroupUsers(client, "zkhy", new HashMap<String, Object>(2));
        
        System.out.println("更新中科恒运(zkhy)组下面zhangy用户：");
        //更新中科恒运(zkhy)组下面zhangy用户
        this.facesetUpdateUser(client, "D:\\zhangyu02.jpg", "zhangy", "张雨的个人信息修改了", "zkhy", new HashMap<String, String>());
        
        System.out.println("查询中科恒运(zkhy)组下面zhangy用户：");
        //查询中科恒运（zkhy）组下的用户
        this.getGroupUsers(client, "zkhy", new HashMap<String, Object>(2));
        
        System.out.println("人脸识别：");
        HashMap<String, Object> optionsIdentifyUser = new HashMap<String, Object>();
        optionsIdentifyUser.put("user_top_num", 5);
        this.identifyUser(client, "D:\\1.jpg",  Arrays.asList("zkhy"), optionsIdentifyUser);
        
        System.out.println("人脸认证：");
        this.verifyUser(client, "D:\\2.jpg", "wangwu", Arrays.asList("zkhy"), new HashMap<String, Object>());
        
        System.out.println("人脸比对：");
        this.faceMatch(client, "D:\\zhangyu.jpg", "D:\\zhangyu02.jpg");
        
    }
    
}
