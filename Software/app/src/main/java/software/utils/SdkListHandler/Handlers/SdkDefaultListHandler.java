package software.utils.SdkListHandler.Handlers;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.CompletableFuture;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import software.utils.SdkListHandler.models.SdkDefaultHeaderModel;

public class SdkDefaultListHandler {

    private SdkDefaultHeaderModel sdkDefaultModel;
    private ObjectMapper  objectMapper;
    private URL path;
    private final String yamlPath = "/yaml/defultSdks.yaml";

    public SdkDefaultListHandler(SdkDefaultHeaderModel sdkDefaultModel, ObjectMapper objectMapper, URL path) {
        this.sdkDefaultModel = sdkDefaultModel;
        this.objectMapper = objectMapper;
        this.objectMapper.findAndRegisterModules();
        this.path = path;
    }

    public SdkDefaultListHandler() {
        sdkDefaultModel = new SdkDefaultHeaderModel();
        this.objectMapper = new ObjectMapper(new YAMLFactory());
        this.objectMapper.findAndRegisterModules();
        this.path = getClass().getResource(yamlPath);
    }

    
    public CompletableFuture<SdkDefaultHeaderModel> readDefultSdkFromYaml(){
        return CompletableFuture.supplyAsync(()->{
            try {
                sdkDefaultModel = objectMapper.readValue(new File(path.getFile()),SdkDefaultHeaderModel.class );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sdkDefaultModel;
        });
    }


    public SdkDefaultHeaderModel getSdkDefaultModel() {
        return sdkDefaultModel;
    }

    public void setSdkDefaultModel(SdkDefaultHeaderModel sdkDefaultModel) {
        this.sdkDefaultModel = sdkDefaultModel;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public URL getPath() {
        return path;
    }

    public void setPath(URL path) {
        this.path = path;
    }
}
