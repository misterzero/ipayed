import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Prospect } from './prospect.model';
import { ProspectPopupService } from './prospect-popup.service';
import { ProspectService } from './prospect.service';

@Component({
    selector: 'jhi-prospect-delete-dialog',
    templateUrl: './prospect-delete-dialog.component.html'
})
export class ProspectDeleteDialogComponent {

    prospect: Prospect;

    constructor(
        private prospectService: ProspectService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.prospectService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'prospectListModification',
                content: 'Deleted an prospect'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-prospect-delete-popup',
    template: ''
})
export class ProspectDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private prospectPopupService: ProspectPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.prospectPopupService
                .open(ProspectDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
