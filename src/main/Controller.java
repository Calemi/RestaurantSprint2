package main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import order.Food;
import order.OrderQueue;
import profile.ActiveProfile;
import profile.AuthenticationManager;
import profile.Profile;
import profile.WaitStaffProfile;
import table.ActiveTable;
import table.DiningRoom;
import table.Table;

import java.io.IOException;

/**
 * Used by the scenes to add functionality to buttons and other components.
 */
public class Controller {

    /**
     * Used to get the correct Controller when switching scenes.
     */
    public static Controller instance;

    /**
     * The username field from the login scene.
     */
    @FXML

    private TextField username;
    /**
     * The password field from the login scene.
     */
    @FXML
    private PasswordField password;

    /**
     * This label's text is set to an error message if one occurs during login.
     */
    @FXML
    private Label loginError;


    /**
     * This label will be set to the active profile's username.
     */
    @FXML
    private Label profileName;

    /**
     * This button will disappear when on the table panel.
     */
    @FXML
    public Button backBtn;

    /**
     * This grid pane holds all of the table buttons.
     */
    @FXML
    public GridPane diningRoom;

    /**
     * This text area holds the tab information for the currently selected table.
     */
    @FXML
    private TextArea tabText;

    /**
     * This label's text is set to the overall total of the current tab.
     */
    @FXML
    private Label tabTotal;

    /**
     * This group holds all of the panels. The panels will activate and deactivate depending on the current selected panel.
     */
    @FXML
    private Group panelParent;

    /**
     * This grid pane holds all of the food buttons.
     */
    @FXML
    private GridPane foodBtnParent;


    /**
     * Called when the login confirm button is pressed.
     * Used to check authenticate the user's credentials and set the active profile if they're correct.
     */
    @FXML
    private void onBtnLoginConfirm(ActionEvent event) throws IOException {

        //Checks if the user's credentials are correct.
        if (AuthenticationManager.logIn(username.getText(), password.getText())) {

            //Changes to main scene.
            Scenes.loadScene(Scenes.getMainScene());

            //Switches to the dining room panel.
            setCurrentPanel(0);

            Profile profile = ActiveProfile.getActiveProfile();

            instance.profileName.setText(profile.getUsername());

            //Checks if the profile that logged in is a waitstaff.
            if (profile instanceof WaitStaffProfile) {

                WaitStaffProfile waitStaff = (WaitStaffProfile) profile;
                DiningRoom.getInstance().randomizeTableStates();
                DiningRoom.getInstance().assignTables(waitStaff);
            }
        }

        else instance.loginError.setText("! Username or password incorrect !");
    }

    /**
     * Called when the logout button is pressed.
     * Used to return back to the login scene and remove the active profile.
     */
    @FXML
    private void onBtnLogoutConfirm(ActionEvent event) throws IOException {
        Scenes.loadScene(Scenes.getLoginScene());
        AuthenticationManager.logOut();
    }

    /**
     * Called when the back button is pressed.
     * Used to return back to the dining room panel.
     */
    @FXML
    private void onBtnBack(ActionEvent event) {
        //Switches to the dining room panel.
        setCurrentPanel(0);
    }

    /**
     * Called when a table button is pressed.
     * Used to open the table's tab information.
     */
    @FXML
    private void onBtnTable(ActionEvent event) {

        //Gets the button.
        Button button = (Button) event.getSource();

        //Gets the table's ID by the button's text.
        int tableID = Integer.parseInt(button.getText()) - 1;

        //Gets table by ID.
        Table table = DiningRoom.getInstance().getTables()[tableID];

        //Checks if the table's state is an occupied state.
        if (table.getState() == Table.TableState.OCCUPIED) {

            //Switches to the tab panel.
            setCurrentPanel(1);

            //Sets the active table.
            ActiveTable.setActiveTable(table);

            //Updates the tab's information.
            updateTab();
        }
    }

    /**
     * Called when a add order button is pressed.
     * Used to open the categories panel.
     */
    @FXML
    private void onBtnAddOrder(ActionEvent event) {
        //Switches to the categories panel.
        setCurrentPanel(2);
    }

    /**
     * Called when a add select category button is pressed.
     * Used to open the foods panel.
     */
    @FXML
    private void onBtnSelectCategory(ActionEvent event) {

        //Switches to the foods panel.
        setCurrentPanel(3);

        //Gets the button's ID from it's internal fixed id.
        int buttonID = Integer.parseInt(((Button)event.getSource()).getId().replace("cat", ""));

        //Iterates through all of the food buttons.
        for (int i = 0; i < instance.foodBtnParent.getChildren().size(); i++) {

            //Checks if the current node is a button
            if (instance.foodBtnParent.getChildren().get(i) instanceof Button) {

                Button button = (Button) instance.foodBtnParent.getChildren().get(i);

                //Sets the button's text to the corresponding food's name.
                button.setText(Food.fromCategory(buttonID).get(i).getName());
            }
        }
    }

    /**
     * Called when a add food button is pressed.
     * Used to add the selected food to the active table's tab.
     */
    @FXML
    private void onBtnAddFood(ActionEvent event) {

        //Gets the button.
        Button button = (Button)event.getSource();

        //Gets the food from the button's text.
        Food food = Food.fromName(button.getText());

        //Checks if the food exists.
        if (food != null) {

            //Switches back to the tab panel.
            setCurrentPanel(1);

            //Adds the food to the tab.
            ActiveTable.getActiveTable().getTab().addToTab(food);

            //Updates the tab's information.
            updateTab();

            //Adds the food to the order queue.
            OrderQueue.getInstance().addToQueue(food);
        }
    }

    /**
     * Used to set the current panel in the main scene. The panels are given id's.
     * 0 - dining room panel
     * 1 - tab information panel
     * 2 - categories panel
     * 3 - foods panel
     */
    public void setCurrentPanel(int id) {

        //Makes the back button disappear when on the table panel.
        instance.backBtn.setVisible(id != 0);

        //Iterates through all of the panels.
        for (int i = 0; i < instance.panelParent.getChildren().size(); i++) {

            //Checks if the panel is a group.
            if (instance.panelParent.getChildren().get(i) instanceof Group) {

                //Gets the current group
                Group group = (Group) instance.panelParent.getChildren().get(i);

                //Checks if the id given by the parameter is the same as the group's id
                if (i == id) {
                    //Sets the group to visible.
                    group.setOpacity(1);
                    group.setDisable(false);
                }

                else {
                    //Sets the group to not visible.
                    group.setOpacity(0);
                    group.setDisable(true);
                }
            }
        }
    }

    /**
     * Used to update the active table's tab information.
     */
    public void updateTab() {

        Table table = ActiveTable.getActiveTable();

        tabText.setText(table.getTab().getTabString());
        tabTotal.setText("Total: $" + String.format("%.2f", table.getTab().calculateTotal()));
    }
}
