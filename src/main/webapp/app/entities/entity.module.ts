import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { IpayedBankAccountModule } from './bank-account/bank-account.module';
import { IpayedBankAccountTypeModule } from './bank-account-type/bank-account-type.module';
import { IpayedBadGuyListModule } from './bad-guy-list/bad-guy-list.module';
import { IpayedCustomerModule } from './customer/customer.module';
import { IpayedCustomerActivityModule } from './customer-activity/customer-activity.module';
import { IpayedProspectModule } from './prospect/prospect.module';
import { IpayedTrans8MarModule } from './trans-8-mar/trans-8-mar.module';
import { IpayedTransactionTypeModule } from './transaction-type/transaction-type.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        IpayedBankAccountModule,
        IpayedBankAccountTypeModule,
        IpayedBadGuyListModule,
        IpayedCustomerModule,
        IpayedCustomerActivityModule,
        IpayedProspectModule,
        IpayedTrans8MarModule,
        IpayedTransactionTypeModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class IpayedEntityModule {}
