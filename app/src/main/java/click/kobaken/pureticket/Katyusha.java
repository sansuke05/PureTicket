package click.kobaken.pureticket;

import android.app.Application;

import click.kobaken.pureticket.data.repository.UserInfoRepositoryImpl;
import click.kobaken.pureticket.domain.entity.UserInfo;
import click.kobaken.pureticket.domain.repository.UserInfoRepository;
import io.soramitsu.irohaandroid.model.KeyPair;
import io.soramitsu.irohaandroid.security.KeyGenerator;

public class Katyusha extends Application {
    private KeyPair keyPair;
    private UserInfo userInfo;

    private UserInfoRepository userInfoRepository = new UserInfoRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
        keyPair = KeyGenerator.createKeyPair();;
        userInfo = userInfoRepository.getUserInfo("hoge").single().toBlocking().single();
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
