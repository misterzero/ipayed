import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BadGuyList } from './bad-guy-list.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BadGuyList>;

@Injectable()
export class BadGuyListService {

    private resourceUrl =  SERVER_API_URL + 'api/bad-guy-lists';

    constructor(private http: HttpClient) { }

    create(badGuyList: BadGuyList): Observable<EntityResponseType> {
        const copy = this.convert(badGuyList);
        return this.http.post<BadGuyList>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(badGuyList: BadGuyList): Observable<EntityResponseType> {
        const copy = this.convert(badGuyList);
        return this.http.put<BadGuyList>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BadGuyList>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BadGuyList[]>> {
        const options = createRequestOption(req);
        return this.http.get<BadGuyList[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BadGuyList[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BadGuyList = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BadGuyList[]>): HttpResponse<BadGuyList[]> {
        const jsonResponse: BadGuyList[] = res.body;
        const body: BadGuyList[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BadGuyList.
     */
    private convertItemFromServer(badGuyList: BadGuyList): BadGuyList {
        const copy: BadGuyList = Object.assign({}, badGuyList);
        return copy;
    }

    /**
     * Convert a BadGuyList to a JSON which can be sent to the server.
     */
    private convert(badGuyList: BadGuyList): BadGuyList {
        const copy: BadGuyList = Object.assign({}, badGuyList);
        return copy;
    }
}
