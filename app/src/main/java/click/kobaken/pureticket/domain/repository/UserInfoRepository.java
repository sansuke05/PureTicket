package click.kobaken.pureticket.domain.repository;

import click.kobaken.pureticket.domain.entity.UserInfo;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface UserInfoRepository {
    Observable<UserInfo> getUserInfo(String uuid);

    interface UserInfoService {
        @GET(value = "/account")
        Observable<UserInfo> userInfo(@Query("uuid") String uuid);
    }
}
