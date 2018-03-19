import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpayedSharedModule } from '../../shared';
import {
    TransactionTypeService,
    TransactionTypePopupService,
    TransactionTypeComponent,
    TransactionTypeDetailComponent,
    TransactionTypeDialogComponent,
    TransactionTypePopupComponent,
    TransactionTypeDeletePopupComponent,
    TransactionTypeDeleteDialogComponent,
    transactionTypeRoute,
    transactionTypePopupRoute,
} from './';

const ENTITY_STATES = [
    ...transactionTypeRoute,
    ...transactionTypePopupRoute,
];

@NgModule({
    imports: [
        IpayedSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TransactionTypeComponent,
        TransactionTypeDetailComponent,
        TransactionTypeDialogComponent,
        TransactionTypeDeleteDialogComponent,
        TransactionTypePopupComponent,
        TransactionTypeDeletePopupComponent,
    ],
    entryComponents: [
        TransactionTypeComponent,
        TransactionTypeDialogComponent,
        TransactionTypePopupComponent,
        TransactionTypeDeleteDialogComponent,
        TransactionTypeDeletePopupComponent,
    ],
    providers: [
        TransactionTypeService,
        TransactionTypePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedTransactionTypeModule {}
