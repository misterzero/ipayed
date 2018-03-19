import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { CustomerActivityComponent } from './customer-activity.component';
import { CustomerActivityDetailComponent } from './customer-activity-detail.component';
import { CustomerActivityPopupComponent } from './customer-activity-dialog.component';
import { CustomerActivityDeletePopupComponent } from './customer-activity-delete-dialog.component';

export const customerActivityRoute: Routes = [
    {
        path: 'customer-activity',
        component: CustomerActivityComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerActivities'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'customer-activity/:id',
        component: CustomerActivityDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerActivities'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const customerActivityPopupRoute: Routes = [
    {
        path: 'customer-activity-new',
        component: CustomerActivityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerActivities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-activity/:id/edit',
        component: CustomerActivityPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerActivities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'customer-activity/:id/delete',
        component: CustomerActivityDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'CustomerActivities'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
