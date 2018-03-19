import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Trans8Mar } from './trans-8-mar.model';
import { Trans8MarPopupService } from './trans-8-mar-popup.service';
import { Trans8MarService } from './trans-8-mar.service';

@Component({
    selector: 'jhi-trans-8-mar-delete-dialog',
    templateUrl: './trans-8-mar-delete-dialog.component.html'
})
export class Trans8MarDeleteDialogComponent {

    trans8Mar: Trans8Mar;

    constructor(
        private trans8MarService: Trans8MarService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.trans8MarService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'trans8MarListModification',
                content: 'Deleted an trans8Mar'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trans-8-mar-delete-popup',
    template: ''
})
export class Trans8MarDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private trans8MarPopupService: Trans8MarPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.trans8MarPopupService
                .open(Trans8MarDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
