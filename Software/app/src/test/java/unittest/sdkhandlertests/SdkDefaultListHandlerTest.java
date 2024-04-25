package unittest.sdkhandlertests;

import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import software.utils.SdkListHandler.Handlers.SdkDefaultListHandler;

import software.utils.SdkListHandler.models.SdkDefaultHeaderModel;

@RunWith(MockitoJUnitRunner.class)
public class SdkDefaultListHandlerTest {
    
    @Mock 
    private SdkDefaultHeaderModel sdkDefaultHeaderModelMock;

    @Mock
    private  ObjectMapper objectMapperMock;

    private  SdkDefaultListHandler sdkDefaultListHandler;

    private URL path;
    private final String yamlPath= "src/main/resources/yaml/defultSdks.yaml";
    @Before
    public void setUp() {
        path = mock(URL.class);
        
        sdkDefaultListHandler = new SdkDefaultListHandler(sdkDefaultHeaderModelMock, objectMapperMock, path);
    }

    @Test
    public void testReadDefultSdkFromYaml() {

        // Mocking behavior for ObjectMapper and URL
        when(path.getFile()).thenReturn(yamlPath);
        
        try {
            when(objectMapperMock.readValue(new File(yamlPath), SdkDefaultHeaderModel.class))
                    .thenReturn(sdkDefaultHeaderModelMock);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Call the method under test
        CompletableFuture<SdkDefaultHeaderModel> future = sdkDefaultListHandler.readDefultSdkFromYaml();

        // Block and get the result from the CompletableFuture
        SdkDefaultHeaderModel resultModel;
        try {
            resultModel = future.get();
            // Assert that the result matches the mocked model
            assertNotNull(resultModel);
            assertNotNull(sdkDefaultHeaderModelMock);
            assertEquals(sdkDefaultHeaderModelMock, resultModel);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }



        
    }

}
