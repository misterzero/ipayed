import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CustomerActivity } from './customer-activity.model';
import { CustomerActivityPopupService } from './customer-activity-popup.service';
import { CustomerActivityService } from './customer-activity.service';
import { Customer, CustomerService } from '../customer';

@Component({
    selector: 'jhi-customer-activity-dialog',
    templateUrl: './customer-activity-dialog.component.html'
})
export class CustomerActivityDialogComponent implements OnInit {

    customerActivity: CustomerActivity;
    isSaving: boolean;

    customers: Customer[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerActivityService: CustomerActivityService,
        private customerService: CustomerService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.customerService.query()
            .subscribe((res: HttpResponse<Customer[]>) => { this.customers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerActivity.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerActivityService.update(this.customerActivity));
        } else {
            this.subscribeToSaveResponse(
                this.customerActivityService.create(this.customerActivity));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerActivity>>) {
        result.subscribe((res: HttpResponse<CustomerActivity>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerActivity) {
        this.eventManager.broadcast({ name: 'customerActivityListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-activity-popup',
    template: ''
})
export class CustomerActivityPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerActivityPopupService: CustomerActivityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerActivityPopupService
                    .open(CustomerActivityDialogComponent as Component, params['id']);
            } else {
                this.customerActivityPopupService
                    .open(CustomerActivityDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
