package edu.lab04_add00;

public class MyClass {
    public int publicField;
    private String privateField;

    public MyClass(int publicField, String privateField) {
        this.publicField = publicField;
        this.privateField = privateField;
    }

    // Getter and setter for private field
    public String getPrivateField() {
        return privateField;
    }

    public void setPrivateField(String privateField) {
        this.privateField = privateField;
    }
}
