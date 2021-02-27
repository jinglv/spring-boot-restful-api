package com.spring.boot.demo.controller;

import com.spring.boot.demo.BaseCase;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author jingLv
 * @date 2020/11/16
 */
class UserControllerTest extends BaseCase {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        // 实例化MVC
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }


    // @Test
    void login() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                // 构造一个get请求
                MockMvcRequestBuilders.get("/v1/user/login")
                        // 请求头headers
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        // 请求body
                        .content("{\"username\":\"xiaohong\",\"password\":\"123123\"}")
        )
                //andExpect，添加ResultMatchers验证规则，验证控制器执行完成后结果是否正确，【这是一个断言】
                .andExpect(MockMvcResultMatchers.status().isOk())
                //添加ResultHandler结果处理器，比如调试时 打印结果(print方法)到控制台
                .andDo(MockMvcResultHandlers.print())
                //返回相应的MvcResult
                .andReturn();
        System.out.println("Result==" + mvcResult.toString());
    }
}