package com.example.acer.h1129_01;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private  Button button_calc;
    private  EditText field_height;
    private  EditText field_weight;
    private  TextView view_result;
    private  TextView view_suggest;

    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST + 1;


//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_ABOUT, 0, "关于...");
        menu.add(0, MENU_Quit, 0, "结束");
        return true;
    }



//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case MENU_ABOUT:
                openOptionsDialog();
                break;
            case MENU_Quit:
                finish();
                break;
        }
        return true;
    }

    private void findViews() {
        button_calc = (Button) findViewById(R.id.submit);
        field_height = (EditText) findViewById(R.id.height);
        field_weight = (EditText) findViewById(R.id.weight);
        view_result = (TextView) findViewById(R.id.result);
        view_suggest = (TextView) findViewById(R.id.suggest);
    }


    //Listen for button clicks
    private void setListeners() {
        button_calc.setOnClickListener(calcBMI);
    }

    //弹出对话框
    private void openOptionsDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle(R.string.about_title);
        dialog.setMessage(R.string.about_msg);

        //"确认" 按键
        dialog.setPositiveButton(R.string.ok_label,
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialoginterface, int i) {
                    }
                });

//        Toast popup = Toast.makeText(MainActivity.this, R.string.input_error, Toast.LENGTH_LONG);
//        popup.show();

        //"首页" 按键
        dialog.setNegativeButton(R.string.homepage_label,
                new DialogInterface.OnClickListener() {
                    public void onClick(
                            DialogInterface dialoginterface, int i) {
                        //go to url
                        Uri uri = Uri.parse(getString(R.string.homepage_uri));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                });

        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

//        //Listen for button clicks
//        Button button = (Button) findViewById(R.id.submit);
//        button.setOnClickListener(calcBMI);
        findViews();
        setListeners();

        //
//        openOptionsDialog();
    }




    private View.OnClickListener calcBMI =  new  View.OnClickListener()
    {
        public  void onClick ( View v )
        {
            DecimalFormat nf =  new  DecimalFormat ( "0.00" );

            double height =  Double.parseDouble ( field_height.getText (). toString ())/ 100 ;
            double weight =  Double.parseDouble ( field_weight.getText (). toString ());
            double BMI = weight /  ( height * height );

            view_result =  ( TextView ) findViewById ( R.id.result );
            view_result.setText("Your BMI is " + nf.format(BMI));

            //Give health advice
            view_suggest =  ( TextView ) findViewById ( R.id.suggest );
            if ( BMI > 25 ){
                view_suggest.setText ( R.string.advice_heavy);
            } else  if ( BMI < 20 ){
                view_suggest.setText ( R.string.advice_light );
            } else {
                view_suggest.setText ( R.string.advice_average );
            }
        }
    };


}
