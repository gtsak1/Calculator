package com.calc.calculator.DataBase.Model;

public class Calculator {
    public static final String TABLE_NAME = "Calculator_History";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_INPUT = "input";
    public static final String COLUMN_RESULT = "result";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String input;
    private String result;
    private String timestamp;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_INPUT + " TEXT,"
                    + COLUMN_RESULT + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT (DATETIME(CURRENT_TIMESTAMP, 'LOCALTIME'))"
                    + ")";

    public Calculator() {
    }

    public Calculator(int id, String input, String result, String timestamp) {
        this.id = id;
        this.input = input;
        this.result = result;
        this.timestamp = timestamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
