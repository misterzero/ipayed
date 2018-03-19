import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { IpayedSharedModule } from '../../shared';
import {
    ProspectService,
    ProspectPopupService,
    ProspectComponent,
    ProspectDetailComponent,
    ProspectDialogComponent,
    ProspectPopupComponent,
    ProspectDeletePopupComponent,
    ProspectDeleteDialogComponent,
    prospectRoute,
    prospectPopupRoute,
} from './';

const ENTITY_STATES = [
    ...prospectRoute,
    ...prospectPopupRoute,
];

@NgModule({
    imports: [
        IpayedSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProspectComponent,
        ProspectDetailComponent,
        ProspectDialogComponent,
        ProspectDeleteDialogComponent,
        ProspectPopupComponent,
        ProspectDeletePopupComponent,
    ],
    entryComponents: [
        ProspectComponent,
        ProspectDialogComponent,
        ProspectPopupComponent,
        ProspectDeleteDialogComponent,
        ProspectDeletePopupComponent,
    ],
    providers: [
        ProspectService,
        ProspectPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedProspectModule {}
