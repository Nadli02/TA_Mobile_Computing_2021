package com.example.ta_mobile_computing;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText Email, Password, Re_password, Name ;
    Button Register;
    String NameHolder, EmailHolder, PasswordHolder, Re_passwordHolder, Password_1, Password_2;
    Boolean EditTextEmptyHolder, MatchPassword;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register = (Button)findViewById(R.id.buttonRegister);
        Email = (EditText)findViewById(R.id.editEmail);
        Password = (EditText)findViewById(R.id.editPassword);
        Re_password = (EditText)findViewById(R.id.edit_re_password);
        Name = (EditText)findViewById(R.id.editName);
        sqLiteHelper = new SQLiteHelper(this);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Method Create Db Sqlite
                SQLiteDataBaseBuild();
                // Method Db Sqlite
                SQLiteTableBuild();
                // Method Cek Status Email
                CheckEditTextStatus();
                CheckPassword();
                // Method cek email yang sudah ada di db Sqlite
                CheckingEmailAlreadyExistsOrNot();
                // Method Proses insert email password
                EmptyEditTextAfterDataInsert();
            }
        });
    }
    // SQLite database build method.
    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj =
                openOrCreateDatabase(SQLiteHelper.DATABASE_NAME,
                        Context.MODE_PRIVATE, null);
    }
    // SQLite table build method.
    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " +
                SQLiteHelper.TABLE_NAME + "(" + SQLiteHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                SQLiteHelper.Table_Column_1_Name + " VARCHAR, " +
                SQLiteHelper.Table_Column_2_Email + " VARCHAR, " +
                SQLiteHelper.Table_Column_3_Password + " VARCHAR);");
    }
    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){
        if(EditTextEmptyHolder == true && MatchPassword == true)
        {
            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,email,password) VALUES('"+NameHolder+"', '"+EmailHolder+"', '"+PasswordHolder+"');";
            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            // Closing SQLite database object.
            sqLiteDatabaseObj.close();
            // Printing toast message after done inserting.
            AlertDialog.Builder builder = new
                    AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("User Registered Successfully").setNegativeButton("OK",null).create().show();
        }
        else if (EditTextEmptyHolder == false){
            // Messages Pop Up
            AlertDialog.Builder builder = new
                    AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("Please Fill All The Required Fields").setNegativeButton("OK",null).create().show();
        }
        else if (MatchPassword == false){
            AlertDialog.Builder builder = new
                    AlertDialog.Builder(RegisterActivity.this);
            builder.setMessage("Password Do Not Match").setNegativeButton("OK",null).create().show();
        }
    }
    // Method Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        Name.getText().clear();
        Email.getText().clear();
        Password.getText().clear();
        Re_password.getText().clear();
    }
    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){
        NameHolder = Name.getText().toString() ;
        EmailHolder = Email.getText().toString();
        PasswordHolder = Password.getText().toString();
        Re_passwordHolder = Re_password.getText().toString();
        if(TextUtils.isEmpty(NameHolder) ||
                TextUtils.isEmpty(EmailHolder) ||
                TextUtils.isEmpty(PasswordHolder) ||
                TextUtils.isEmpty((Re_passwordHolder))){
            EditTextEmptyHolder = false ;
        }
        else {
            EditTextEmptyHolder = true ;
        }
    }
    public void CheckPassword() {
        Password_1 = Password.getText().toString();
        Password_2 = Re_password.getText().toString();
        if(Password_1.equals(Password_2)){
            MatchPassword = true;
        }
        else {
            MatchPassword = false;
        }
    }
    // Method Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){
        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();
        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query(SQLiteHelper.TABLE_NAME, null, " " + SQLiteHelper.Table_Column_2_Email + "=?", new
                String[]{EmailHolder}, null, null, null);
        while (cursor.moveToNext()) {
            if (cursor.isFirst()) {
                cursor.moveToFirst();
                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Email Found";
                // Closing cursor.
                cursor.close();
            }
        }
        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();
    }
    // Checking result
    public void CheckFinalResult(){
        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found"))
        {
            // If email is exists then toast msg will display.
            Toast.makeText(RegisterActivity.this,"Email Already Exists", Toast.LENGTH_LONG).show();
        }
        else {
            // If email already dose n't exists then userregistration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();
        }
        F_Result = "Not_Found" ;
    }
    public void Beck(View view) {
        Intent intent = new
                Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}