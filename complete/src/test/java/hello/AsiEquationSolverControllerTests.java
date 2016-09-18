package hello;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AsiEquationSolverControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAsiEquationSolverControllerPost() throws Exception {

        this.mockMvc.perform(post("/v1/solver?coefficients=[2,2,-59,-29]&searchRange=[0,100]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(30.0));
        
        this.mockMvc.perform(post("/v1/solver?coefficients=[3,4,5,-6]&searchRange=[0,100]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(0.6));

        this.mockMvc.perform(post("/v1/solver?coefficients=[3,4,5,-6]&searchRange=[-10,0]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(-2.2));

        this.mockMvc.perform(post("/v1/solver?coefficients=[0,4,5,-6]&searchRange=[0,100]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(0.8));

        this.mockMvc.perform(post("/v1/solver?coefficients=[0,4,5,-6]&searchRange=[-10,0]")).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$.root").value(-2.0));
        
        this.mockMvc.perform(post("/v1/solver?coefficients=[7.1 , 1.4 , 5 , -6]&searchRange=[-2.5,100]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(0.5));

        this.mockMvc.perform(post("/v1/solver?coefficients=[7.1 , 1.4 , 5 , -6]&searchRange=[-10,-2.5]")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.root").value(-3.8));
    }

}
