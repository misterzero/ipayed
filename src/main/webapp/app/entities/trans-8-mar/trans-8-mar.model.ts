import { BaseEntity } from './../../shared';

export class Trans8Mar implements BaseEntity {
    constructor(
        public id?: number,
        public amount?: number,
        public beginAccountBalance?: number,
        public isCd?: string,
        public currency?: string,
        public datetime?: string,
        public endAccountBalance?: number,
        public entityFee?: number,
        public linkedIpayedTrans?: string,
        public linkedTrans?: string,
        public transFamilyId?: string,
        public bankAccount?: BaseEntity,
        public customer?: BaseEntity,
        public transTypeCode?: BaseEntity,
    ) {
    }
}
