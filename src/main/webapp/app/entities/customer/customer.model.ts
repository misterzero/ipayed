import { BaseEntity } from './../../shared';

export class Customer implements BaseEntity {
    constructor(
        public id?: number,
        public address1?: string,
        public address2?: string,
        public businessName?: string,
        public city?: string,
        public country?: string,
        public createDate?: string,
        public dob?: string,
        public email?: string,
        public firstName?: string,
        public inactiveDate?: string,
        public lastName?: string,
        public middleName?: string,
        public phone?: string,
        public isPoliticallyExposed?: string,
        public state?: string,
        public status?: string,
        public tin?: string,
        public tinType?: string,
        public title?: string,
        public zip?: string,
    ) {
    }
}
