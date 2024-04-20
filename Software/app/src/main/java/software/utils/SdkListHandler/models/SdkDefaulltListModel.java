package software.utils.SdkListHandler.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SdkDefaulltListModel {
    @JsonProperty
    private String sdkName;
    @JsonProperty
    private String sdkVersion; 
    @JsonProperty
    private String sdkType;
    @JsonProperty
    private String sdkDescription;
    @JsonProperty
    private String sdkPath;

    public SdkDefaulltListModel(String sdkName, String sdkVersion, String sdkType, String sdkDescription, String sdkPath) {
        this.sdkName = sdkName;
        this.sdkVersion = sdkVersion;
        this.sdkType = sdkType;
        this.sdkDescription = sdkDescription;
        this.sdkPath = sdkPath;
    }

    public SdkDefaulltListModel() {
    }

    public String getSdkName() {
        return sdkName;
    }

    public void setSdkName(String sdkName) {
        this.sdkName = sdkName;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getSdkType() {
        return sdkType;
    }

    public void setSdkType(String sdkType) {
        this.sdkType = sdkType;
    }

    public String getSdkDescription() {
        return sdkDescription;
    }

    public void setSdkDescription(String sdkDescription) {
        this.sdkDescription = sdkDescription;
    }

    public String getSdkPath() {
        return sdkPath;
    }

    public void setSdkPath(String sdkPath) {
        this.sdkPath = sdkPath;
    }
    
    
}
