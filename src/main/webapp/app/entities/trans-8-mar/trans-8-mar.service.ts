import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Trans8Mar } from './trans-8-mar.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Trans8Mar>;

@Injectable()
export class Trans8MarService {

    private resourceUrl =  SERVER_API_URL + 'api/trans-8-mars';

    constructor(private http: HttpClient) { }

    create(trans8Mar: Trans8Mar): Observable<EntityResponseType> {
        const copy = this.convert(trans8Mar);
        return this.http.post<Trans8Mar>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(trans8Mar: Trans8Mar): Observable<EntityResponseType> {
        const copy = this.convert(trans8Mar);
        return this.http.put<Trans8Mar>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Trans8Mar>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Trans8Mar[]>> {
        const options = createRequestOption(req);
        return this.http.get<Trans8Mar[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Trans8Mar[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Trans8Mar = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Trans8Mar[]>): HttpResponse<Trans8Mar[]> {
        const jsonResponse: Trans8Mar[] = res.body;
        const body: Trans8Mar[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Trans8Mar.
     */
    private convertItemFromServer(trans8Mar: Trans8Mar): Trans8Mar {
        const copy: Trans8Mar = Object.assign({}, trans8Mar);
        return copy;
    }

    /**
     * Convert a Trans8Mar to a JSON which can be sent to the server.
     */
    private convert(trans8Mar: Trans8Mar): Trans8Mar {
        const copy: Trans8Mar = Object.assign({}, trans8Mar);
        return copy;
    }
}
