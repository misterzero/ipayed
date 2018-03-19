import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { Trans8MarComponent } from './trans-8-mar.component';
import { Trans8MarDetailComponent } from './trans-8-mar-detail.component';
import { Trans8MarPopupComponent } from './trans-8-mar-dialog.component';
import { Trans8MarDeletePopupComponent } from './trans-8-mar-delete-dialog.component';

export const trans8MarRoute: Routes = [
    {
        path: 'trans-8-mar',
        component: Trans8MarComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Trans8Mars'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'trans-8-mar/:id',
        component: Trans8MarDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Trans8Mars'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const trans8MarPopupRoute: Routes = [
    {
        path: 'trans-8-mar-new',
        component: Trans8MarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Trans8Mars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trans-8-mar/:id/edit',
        component: Trans8MarPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Trans8Mars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'trans-8-mar/:id/delete',
        component: Trans8MarDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Trans8Mars'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
