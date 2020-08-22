package com.kodemamba.wxshop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.kevinsawicki.http.HttpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * @Author shengding
 * @Date 2020/8/22 22:16
 * @Description
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WxshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application.yml")
public class CodeIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    Environment environment;

    @Test
    public void returnHttpOKWhenParameterIsCorrect() throws JsonProcessingException {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setTel("15335181657");
        int code = HttpRequest.post(getUrl("/api/code"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .send(objectMapper.writeValueAsString(codeInfo))
                .code();
        Assertions.assertEquals(HTTP_OK, code);
    }

    @Test
    public void returnHttpBadWhenParameterIsCorrect() throws JsonProcessingException {
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setTel("");
        int code = HttpRequest.post(getUrl("/api/code"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .send(objectMapper.writeValueAsString(codeInfo))
                .code();
        Assertions.assertEquals(HTTP_BAD_REQUEST, code);
    }

    private String getUrl(String apiName) {
        // 获取集成测试的端口号
        return "http://localhost:" + environment.getProperty("local.server.port") + apiName;
    }




//    @BeforeEach
//    public void setUp() throws JsonProcessingException {
//        // 初始化内存数据库，以备测试
//        ClassicConfiguration conf = new ClassicConfiguration();
//        conf.setDataSource(
//                "jdbc:h2:mem:test",
//                "test",
//                "test");
//        Flyway flyway = new Flyway(conf);
//        flyway.clean();
//        flyway.migrate();
//
//        // 注册一个测试用户，以备测试
//        registerUser("test", "test");
//    }

//    @Test
//    public void usersCanLoginAndLogout() throws IOException {
//        // 进行自动化的用户登录、注销操作
//        for (int i = 0; i < 20; ++i) {
//            // 最开始不带Cookie访问，登录状态应该是未登录
//            assertLogInStatus(null, false);
//            assertLogInStatus(null, false);
//
//            // 执行一个登录操作，拿到Cookie
//            HttpRequest loginRequest = HttpRequest.post(getUrl("/auth/login"))
//                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                    .send(getUserNameAndPasswordJsonString("test", "test"));
//            String cookie = loginRequest.headers().get("Set-Cookie").get(0);
//
//            // 检查登录状态，应该是true
//            assertLogInStatus(cookie, true);
//
//            // 执行一个注销操作
//            HttpRequest logoutRequest = HttpRequest.get(getUrl("/auth/logout"))
//                    .header("Cookie", cookie)
//                    .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                    .accept(MediaType.APPLICATION_JSON_UTF8_VALUE);
//            Map logoutResponse = objectMapper.readValue(loginRequest.body(), Map.class);
//            // 断言注销操作成功
//            Assertions.assertEquals(HTTP_OK, logoutRequest.code());
//            Assertions.assertEquals(Result.ResultStatus.OK.getStatus(), logoutResponse.get("status"));
//
//            // 注销后，检查登录状态
//            // 现在，登录状态应该是false
//            assertLogInStatus(cookie, false);
//            assertLogInStatus(cookie, false);
//        }
//    }
//
//    // 检查用户当前的登录状态
//    private void assertLogInStatus(String cookie, boolean status) throws IOException {
//        String logStatus = HttpRequest.get(getUrl("/auth"))
//                .header("Cookie", cookie)
//                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .body();
//        Map logStatusResponse = objectMapper.readValue(logStatus, Map.class);
//        // 现在，登录状态应该是false
//        Assertions.assertEquals(status, logStatusResponse.get("isLogin"));
//        Assertions.assertEquals(Result.ResultStatus.OK.getStatus(), logStatusResponse.get("status"));
//    }
}
