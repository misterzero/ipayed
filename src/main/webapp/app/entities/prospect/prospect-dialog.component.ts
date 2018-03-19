import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Prospect } from './prospect.model';
import { ProspectPopupService } from './prospect-popup.service';
import { ProspectService } from './prospect.service';

@Component({
    selector: 'jhi-prospect-dialog',
    templateUrl: './prospect-dialog.component.html'
})
export class ProspectDialogComponent implements OnInit {

    prospect: Prospect;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private prospectService: ProspectService,
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
        if (this.prospect.id !== undefined) {
            this.subscribeToSaveResponse(
                this.prospectService.update(this.prospect));
        } else {
            this.subscribeToSaveResponse(
                this.prospectService.create(this.prospect));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Prospect>>) {
        result.subscribe((res: HttpResponse<Prospect>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Prospect) {
        this.eventManager.broadcast({ name: 'prospectListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-prospect-popup',
    template: ''
})
export class ProspectPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private prospectPopupService: ProspectPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.prospectPopupService
                    .open(ProspectDialogComponent as Component, params['id']);
            } else {
                this.prospectPopupService
                    .open(ProspectDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
