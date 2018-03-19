import { BaseEntity } from './../../shared';

export class BankAccount implements BaseEntity {
    constructor(
        public id?: number,
        public tosFlag?: string,
        public dateOfTos?: string,
        public accountBeginDate?: string,
        public accountEndDate?: string,
        public accountTypeCode?: BaseEntity,
        public customer?: BaseEntity,
    ) {
    }
}
