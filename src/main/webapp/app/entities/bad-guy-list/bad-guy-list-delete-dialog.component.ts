import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BadGuyList } from './bad-guy-list.model';
import { BadGuyListPopupService } from './bad-guy-list-popup.service';
import { BadGuyListService } from './bad-guy-list.service';

@Component({
    selector: 'jhi-bad-guy-list-delete-dialog',
    templateUrl: './bad-guy-list-delete-dialog.component.html'
})
export class BadGuyListDeleteDialogComponent {

    badGuyList: BadGuyList;

    constructor(
        private badGuyListService: BadGuyListService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.badGuyListService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'badGuyListListModification',
                content: 'Deleted an badGuyList'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-bad-guy-list-delete-popup',
    template: ''
})
export class BadGuyListDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private badGuyListPopupService: BadGuyListPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.badGuyListPopupService
                .open(BadGuyListDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
