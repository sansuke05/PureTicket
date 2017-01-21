package click.kobaken.pureticket.data.repository;

import click.kobaken.pureticket.domain.entity.UserInfo;
import click.kobaken.pureticket.domain.repository.UserInfoRepository;
import rx.Observable;

public class UserInfoRepositoryImpl extends RetrofitRepository implements UserInfoRepository {

    private UserInfoRepository.UserInfoService userInfoService;

    public UserInfoRepositoryImpl() {
        super(buildClient());
        this.userInfoService = retrofit.create(UserInfoService.class);
    }

    @Override
    public Observable<UserInfo> getUserInfo(String uuid) {
        // mock
        UserInfo userInfo = new UserInfo();
        userInfo.alias = "makoto";
        userInfo.amount = 1000;
        userInfo.email = "takemitya@soramitsu.co.jp";
        userInfo.age = 24;
        userInfo.gender = "male";
        return Observable.just(userInfo);
    }
}
