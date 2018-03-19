import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { BadGuyList } from './bad-guy-list.model';
import { BadGuyListService } from './bad-guy-list.service';

@Component({
    selector: 'jhi-bad-guy-list-detail',
    templateUrl: './bad-guy-list-detail.component.html'
})
export class BadGuyListDetailComponent implements OnInit, OnDestroy {

    badGuyList: BadGuyList;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private badGuyListService: BadGuyListService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInBadGuyLists();
    }

    load(id) {
        this.badGuyListService.find(id)
            .subscribe((badGuyListResponse: HttpResponse<BadGuyList>) => {
                this.badGuyList = badGuyListResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInBadGuyLists() {
        this.eventSubscriber = this.eventManager.subscribe(
            'badGuyListListModification',
            (response) => this.load(this.badGuyList.id)
        );
    }
}
