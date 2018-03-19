import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BankAccountType } from './bank-account-type.model';
import { BankAccountTypePopupService } from './bank-account-type-popup.service';
import { BankAccountTypeService } from './bank-account-type.service';

@Component({
    selector: 'jhi-bank-account-type-dialog',
    templateUrl: './bank-account-type-dialog.component.html'
})
export class BankAccountTypeDialogComponent implements OnInit {

    bankAccountType: BankAccountType;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private bankAccountTypeService: BankAccountTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.bankAccountType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bankAccountTypeService.update(this.bankAccountType));
        } else {
            this.subscribeToSaveResponse(
                this.bankAccountTypeService.create(this.bankAccountType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BankAccountType>>) {
        result.subscribe((res: HttpResponse<BankAccountType>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BankAccountType) {
        this.eventManager.broadcast({ name: 'bankAccountTypeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-bank-account-type-popup',
    template: ''
})
export class BankAccountTypePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private bankAccountTypePopupService: BankAccountTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.bankAccountTypePopupService
                    .open(BankAccountTypeDialogComponent as Component, params['id']);
            } else {
                this.bankAccountTypePopupService
                    .open(BankAccountTypeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
