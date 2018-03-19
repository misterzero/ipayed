import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerActivity } from './customer-activity.model';
import { CustomerActivityService } from './customer-activity.service';

@Component({
    selector: 'jhi-customer-activity-detail',
    templateUrl: './customer-activity-detail.component.html'
})
export class CustomerActivityDetailComponent implements OnInit, OnDestroy {

    customerActivity: CustomerActivity;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerActivityService: CustomerActivityService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerActivities();
    }

    load(id) {
        this.customerActivityService.find(id)
            .subscribe((customerActivityResponse: HttpResponse<CustomerActivity>) => {
                this.customerActivity = customerActivityResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerActivities() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerActivityListModification',
            (response) => this.load(this.customerActivity.id)
        );
    }
}
