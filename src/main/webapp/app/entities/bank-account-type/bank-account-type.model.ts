import { BaseEntity } from './../../shared';

export class BankAccountType implements BaseEntity {
    constructor(
        public id?: number,
        public possibleIpayedAccountType?: string,
        public accountTypeCode?: string,
        public accountTypeCodes?: BaseEntity[],
    ) {
    }
}
