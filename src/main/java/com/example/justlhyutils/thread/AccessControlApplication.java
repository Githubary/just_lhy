package com.example.justlhyutils.thread;

/**
 * description:
 *
 * @author liuhuayang
 * date: 2024/5/9 10:51
 */
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableScheduling
public class AccessControlApplication {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${access.file.path}")
    private String accessFilePath;

    private Map<Integer, UserAccess> accessMap;
    private Map<Integer, UserAccess> hotAccessMap;

    public static void main(String[] args) {
        SpringApplication.run(AccessControlApplication.class, args);
    }

    public AccessControlApplication() {
        try {
            accessMap = loadAccessMapFromFile();
            hotAccessMap = new HashMap<>();
        } catch (IOException e) {
            accessMap = new HashMap<>();
            hotAccessMap = new HashMap<>();
            e.printStackTrace();
        }
    }

    @PostMapping("/admin/addUser")
    public ResponseEntity<String> addUserAccess(@RequestHeader("Authorization") String authHeader,
                                                @RequestBody AccessRequest accessRequest) {
        if (!isAdmin(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Only admin can access this endpoint.");
        }

        int userId = accessRequest.getUserId();
        accessMap.put(userId, new UserAccess(userId, accessRequest.getEndpoints()));

        // 更新热点访问数据
        hotAccessMap.put(userId, accessMap.get(userId));

        try {
            saveAccessMapToFile();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save access information.");
        }

        return ResponseEntity.ok("Access added for user " + userId);
    }

    @GetMapping("/user/{resource}")
    public ResponseEntity<String> checkUserAccess(@RequestHeader("Authorization") String authHeader,
                                                  @PathVariable String resource) {
        int userId = getUserId(authHeader);
        if (userId == -1) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid authorization header.");
        }

        UserAccess userAccess = hotAccessMap.get(userId);
        if (userAccess != null && userAccess.hasAccess(resource)) {
            userAccess.incrementAccessCount(); // 增加访问计数
            return ResponseEntity.ok("User has access to resource " + resource);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User does not have access to resource " + resource);
        }
    }

    @Scheduled(fixedDelay = 60000) // 每分钟清理一次热点访问数据
    public void cleanupHotAccessData() {
        // 清理访问次数较低的数据
        hotAccessMap.entrySet().removeIf(entry -> entry.getValue().getAccessCount() < 3); // 举例：访问次数小于3的数据会被清理
    }

    private boolean isAdmin(String authHeader) {
        // 解码Header并检查角色是否为admin
        // 在这里，你可以使用Base64解码authHeader，然后检查是否包含"role":"admin"，此处省略实现
        return true; // 临时返回true，表示管理员角色
    }

    private int getUserId(String authHeader) {
        // 解码Header并获取用户ID
        // 在这里，你可以使用Base64解码authHeader，然后获取用户ID，此处省略实现
        return 123456; // 临时返回一个用户ID
    }

    private Map<Integer, UserAccess> loadAccessMapFromFile() throws IOException {
        File file = new File(accessFilePath);
        if (file.exists()) {
            return objectMapper.readValue(file, new TypeReference<Map<Integer, UserAccess>>() {});
        } else {
            return new HashMap<>();
        }
    }

    private void saveAccessMapToFile() throws IOException {
        File file = new File(accessFilePath);
        objectMapper.writeValue(file, accessMap);
    }

    static class AccessRequest {
        private int userId;
        private String[] endpoints;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String[] getEndpoints() {
            return endpoints;
        }

        public void setEndpoints(String[] endpoints) {
            this.endpoints = endpoints;
        }
    }

    static class UserAccess {
        private int userId;
        private String[] accessList;
        private int accessCount; // 访问次数

        public UserAccess(int userId, String[] accessList) {
            this.userId = userId;
            this.accessList = accessList;
            this.accessCount = 0;
        }

        public boolean hasAccess(String resource) {
            for (String access : accessList) {
                if (access.equals(resource)) {
                    return true;
                }
            }
            return false;
        }

        public int getAccessCount() {
            return accessCount;
        }

        public void incrementAccessCount() {
            accessCount++;
        }
    }
}
