module com.example.four_x_four_tic_tac_toe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens com.example.four_x_four_tic_tac_toe to javafx.fxml;
    exports com.example.four_x_four_tic_tac_toe;
}