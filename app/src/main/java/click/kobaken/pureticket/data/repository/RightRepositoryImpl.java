package click.kobaken.pureticket.data.repository;

import java.util.List;

import click.kobaken.pureticket.domain.entity.Right;
import click.kobaken.pureticket.domain.entity.request.TransferRequest;
import click.kobaken.pureticket.domain.entity.response.ResponseObject;
import click.kobaken.pureticket.domain.repository.RightRepository;
import rx.Observable;


public class RightRepositoryImpl extends RetrofitRepository implements RightRepository {

    private RightService rightService;

    public RightRepositoryImpl() {
        super(buildClient());
        rightService = retrofit.create(RightService.class);
    }

    @Override
    public Observable<ResponseObject> sendRight(TransferRequest body) {
        // mock
        ResponseObject responseObject = new ResponseObject();
        responseObject.status = 200;
        responseObject.message = "Successful send a payment!";
        return Observable.just(responseObject);
    }

    @Override
    public Observable<List<Right>> getRights(String uuid) {
        // mock
        Right right = new Right();
        right.initializeData();
        return Observable.just(right.getRights());
    }
}
