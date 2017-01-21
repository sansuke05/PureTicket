package click.kobaken.pureticket.domain.repository;

import click.kobaken.pureticket.domain.entity.request.TransferRequest;
import click.kobaken.pureticket.domain.entity.response.ResponseObject;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface TransactionRepository {
    Observable<ResponseObject> sendPayment(TransferRequest body);

    interface TransactionService {
        @POST(value = "/asset/operation")
        Observable<ResponseObject> transfer(@Body TransferRequest body);
    }
}
