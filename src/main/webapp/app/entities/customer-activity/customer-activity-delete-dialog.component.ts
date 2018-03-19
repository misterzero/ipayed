import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerActivity } from './customer-activity.model';
import { CustomerActivityPopupService } from './customer-activity-popup.service';
import { CustomerActivityService } from './customer-activity.service';

@Component({
    selector: 'jhi-customer-activity-delete-dialog',
    templateUrl: './customer-activity-delete-dialog.component.html'
})
export class CustomerActivityDeleteDialogComponent {

    customerActivity: CustomerActivity;

    constructor(
        private customerActivityService: CustomerActivityService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerActivityService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerActivityListModification',
                content: 'Deleted an customerActivity'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-activity-delete-popup',
    template: ''
})
export class CustomerActivityDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerActivityPopupService: CustomerActivityPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerActivityPopupService
                .open(CustomerActivityDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
