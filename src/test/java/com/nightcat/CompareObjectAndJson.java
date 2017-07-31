//package com.nightcat;
//
//import com.google.gson.Gson;
//import com.nightcat.model.User;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.utility.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class CompareObjectAndJson {
//
//
//    /***
//     *
//     * 10万次比较
//     *
//     * 平均毫秒数 重复5次
//     * json 保存: 7003
//     *      取得: 4890
//     *
//     * obj 保存: 5904
//     *      取得: 6160
//     *
//     *对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间
//     json方式,保存对象, 消耗时间(毫秒): 7083
//     obj方式, 保存对象, 消耗时间(毫秒): 5038
//     对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间
//     json方式,获取 对象, 消耗时间(毫秒): 4954
//     obj方式, 获取 对象, 消耗时间(毫秒): 5874
//     *
//     *
//     * 对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间
//     json方式,保存对象, 消耗时间(毫秒): 8112
//     obj方式, 保存对象, 消耗时间(毫秒): 7493
//     对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间
//     json方式,获取 对象, 消耗时间(毫秒): 4839
//     obj方式, 获取 对象, 消耗时间(毫秒): 5718
//     *
//     *
//     * 对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间
//     json方式,保存对象, 消耗时间(毫秒): 6440
//     obj方式, 保存对象, 消耗时间(毫秒): 6981
//     对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间
//     json方式,获取 对象, 消耗时间(毫秒): 6423
//     obj方式, 获取 对象, 消耗时间(毫秒): 7426
//
//     对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间
//     json方式,保存对象, 消耗时间(毫秒): 5222
//     obj方式, 保存对象, 消耗时间(毫秒): 4908
//     对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间
//     json方式,获取 对象, 消耗时间(毫秒): 4044
//     obj方式, 获取 对象, 消耗时间(毫秒): 5206
//
//     对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间
//     json方式,保存对象, 消耗时间(毫秒): 8162
//     obj方式, 保存对象, 消耗时间(毫秒): 5100
//     对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间
//     json方式,获取 对象, 消耗时间(毫秒): 4193
//     obj方式, 获取 对象, 消耗时间(毫秒): 6580
//     * */
//
//
//    Random random = new Random(47);
//
//    int count = 100000;
//
//    @Autowired
//    Gson gson;
//
//    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
//
//    @Autowired
//    private RedisTemplate<String, User> redisObjTemplate;
//
//    Map<String, User> jsonmap = new HashMap<>(count);
//    Map<String, User> objectmap = new HashMap<>(count);
//
//    LinkedList<String> jsonids = new LinkedList<>();
//    LinkedList<String> objids = new LinkedList<>();
//
//
//    @Before
//    public void setup() {
//        //测试用10000个用户来比较相同内容时的速度;
//        List<User> tested = getUserList(count);
//        tested.forEach(e -> {
//            jsonmap.put("JSONTEST:" + e.getUid(), e);
//            jsonids.add("JSONTEST:" + e.getUid());
//            objectmap.put("OBJECTTEST:" + e.getUid(), e);
//            objids.add("OBJECTTEST:" + e.getUid());
//
//        });
//        System.out.println("user count: " + jsonmap.size());
//        System.out.println("user count: " + objectmap.size());
//
//    }
//
//    @Test
//    public void test() {
//        saveTest();
//        getTest();
//    }
//
//    public void saveTest() {
//        System.out.println("对象7条属性,小型. 测试对于相同对象 保存 相同次数的时间");
//        long jsonsta = System.currentTimeMillis();
//        for (Map.Entry<String, User> entry : jsonmap.entrySet()) {
//            saveJSON(entry.getKey(), entry.getValue());
//        }
//        long jsonend = System.currentTimeMillis();
//        System.out.println("json方式,保存对象, 消耗时间(毫秒): " + (jsonend - jsonsta));
//
//        long objsta = System.currentTimeMillis();
//        for (Map.Entry<String, User> entry : objectmap.entrySet()) {
//            saveObj(entry.getKey(), entry.getValue());
//        }
//        long objend = System.currentTimeMillis();
//        System.out.println("obj方式, 保存对象, 消耗时间(毫秒): " + (objend - objsta));
//
//        //one json 5236; obj 4780
//    }
//
//    public void getTest() {
//        System.out.println("对象7条属性,小型. 测试对于相同对象 获取 相同次数的时间");
//        long jsonsta = System.currentTimeMillis();
//        jsonids.forEach(this::getJson);
//        long jsonend = System.currentTimeMillis();
//        System.out.println("json方式,获取 对象, 消耗时间(毫秒): " + (jsonend - jsonsta));
//
//        long objsta = System.currentTimeMillis();
//        objids.forEach(this::getObj);
//        long objend = System.currentTimeMillis();
//        System.out.println("obj方式, 获取 对象, 消耗时间(毫秒): " + (objend - objsta));
//
//    }
//
//    public void saveJSON(String key, User user) {
//        redisTemplate.opsForValue().set(key, gson.toJson(user));
//    }
//
//    public void saveObj(String key, User user) {
//        redisObjTemplate.opsForValue().set(key, user);
//    }
//
//    public User getJson(String key) {
//        return gson.fromJson(redisTemplate.opsForValue().get(key), User.class);
//    }
//
//    public User getObj(String key) {
//        return redisObjTemplate.opsForValue().get(key);
//    }
//
//
//    public int getLenght(String key) {
//        return redisTemplate.opsForValue().get(key).length();
//    }
//
//    public List<User> getUserList(int count) {
//        List<User> users = new LinkedList<>();
//        for (int i = 0; i < count; i++) {
//            User user = new User();
//            user.setUid(UUID.randomUUID().toString());
//            user.setDel(random.nextBoolean());
//            user.setPhone(Long.toString(random.nextLong()));
//            user.setNickname(UUID.randomUUID().toString());
//            user.setRole(random.nextBoolean() ? "00" : "01");
//            user.setPassword(UUID.randomUUID().toString());
//            user.setRealname(UUID.randomUUID().toString());
//            users.add(user);
//        }
//        return users;
//    }
//}
