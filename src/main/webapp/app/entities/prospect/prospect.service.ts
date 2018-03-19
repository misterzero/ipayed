import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Prospect } from './prospect.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Prospect>;

@Injectable()
export class ProspectService {

    private resourceUrl =  SERVER_API_URL + 'api/prospects';

    constructor(private http: HttpClient) { }

    create(prospect: Prospect): Observable<EntityResponseType> {
        const copy = this.convert(prospect);
        return this.http.post<Prospect>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(prospect: Prospect): Observable<EntityResponseType> {
        const copy = this.convert(prospect);
        return this.http.put<Prospect>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Prospect>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Prospect[]>> {
        const options = createRequestOption(req);
        return this.http.get<Prospect[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Prospect[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Prospect = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Prospect[]>): HttpResponse<Prospect[]> {
        const jsonResponse: Prospect[] = res.body;
        const body: Prospect[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Prospect.
     */
    private convertItemFromServer(prospect: Prospect): Prospect {
        const copy: Prospect = Object.assign({}, prospect);
        return copy;
    }

    /**
     * Convert a Prospect to a JSON which can be sent to the server.
     */
    private convert(prospect: Prospect): Prospect {
        const copy: Prospect = Object.assign({}, prospect);
        return copy;
    }
}
