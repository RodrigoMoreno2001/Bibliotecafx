package com.example.bibliotecafx.util;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DatePickerTableCell<S> extends TableCell<S, LocalDate> {
    private final DatePicker datePicker = new DatePicker();
    private final Label label = new Label();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public DatePickerTableCell() {
        datePicker.setOnAction(e -> commitEdit(datePicker.getValue()));
    }

    @Override
    protected void updateItem(LocalDate fecha, boolean empty) {
        super.updateItem(fecha, empty);
        if (empty || fecha == null) {
            setGraphic(null);
        } else {
            datePicker.setValue(fecha);
            label.setText(fecha.format(formatter));

            if (isEditing()) {
                setGraphic(datePicker);
            } else {
                setGraphic(label);
            }
        }
    }

    @Override
    public void startEdit() {
        if (!isEditable() || !getTableColumn().isEditable() || !getTableView().isEditable()) {
            return;
        }
        super.startEdit();
        setGraphic(datePicker);
        datePicker.requestFocus();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setGraphic(label);
    }

    @Override
    public void commitEdit(LocalDate newValue) {
        super.commitEdit(newValue);
        label.setText(newValue.format(formatter));
        setGraphic(label);
    }
}
