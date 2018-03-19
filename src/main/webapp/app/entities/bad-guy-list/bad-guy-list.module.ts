import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpayedSharedModule } from '../../shared';
import {
    BadGuyListService,
    BadGuyListPopupService,
    BadGuyListComponent,
    BadGuyListDetailComponent,
    BadGuyListDialogComponent,
    BadGuyListPopupComponent,
    BadGuyListDeletePopupComponent,
    BadGuyListDeleteDialogComponent,
    badGuyListRoute,
    badGuyListPopupRoute,
} from './';

const ENTITY_STATES = [
    ...badGuyListRoute,
    ...badGuyListPopupRoute,
];

@NgModule({
    imports: [
        IpayedSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BadGuyListComponent,
        BadGuyListDetailComponent,
        BadGuyListDialogComponent,
        BadGuyListDeleteDialogComponent,
        BadGuyListPopupComponent,
        BadGuyListDeletePopupComponent,
    ],
    entryComponents: [
        BadGuyListComponent,
        BadGuyListDialogComponent,
        BadGuyListPopupComponent,
        BadGuyListDeleteDialogComponent,
        BadGuyListDeletePopupComponent,
    ],
    providers: [
        BadGuyListService,
        BadGuyListPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedBadGuyListModule {}
