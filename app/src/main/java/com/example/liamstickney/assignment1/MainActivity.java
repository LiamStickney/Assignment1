package com.example.liamstickney.assignment1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.liamstickney.assignment1.model.Student;

import java.util.ArrayList;

//extends activity, not AppCompatActivity as this app isn't backwards compatible
public class MainActivity extends Activity {

    //defining the array and boolean: used for duplicate data check
    private ArrayList<Student> students = new ArrayList<>();
    private EditText edtName;
    private EditText edtNumber;
    private TextView txtDisplay;
    private boolean dupeData;

    //boolean method that checks if either of the text input fields are empty
    //is called in the btnAdd method and won't add a student if empty() returns true
    private boolean emptyEditText() {
        return edtName.getText().toString().isEmpty() || edtNumber.getText().toString().isEmpty();
    }

    //within the onCreate method, the button onclick methods are defined
    //also sets button/edittext/textdisplay variables to the widgets in the activity_main
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnList = findViewById(R.id.btnList);
        Button btnClear = findViewById(R.id.btnClear);
        edtName = findViewById(R.id.edtName);
        edtNumber = findViewById(R.id.edtNumber);
        txtDisplay = findViewById(R.id.txtDisplay);

        /*
        the onclick method for the btnAdd button
        first checks the emptyEditText method to see if the inputs are empty and displays appropriate error
        if the integer value entered exceeds the maximum or minimum allowed value, an exception is caught
        creates new Student object that is constructed with its name, number and the resource string
        checks the name for a number; returns error if there's a number
        regex retrieved from https://stackoverflow.com/questions/18590901/check-if-a-string-contains-numbers-java
        checks the arraylist to check for a duplicate number
        if no duplicate is detected, adds the newStudent to the list
        */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (emptyEditText()) {
                    txtDisplay.setText("");
                    if (edtName.getText().toString().isEmpty() && !edtNumber.getText().toString().isEmpty()) {
                        edtName.setError(getString(R.string.error_Name));
                    } else if (edtNumber.getText().toString().isEmpty() && !edtName.getText().toString().isEmpty()) {
                        edtNumber.setError(getString((R.string.error_Number)));
                    } else {
                        edtName.setError(getString(R.string.error_Name));
                        edtNumber.setError(getString((R.string.error_Number)));
                    }
                    return;
                }

                try {
                    Student newStudent = new Student(getString(R.string.display_student), edtName.getText().toString(), Integer.parseInt(edtNumber.getText().toString()));
                    for (int i = 0; i < students.size(); i++) {
                        if (newStudent.getNumber() == (students.get(i).getNumber())) {
                            dupeData = true;
                            break;
                        } else {
                            dupeData = false;
                        }
                    }


                    if (!dupeData) {
                        if (newStudent.getName().matches(".*\\d+.*")) {
                            edtName.setError(getString(R.string.name_contains_number));
                            return;
                        }

                        if (newStudent.getNumber() < 1) {
                            edtNumber.setError(getString(R.string.error_Number));
                            return;
                        }

                        students.add(newStudent);
                        txtDisplay.setText(getString(R.string.student_Add) + getString(R.string.new_line) + newStudent);
                        edtName.setText("");
                        edtNumber.setText("");
                    } else {
                        txtDisplay.setText(getString(R.string.student_Exist));
                    }
                }

                catch (NumberFormatException e) {
                    edtNumber.setError(getString(R.string.int_value_exceeded));
                }

            }
        });

        /*
        the onclick method for the list button
        first, checks if the list is empty
        if not empty, creates a string builder that collects each of the student's data
        the string builder is then displayed
        */
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (students.isEmpty()) {
                    txtDisplay.setText(getString(R.string.student_No_Find));
                } else {
                    txtDisplay.setText("");
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < students.size(); i++) {
                        sb.append(students.get(i) + getString(R.string.new_line));
                    }
                    txtDisplay.setText(sb);
                }
            }
        });
        /*
        the onclick method for the clear list button
        first, checks if the list is already empty
        if not, clears the inputs and the students list
        */

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (students.isEmpty()) {
                    txtDisplay.setText(getString(R.string.list_is_empty));
                } else {
                    edtName.setText("");
                    edtNumber.setText("");
                    students.clear();
                    txtDisplay.setText(getString(R.string.list_Clear));
                }
            }
        });

    }


}
