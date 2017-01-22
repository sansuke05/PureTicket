package click.kobaken.pureticket;

import android.app.Application;

import click.kobaken.pureticket.model.UserInfo;
import io.soramitsu.irohaandroid.Iroha;
import io.soramitsu.irohaandroid.model.KeyPair;
import io.soramitsu.irohaandroid.security.KeyGenerator;

public class Katyusha extends Application {
    private KeyPair keyPair;
    private UserInfo userInfo;

    @Override
    public void onCreate() {
        super.onCreate();
        keyPair = KeyGenerator.createKeyPair();
        userInfo = UserInfo.createMock();
        new Iroha.Builder()
                .baseUrl("https:45.76.148.248:80/")
                .build();
    }

    public String getPublicKey() {
        return keyPair.publicKey;
    }

    public String getPrivateKey() {
        return keyPair.privateKey;
    }

    public UserInfo getUserInfo() {
        if (userInfo.alias.equals("makoto")) {
            userInfo.image = getDrawable(R.drawable.takemiya);
        } else {
            userInfo.image = getDrawable(R.drawable.tony);
        }
        return userInfo;
    }
}
