package click.kobaken.pureticket.domain.repository;

import java.util.List;

import click.kobaken.pureticket.domain.entity.Right;
import click.kobaken.pureticket.domain.entity.request.TransferRequest;
import click.kobaken.pureticket.domain.entity.response.ResponseObject;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface RightRepository {
    Observable<ResponseObject> sendRight(TransferRequest body);

    Observable<List<Right>> getRights(String uuid);

    interface RightService {
        @POST(value = "/asset/operation")
        Observable<ResponseObject> transfer(@Body TransferRequest body);

        @GET(value = "/asset/list/{DOMAIN}")
        Observable<List<Right>> assets(@Body TransferRequest body);
    }
}
