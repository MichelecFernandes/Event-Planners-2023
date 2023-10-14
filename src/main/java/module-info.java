module br.com.sistemabancario {
    requires javafx.controls;
    requires javafx.fxml;

    exports br.com.eventplanners.main;
    opens br.com.eventplanners.main to javafx.fxml;

    exports br.com.eventplanners.controlador;
    opens br.com.eventplanners.controlador to javafx.fxml;

    exports br.com.eventplanners.controladorTela;
    opens br.com.eventplanners.controladorTela to javafx.fxml;

    exports br.com.eventplanners.cadastros;
    opens br.com.eventplanners.cadastros to javafx.fxml;

    exports br.com.eventplanners.manipulacaoArquivo;
    opens br.com.eventplanners.manipulacaoArquivo to javafx.fxml;


}