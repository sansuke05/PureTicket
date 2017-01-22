package click.kobaken.pureticket.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

import click.kobaken.pureticket.Katyusha;
import click.kobaken.pureticket.R;

import click.kobaken.pureticket.view.dialogs.MyProgressDialog;
import click.kobaken.pureticket.view.dialogs.SuccessDialog;
import io.soramitsu.irohaandroid.Iroha;
import io.soramitsu.irohaandroid.callback.Callback;
import io.soramitsu.irohaandroid.model.Account;

public class RegisterActivity extends AppCompatActivity{

    private static final String IROHA_TASK_TAG_ACCOUNT_REGISTER = "AccountRegister";

    //Dialog
    MyProgressDialog mProgressDialog = new MyProgressDialog();

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText = (EditText)findViewById(R.id.editText);
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

            mProgressDialog.show(RegisterActivity.this, "登録中", "登録中");

            String name = editText.getText().toString();
            String email = editText2.getText().toString();
            String birthday = editText3.getText().toString();
            String responce = "Name:" + name + " E-mail:" + email + " Gender" + gender + " Birthday:" + birthday;
            //Toast.makeText(RegisterActivity.this, responce, Toast.LENGTH_LONG).show();

            Katyusha katyusha = ((Katyusha) getApplication());
            Iroha iroha = Iroha.getInstance();
            iroha.runAsyncTask(
                    IROHA_TASK_TAG_ACCOUNT_REGISTER,
                    iroha.registerAccountFunction(katyusha.getPublicKey(), name),
                    callback()
            );

        });
    }

    private Callback<Account> callback() {
        return new Callback<Account>() {
            @Override
            public void onSuccessful(Account result) {
                registerSuccessful(result);
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        };
    }

    private void registerSuccessful(Account result){
        mProgressDialog.hide();
        Context context = getApplicationContext();

        try {
            result.alias = editText.getText().toString();
            result.save(context);
        } catch (InvalidKeyException | NoSuchAlgorithmException
                | KeyStoreException | NoSuchPaddingException | IOException e){
            Log.e("RegisterActivity", "onSuccessfull: ", e);
            return;
        }

        Intent intent = new Intent(getApplication(), MainActivity.class);
        startActivity(intent);
        /*
        LayoutInflater inflater = LayoutInflater.from(context);
        SuccessDialog successDialog = new SuccessDialog(inflater);
        successDialog.show();
        */

    }
}
