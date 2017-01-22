package click.kobaken.pureticket.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import click.kobaken.pureticket.R;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText editText = (EditText)findViewById(R.id.editText);
        EditText editText2 = (EditText)findViewById(R.id.editText2);
        EditText editText3 = (EditText)findViewById(R.id.editText3);
        Button button = (Button)findViewById(R.id.button);
        RadioGroup mRadioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        button.setOnClickListener(v ->  {
            int checkedId = mRadioGroup.getCheckedRadioButtonId();
            String gender = "";
            if (-1 != checkedId){
                gender = ((RadioButton)findViewById(checkedId)).getText().toString();
            } else {
                Toast.makeText(RegisterActivity.this, "性別を選択して下さい", Toast.LENGTH_LONG).show();
                return;
            }

            String name = editText.getText().toString();
            String email = editText2.getText().toString();
            String birthday = editText3.getText().toString();
            String responce = "Name:" + name + " E-mail:" + email + " Gender" + gender + " Birthday:" + birthday;
            //Toast.makeText(RegisterActivity.this, responce, Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplication(), MainActivity.class);
            startActivity(intent);
        });
    }
}
