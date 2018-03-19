import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { BadGuyListComponent } from './bad-guy-list.component';
import { BadGuyListDetailComponent } from './bad-guy-list-detail.component';
import { BadGuyListPopupComponent } from './bad-guy-list-dialog.component';
import { BadGuyListDeletePopupComponent } from './bad-guy-list-delete-dialog.component';

export const badGuyListRoute: Routes = [
    {
        path: 'bad-guy-list',
        component: BadGuyListComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BadGuyLists'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'bad-guy-list/:id',
        component: BadGuyListDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BadGuyLists'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const badGuyListPopupRoute: Routes = [
    {
        path: 'bad-guy-list-new',
        component: BadGuyListPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BadGuyLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bad-guy-list/:id/edit',
        component: BadGuyListPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BadGuyLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'bad-guy-list/:id/delete',
        component: BadGuyListDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'BadGuyLists'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
