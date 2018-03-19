import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Trans8Mar } from './trans-8-mar.model';
import { Trans8MarPopupService } from './trans-8-mar-popup.service';
import { Trans8MarService } from './trans-8-mar.service';
import { BankAccount, BankAccountService } from '../bank-account';
import { Customer, CustomerService } from '../customer';
import { TransactionType, TransactionTypeService } from '../transaction-type';

@Component({
    selector: 'jhi-trans-8-mar-dialog',
    templateUrl: './trans-8-mar-dialog.component.html'
})
export class Trans8MarDialogComponent implements OnInit {

    trans8Mar: Trans8Mar;
    isSaving: boolean;

    bankaccounts: BankAccount[];

    customers: Customer[];

    transactiontypes: TransactionType[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private trans8MarService: Trans8MarService,
        private bankAccountService: BankAccountService,
        private customerService: CustomerService,
        private transactionTypeService: TransactionTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.bankAccountService.query()
            .subscribe((res: HttpResponse<BankAccount[]>) => { this.bankaccounts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.customerService.query()
            .subscribe((res: HttpResponse<Customer[]>) => { this.customers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.transactionTypeService.query()
            .subscribe((res: HttpResponse<TransactionType[]>) => { this.transactiontypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.trans8Mar.id !== undefined) {
            this.subscribeToSaveResponse(
                this.trans8MarService.update(this.trans8Mar));
        } else {
            this.subscribeToSaveResponse(
                this.trans8MarService.create(this.trans8Mar));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Trans8Mar>>) {
        result.subscribe((res: HttpResponse<Trans8Mar>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Trans8Mar) {
        this.eventManager.broadcast({ name: 'trans8MarListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBankAccountById(index: number, item: BankAccount) {
        return item.id;
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackTransactionTypeById(index: number, item: TransactionType) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-trans-8-mar-popup',
    template: ''
})
export class Trans8MarPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private trans8MarPopupService: Trans8MarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.trans8MarPopupService
                    .open(Trans8MarDialogComponent as Component, params['id']);
            } else {
                this.trans8MarPopupService
                    .open(Trans8MarDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
