package com.capitalone.weathertracker.resources.measurements;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import com.capitalone.weathertracker.resources.RootResource;
import com.capitalone.weathertracker.resources.models.Measurement;
import com.capitalone.weathertracker.resources.service.MeasurementService;
import com.google.gson.Gson;

public class MeasurementTest {
	
	private final static String URL = "/measurements";
	private MockMvc mockMvc;
	private Gson gson;

    @Mock
    private MeasurementService measurementService;

    @InjectMocks
    private RootResource rootResource;

    @Before
    public void init(){
		this.gson = new Gson();
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(rootResource)
                .build();
    }
    
    @Test
    public void test_create_user_success() throws Exception {
    	Measurement payload = new Measurement("2019-22-01T22:00:00.000Z", 27.1, 16.0, 10.0);
    	
    	/*mockMvc.perform(MockMvcRequestBuilders.post(URL)
    			  .content(gson.toJson(payload))
    			  .contentType(MediaType.APPLICATION_JSON)
    			  .accept(MediaType.APPLICATION_JSON));*/
    }

}
