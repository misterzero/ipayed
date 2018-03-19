import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { TransactionType } from './transaction-type.model';
import { TransactionTypeService } from './transaction-type.service';
import { Principal } from '../../shared';

@Component({
    selector: 'jhi-transaction-type',
    templateUrl: './transaction-type.component.html'
})
export class TransactionTypeComponent implements OnInit, OnDestroy {
transactionTypes: TransactionType[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private transactionTypeService: TransactionTypeService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.transactionTypeService.query().subscribe(
            (res: HttpResponse<TransactionType[]>) => {
                this.transactionTypes = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInTransactionTypes();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: TransactionType) {
        return item.id;
    }
    registerChangeInTransactionTypes() {
        this.eventSubscriber = this.eventManager.subscribe('transactionTypeListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
