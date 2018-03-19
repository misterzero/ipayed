import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Trans8Mar } from './trans-8-mar.model';
import { Trans8MarService } from './trans-8-mar.service';

@Component({
    selector: 'jhi-trans-8-mar-detail',
    templateUrl: './trans-8-mar-detail.component.html'
})
export class Trans8MarDetailComponent implements OnInit, OnDestroy {

    trans8Mar: Trans8Mar;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private trans8MarService: Trans8MarService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTrans8Mars();
    }

    load(id) {
        this.trans8MarService.find(id)
            .subscribe((trans8MarResponse: HttpResponse<Trans8Mar>) => {
                this.trans8Mar = trans8MarResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTrans8Mars() {
        this.eventSubscriber = this.eventManager.subscribe(
            'trans8MarListModification',
            (response) => this.load(this.trans8Mar.id)
        );
    }
}
