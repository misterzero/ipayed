import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BadGuyList } from './bad-guy-list.model';
import { BadGuyListPopupService } from './bad-guy-list-popup.service';
import { BadGuyListService } from './bad-guy-list.service';

@Component({
    selector: 'jhi-bad-guy-list-dialog',
    templateUrl: './bad-guy-list-dialog.component.html'
})
export class BadGuyListDialogComponent implements OnInit {

    badGuyList: BadGuyList;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private badGuyListService: BadGuyListService,
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
        if (this.badGuyList.id !== undefined) {
            this.subscribeToSaveResponse(
                this.badGuyListService.update(this.badGuyList));
        } else {
            this.subscribeToSaveResponse(
                this.badGuyListService.create(this.badGuyList));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<BadGuyList>>) {
        result.subscribe((res: HttpResponse<BadGuyList>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: BadGuyList) {
        this.eventManager.broadcast({ name: 'badGuyListListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-bad-guy-list-popup',
    template: ''
})
export class BadGuyListPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private badGuyListPopupService: BadGuyListPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.badGuyListPopupService
                    .open(BadGuyListDialogComponent as Component, params['id']);
            } else {
                this.badGuyListPopupService
                    .open(BadGuyListDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
