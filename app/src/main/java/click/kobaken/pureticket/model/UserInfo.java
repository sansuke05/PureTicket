package click.kobaken.pureticket.model;

import android.graphics.drawable.Drawable;

import io.soramitsu.irohaandroid.model.Account;

public class UserInfo extends Account {
    public String email;
    public String gender;
    public int age;
    public Drawable image;

    public static UserInfo createMock() {
        UserInfo userInfo = new UserInfo();
        userInfo.alias = "こばけん";
        userInfo.email = "kobaken0029@gmail.com";
        userInfo.gender = "男";
        userInfo.age = 22;
        return userInfo;
    }
}
