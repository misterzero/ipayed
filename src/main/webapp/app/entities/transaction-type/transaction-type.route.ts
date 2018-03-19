import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TransactionTypeComponent } from './transaction-type.component';
import { TransactionTypeDetailComponent } from './transaction-type-detail.component';
import { TransactionTypePopupComponent } from './transaction-type-dialog.component';
import { TransactionTypeDeletePopupComponent } from './transaction-type-delete-dialog.component';

export const transactionTypeRoute: Routes = [
    {
        path: 'transaction-type',
        component: TransactionTypeComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransactionTypes'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'transaction-type/:id',
        component: TransactionTypeDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransactionTypes'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const transactionTypePopupRoute: Routes = [
    {
        path: 'transaction-type-new',
        component: TransactionTypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransactionTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'transaction-type/:id/edit',
        component: TransactionTypePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransactionTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'transaction-type/:id/delete',
        component: TransactionTypeDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TransactionTypes'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
