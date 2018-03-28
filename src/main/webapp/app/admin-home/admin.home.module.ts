import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FictionalbankSharedModule } from '../shared';

import { ADMIN_HOME_ROUTE, AdminHomeComponent } from './';

@NgModule({
    imports: [
        FictionalbankSharedModule,
        RouterModule.forChild([ ADMIN_HOME_ROUTE ])
    ],
    declarations: [
        AdminHomeComponent,
    ],
    entryComponents: [
    ],
    providers: [
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AdminHomeModule {}
