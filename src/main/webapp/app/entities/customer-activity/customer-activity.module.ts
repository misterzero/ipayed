import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpayedSharedModule } from '../../shared';
import {
    CustomerActivityService,
    CustomerActivityPopupService,
    CustomerActivityComponent,
    CustomerActivityDetailComponent,
    CustomerActivityDialogComponent,
    CustomerActivityPopupComponent,
    CustomerActivityDeletePopupComponent,
    CustomerActivityDeleteDialogComponent,
    customerActivityRoute,
    customerActivityPopupRoute,
} from './';

const ENTITY_STATES = [
    ...customerActivityRoute,
    ...customerActivityPopupRoute,
];

@NgModule({
    imports: [
        IpayedSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CustomerActivityComponent,
        CustomerActivityDetailComponent,
        CustomerActivityDialogComponent,
        CustomerActivityDeleteDialogComponent,
        CustomerActivityPopupComponent,
        CustomerActivityDeletePopupComponent,
    ],
    entryComponents: [
        CustomerActivityComponent,
        CustomerActivityDialogComponent,
        CustomerActivityPopupComponent,
        CustomerActivityDeleteDialogComponent,
        CustomerActivityDeletePopupComponent,
    ],
    providers: [
        CustomerActivityService,
        CustomerActivityPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedCustomerActivityModule {}
