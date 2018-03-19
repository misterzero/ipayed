import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { ProspectComponent } from './prospect.component';
import { ProspectDetailComponent } from './prospect-detail.component';
import { ProspectPopupComponent } from './prospect-dialog.component';
import { ProspectDeletePopupComponent } from './prospect-delete-dialog.component';

export const prospectRoute: Routes = [
    {
        path: 'prospect',
        component: ProspectComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Prospects'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'prospect/:id',
        component: ProspectDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Prospects'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const prospectPopupRoute: Routes = [
    {
        path: 'prospect-new',
        component: ProspectPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Prospects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prospect/:id/edit',
        component: ProspectPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Prospects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'prospect/:id/delete',
        component: ProspectDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Prospects'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
