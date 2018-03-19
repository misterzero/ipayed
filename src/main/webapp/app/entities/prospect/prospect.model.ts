import { BaseEntity } from './../../shared';

export class Prospect implements BaseEntity {
    constructor(
        public id?: number,
        public address1?: string,
        public address2?: string,
        public businessName?: string,
        public city?: string,
        public country?: string,
        public customerType?: string,
        public dob?: string,
        public email?: string,
        public firstName?: string,
        public lastName?: string,
        public middleName?: string,
        public phone?: string,
        public isPoliticallyExposed?: string,
        public prospectId?: string,
        public state?: string,
        public status?: string,
        public tin?: string,
        public tinType?: string,
        public title?: string,
        public zip?: string,
    ) {
    }
}
