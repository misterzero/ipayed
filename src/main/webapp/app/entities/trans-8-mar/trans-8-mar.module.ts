import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpayedSharedModule } from '../../shared';
import {
    Trans8MarService,
    Trans8MarPopupService,
    Trans8MarComponent,
    Trans8MarDetailComponent,
    Trans8MarDialogComponent,
    Trans8MarPopupComponent,
    Trans8MarDeletePopupComponent,
    Trans8MarDeleteDialogComponent,
    trans8MarRoute,
    trans8MarPopupRoute,
} from './';

const ENTITY_STATES = [
    ...trans8MarRoute,
    ...trans8MarPopupRoute,
];

@NgModule({
    imports: [
        IpayedSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        Trans8MarComponent,
        Trans8MarDetailComponent,
        Trans8MarDialogComponent,
        Trans8MarDeleteDialogComponent,
        Trans8MarPopupComponent,
        Trans8MarDeletePopupComponent,
    ],
    entryComponents: [
        Trans8MarComponent,
        Trans8MarDialogComponent,
        Trans8MarPopupComponent,
        Trans8MarDeleteDialogComponent,
        Trans8MarDeletePopupComponent,
    ],
    providers: [
        Trans8MarService,
        Trans8MarPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedTrans8MarModule {}
