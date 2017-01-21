package click.kobaken.pureticket.data.repository;

import click.kobaken.pureticket.domain.entity.request.TransferRequest;
import click.kobaken.pureticket.domain.entity.response.ResponseObject;
import click.kobaken.pureticket.domain.repository.TransactionRepository;
import rx.Observable;

public class TransactionRepositoryImpl extends RetrofitRepository implements TransactionRepository {
    private TransactionService transactionService;

    public TransactionRepositoryImpl() {
        super(buildClient());
        this.transactionService = retrofit.create(TransactionService.class);
    }

    @Override
    public Observable<ResponseObject> sendPayment(TransferRequest body) {
        // mock
        ResponseObject responseObject = new ResponseObject();
        responseObject.status = 200;
        responseObject.message = "Successful send a payment!";
        return Observable.just(responseObject);
    }
}
