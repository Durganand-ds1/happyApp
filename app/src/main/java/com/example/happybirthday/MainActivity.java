package com.example.happybirthday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    int a=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void submit(View view ){
        //7 is the fixed value in the java as we know


        CheckBox checkbox = (CheckBox) findViewById(R.id.cream);
        CheckBox sugar = (CheckBox) findViewById(R.id.sugar);
        CheckBox free = (CheckBox) findViewById(R.id.free);
        CheckBox Balanced = (CheckBox) findViewById(R.id.Balanced);
        EditText name = (EditText) findViewById(R.id.plain_text_input);
        String nameT =  name.getText().toString();
        boolean checked=checkbox.isChecked();
        boolean sug=sugar.isChecked();
        boolean fre = free.isChecked();
        boolean Bal=Balanced.isChecked();

            if(a>0) {
                printreciept(checked,sug,fre,Bal,nameT,a);
            }else  if(a==0){
                calc(a);
            }


    }
    public void Add(View view){
        a=a+1;

        if(a>10){
            a=10;
        }String d ="extra tax will be added";
        display(a);

    }
    public void Subtract(View view ){
        a=a-1;
        if(a<0){
            a=0;
        }
        String d ="extra tax will be added";
        display(a);

    }
    private void display(int c){
        TextView main_text=(TextView) findViewById(R.id.main_text);
        main_text.setText(""+c);

    }
    private void calc(int d){
        TextView pin_text=(TextView) findViewById(R.id.pin_text);
        pin_text.setText(NumberFormat.getCurrencyInstance().format(d));
    }
    private void prints(String k){
        TextView cpin_text=(TextView) findViewById(R.id.cpin_text);
        cpin_text.setText(k);
    }
    private  void printreciept(boolean checked,boolean sug,boolean free,boolean Bal,String name,int c){
        String ch= "Cream is added? ";
        String sugar ="Is sugar added? "+sug;
        String Free ="It is sugar free? "+free;
        String bal = "Coffee is Balance? "+Bal;
        String text = "Name ->" +name;
        if(checked==true){
            c = c+2;
        }
        if(sug==true){
            c = c+3;


        }
        if(free == true){
            c=c+0;
        }
        if(Bal==true){
            c=c+3;
        }
        calc(c);
        String pr="in indian Rs." +c*2;
        String d = text+"\n" + ch+ checked +"\n" + Free +"\n" + bal+"\n"+ sugar + "\n"+ pr ;
        prints(d);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "some@email.address" });
        intent.putExtra(Intent.EXTRA_SUBJECT, name);
        intent.putExtra(Intent.EXTRA_TEXT, d);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, ""));
        }

    }



}