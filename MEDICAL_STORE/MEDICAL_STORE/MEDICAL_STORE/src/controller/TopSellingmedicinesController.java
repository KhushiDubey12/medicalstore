package controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.DataManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TopSellingmedicinesController {
    public TableView topmedicinesTable;

    @FXML
    public void initialize() throws SQLException {
        topmedicinesTable.getItems().clear();
        topmedicinesTable.getColumns().clear();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.add(Calendar.MONTH, -3);
        String dateThreeMonthsBefore = sdf.format(c.getTime());

        ResultSet ordersResultSet = DataManager.getInstance().getTopSellingmedicines(dateThreeMonthsBefore);
        int n = ordersResultSet.getMetaData().getColumnCount();

        for(int i=0; i<n; i++){
            TableColumn<List<String>, String> column = new TableColumn<>(ordersResultSet.getMetaData().getColumnName(i+1));
            final int colIndex = i;
            column.setCellValueFactory(data -> {
                List<String> rowValues = data.getValue();
                String cellValue ;
                if (colIndex < rowValues.size()) {
                    cellValue = rowValues.get(colIndex);
                } else {
                    cellValue = "";
                }
                return new ReadOnlyStringWrapper(cellValue);
            });
            topmedicinesTable.getColumns().add(column);
        }

        while(ordersResultSet.next()){
            ArrayList<String> row = new ArrayList<>();
            for(int i=1; i<=n; i++){
                row.add(ordersResultSet.getString(i));
            }
            System.out.println("Row: " + row);
            topmedicinesTable.getItems().add(row);
        }
    }
}
