package software.showsdklist.view;

import java.io.IOException;
import java.util.List;

import com.google.common.collect.Table;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import software.showsdklist.viewmodel.ShowSdkListViewModel;
import software.utils.SdkListHandler.Handlers.SdkDefaultListHandler;
import software.utils.SdkListHandler.models.SdkDefaulltListModel;
import software.utils.Sessions.UserSession;
import javafx.scene.Node;



public class ShowSdkListView {

    @FXML
    private JFXButton ReturnButton;

    @FXML
    private Label UsernameLable;
    @FXML
    private TableView<SdkDefaulltListModel> SdkListTable;
    // get  the current userSession
    private UserSession mUserSession = UserSession.getInstance();
    private SdkDefaultListHandler sdkDefaultListHandler = new SdkDefaultListHandler();
    //create a viewModel
    private ShowSdkListViewModel mShowSdkListViewModel = new ShowSdkListViewModel(mUserSession,sdkDefaultListHandler);
    
    @FXML
    void initialize()
    {

        
        
        // Init the Data for the TableView
        mShowSdkListViewModel.setSdkDefaultModel();

        // get the obserableList 
        var data = mShowSdkListViewModel.getSdkDefaultModel();
        createTable(data);
        
        // bindings 
        UsernameLable.textProperty().bind(mShowSdkListViewModel.getUserName());

        ReturnButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                switchToMainMenu(event); 
            }
        });
    }

    public void switchToMainMenu(ActionEvent event) 
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
            Parent root = loader.load();
            Scene registerScene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(registerScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createTable(ObservableList<SdkDefaulltListModel> data)
    {
        List<String> fields = List.of("sdkName","sdkVersion","sdkType","sdkDescription","sdkPath");
        
            for(String field : fields)
            {
                TableColumn<SdkDefaulltListModel,String> column = new TableColumn<SdkDefaulltListModel,String>();
                column.setText(field);
                column.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSdkName()));
                SdkListTable.getColumns().add(column);
            }
    }
    
}
