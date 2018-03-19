import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Prospect } from './prospect.model';
import { ProspectService } from './prospect.service';

@Component({
    selector: 'jhi-prospect-detail',
    templateUrl: './prospect-detail.component.html'
})
export class ProspectDetailComponent implements OnInit, OnDestroy {

    prospect: Prospect;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private prospectService: ProspectService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProspects();
    }

    load(id) {
        this.prospectService.find(id)
            .subscribe((prospectResponse: HttpResponse<Prospect>) => {
                this.prospect = prospectResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProspects() {
        this.eventSubscriber = this.eventManager.subscribe(
            'prospectListModification',
            (response) => this.load(this.prospect.id)
        );
    }
}
