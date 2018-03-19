import { BaseEntity } from './../../shared';

export class TransactionType implements BaseEntity {
    constructor(
        public id?: number,
        public ipayedTransType?: string,
        public ipayedOriginatorFee?: number,
        public ipayedBenefactorFee?: number,
        public loopType?: string,
        public chargeupFee?: number,
        public feeToIpayed?: string,
        public transTypeCode?: string,
        public originatorAccountTypeCode?: string,
        public benefactorAccountTypeCode?: string,
        public transTypeCodes?: BaseEntity[],
    ) {
    }
}
