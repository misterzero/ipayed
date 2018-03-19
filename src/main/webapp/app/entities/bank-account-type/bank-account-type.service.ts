import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { BankAccountType } from './bank-account-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<BankAccountType>;

@Injectable()
export class BankAccountTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/bank-account-types';

    constructor(private http: HttpClient) { }

    create(bankAccountType: BankAccountType): Observable<EntityResponseType> {
        const copy = this.convert(bankAccountType);
        return this.http.post<BankAccountType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(bankAccountType: BankAccountType): Observable<EntityResponseType> {
        const copy = this.convert(bankAccountType);
        return this.http.put<BankAccountType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<BankAccountType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<BankAccountType[]>> {
        const options = createRequestOption(req);
        return this.http.get<BankAccountType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<BankAccountType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: BankAccountType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<BankAccountType[]>): HttpResponse<BankAccountType[]> {
        const jsonResponse: BankAccountType[] = res.body;
        const body: BankAccountType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to BankAccountType.
     */
    private convertItemFromServer(bankAccountType: BankAccountType): BankAccountType {
        const copy: BankAccountType = Object.assign({}, bankAccountType);
        return copy;
    }

    /**
     * Convert a BankAccountType to a JSON which can be sent to the server.
     */
    private convert(bankAccountType: BankAccountType): BankAccountType {
        const copy: BankAccountType = Object.assign({}, bankAccountType);
        return copy;
    }
}
