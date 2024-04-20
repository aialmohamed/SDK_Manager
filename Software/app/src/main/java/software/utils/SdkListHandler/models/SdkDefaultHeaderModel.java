package software.utils.SdkListHandler.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SdkDefaultHeaderModel {
    @JsonProperty
    private List<SdkDefaulltListModel> Defult_SdK;

    public SdkDefaultHeaderModel(List<SdkDefaulltListModel> Defult_SdK) {
        this.Defult_SdK = Defult_SdK;
    }

    public SdkDefaultHeaderModel() {
        this.Defult_SdK = new ArrayList<SdkDefaulltListModel>();
    }

    public List<SdkDefaulltListModel> getSdkDefaultModel() {
        return Defult_SdK;
    }

    public void setSdkDefaultModel(List<SdkDefaulltListModel> Defult_SdK) {
        this.Defult_SdK = Defult_SdK;
    }


}
